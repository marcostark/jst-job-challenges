package br.com.marcosouza.justamobile.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.marcosouza.justamobile.data.repositories.NewsRepository;
import br.com.marcosouza.justamobile.model.NewsResponse;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mMutableLiveData;
    private NewsRepository mNewsRepository;

    public void init() {
        if(mMutableLiveData != null){
            return;
        }
        //mText = new MutableLiveData<>();
        mNewsRepository = NewsRepository.getInstance();
        mMutableLiveData = mNewsRepository.getNews("globo",
                "dengue",
                "c34c7d5ca69042b08d58a226ea5106d2");
    }

    public LiveData<NewsResponse> getNewsRepository() {
        return mMutableLiveData;
    }
}