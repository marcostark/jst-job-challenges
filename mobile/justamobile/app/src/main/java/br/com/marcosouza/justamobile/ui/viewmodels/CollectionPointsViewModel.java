package br.com.marcosouza.justamobile.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.marcosouza.justamobile.data.repositories.CollectionPointsRepository;
import br.com.marcosouza.justamobile.model.CollectionPointsResponse;

public class CollectionPointsViewModel extends ViewModel {

    private MutableLiveData<CollectionPointsResponse> mMutableLiveData;
    private CollectionPointsRepository neighbordhoodsRepository;

    public void init() {
        if(mMutableLiveData != null){
            return;
        }
        neighbordhoodsRepository = CollectionPointsRepository.getInstance();
        mMutableLiveData = neighbordhoodsRepository.getCollectionPoints();
    }

    public LiveData<CollectionPointsResponse> getNeighbordhoodsRepository() {
        return mMutableLiveData;
    }
}