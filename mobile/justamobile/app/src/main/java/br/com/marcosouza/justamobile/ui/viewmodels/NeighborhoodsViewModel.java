package br.com.marcosouza.justamobile.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.marcosouza.justamobile.data.repositories.NeighbordhoodsRepository;
import br.com.marcosouza.justamobile.model.NeighborhoodsResponse;

public class NeighborhoodsViewModel extends ViewModel {

    private MutableLiveData<NeighborhoodsResponse> mMutableLiveData;
    private NeighbordhoodsRepository neighbordhoodsRepository;

    public void init() {
        if(mMutableLiveData != null){
            return;
        }
        neighbordhoodsRepository = NeighbordhoodsRepository.getInstance();
        mMutableLiveData = neighbordhoodsRepository.getCompanies();
    }

    public LiveData<NeighborhoodsResponse> getNeighbordhoodsRepository() {
        return mMutableLiveData;
    }
}