package br.com.marcosouza.justamobile.http;

import br.com.marcosouza.justamobile.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("everything")
    Call<NewsResponse> getFilterNewsList(@Query("sources") String source,
                                   @Query("q") String topic,
                                   @Query("apiKey") String apiKey);
}
