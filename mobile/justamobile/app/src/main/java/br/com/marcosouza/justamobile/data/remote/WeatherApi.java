package br.com.marcosouza.justamobile.data.remote;

import br.com.marcosouza.justamobile.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface WeatherApi {
    //@GET("weather?")
    @GET
    Call<WeatherResponse> getCurrentWeather(
            @Url String url,
            @Query("q") String city,
            @Query("APPID") String app_id,
            @Query("units") String metric);
}
