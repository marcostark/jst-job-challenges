package br.com.marcosouza.justamobile.data.remote;

import br.com.marcosouza.justamobile.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NewsApi {
    @GET
    Call<NewsResponse> getFilterNewsList(
            @Url String url,
            @Query("sources") String source,
            @Query("q") String topic,
            @Query("apiKey") String apiKey);
}
