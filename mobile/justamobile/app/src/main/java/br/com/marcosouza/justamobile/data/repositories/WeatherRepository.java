package br.com.marcosouza.justamobile.data.repositories;

import androidx.lifecycle.MutableLiveData;

import br.com.marcosouza.justamobile.data.core.RetrofitService;
import br.com.marcosouza.justamobile.data.remote.WeatherApi;
import br.com.marcosouza.justamobile.model.WeatherResponse;
import br.com.marcosouza.justamobile.util.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {


    private static WeatherRepository newsRepository;

    public static WeatherRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new WeatherRepository();
        }
        return newsRepository;
    }

    private WeatherApi weatherApi;

    public WeatherRepository(){
        weatherApi = RetrofitService.createService(WeatherApi.class);
    }

    public MutableLiveData<WeatherResponse> getWeather(){
        final MutableLiveData<WeatherResponse> newsData = new MutableLiveData<>();
        weatherApi.getCurrentWeather(AppConstants.WEATHER_ENDPOINT,AppConstants.CITY, AppConstants.API_KEY, AppConstants.UNITS).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call,
                                   Response<WeatherResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
