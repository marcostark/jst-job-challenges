package br.com.marcosouza.justamobile.data.repositories;

import androidx.lifecycle.MutableLiveData;

import br.com.marcosouza.justamobile.data.core.RetrofitService;
import br.com.marcosouza.justamobile.data.remote.NewsApi;
import br.com.marcosouza.justamobile.model.NewsResponse;
import br.com.marcosouza.justamobile.util.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.createService(NewsApi.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String topic, String key){
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getFilterNewsList(AppConstants.NEWS_ENDPOINT ,source, topic, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
