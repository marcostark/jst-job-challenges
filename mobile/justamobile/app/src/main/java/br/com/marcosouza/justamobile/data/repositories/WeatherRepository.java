package br.com.marcosouza.justamobile.data.repositories;

import androidx.lifecycle.MutableLiveData;

import br.com.marcosouza.justamobile.data.core.RetrofitService;
import br.com.marcosouza.justamobile.data.core.WeatherRetrofitService;
import br.com.marcosouza.justamobile.data.remote.NewsApi;
import br.com.marcosouza.justamobile.data.remote.WeatherApi;
import br.com.marcosouza.justamobile.model.NewsResponse;
import br.com.marcosouza.justamobile.model.Weather;
import br.com.marcosouza.justamobile.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private String APPID = "690cac557da1ff012090de70f05d808b";
    private String UNITS = "metric";
    private String CITY = "Serra Talhada";

    private static WeatherRepository newsRepository;

    public static WeatherRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new WeatherRepository();
        }
        return newsRepository;
    }

    private WeatherApi weatherApi;

    public WeatherRepository(){
        weatherApi = WeatherRetrofitService.createService(WeatherApi.class);
    }

    public MutableLiveData<WeatherResponse> getWeather(){
        final MutableLiveData<WeatherResponse> newsData = new MutableLiveData<>();
        weatherApi.getCurrentWeather(CITY, APPID, UNITS).enqueue(new Callback<WeatherResponse>() {
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
