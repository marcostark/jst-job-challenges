package br.com.marcosouza.justamobile.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.marcosouza.justamobile.data.repositories.NewsRepository;
import br.com.marcosouza.justamobile.data.repositories.WeatherRepository;
import br.com.marcosouza.justamobile.model.NewsResponse;
import br.com.marcosouza.justamobile.model.WeatherResponse;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<WeatherResponse> mMutableLiveData;
    private WeatherRepository mWeatherRepository;


    public void init(){
        if(mMutableLiveData != null){
            return;
        }
    //public WeatherViewModel() {
        mWeatherRepository = WeatherRepository.getInstance();
        mMutableLiveData = mWeatherRepository.getWeather();
    }

    public LiveData<WeatherResponse> getWeatherRepository() {
        return mMutableLiveData;
    }
}