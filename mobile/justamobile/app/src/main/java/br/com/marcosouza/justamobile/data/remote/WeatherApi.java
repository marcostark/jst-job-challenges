package br.com.marcosouza.justamobile.data.remote;

import br.com.marcosouza.justamobile.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?")
    Call<WeatherResponse> getCurrentWeather(
            @Query("q") String city,
            @Query("APPID") String app_id,
            @Query("units") String metric);
}
