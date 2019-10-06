package br.com.marcosouza.justamobile.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.marcosouza.justamobile.http.RecyclingCompanyRepository;
import br.com.marcosouza.justamobile.model.RecyclingCompany;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import br.com.marcosouza.justamobile.util.Utils;

public class RecyclingCompaniesViewModel extends ViewModel {

    private MutableLiveData<RecyclingCompanyResponse> mMutableLiveData;
    private RecyclingCompanyRepository recyclingCompanyRepository;

    public void init() {
        if(mMutableLiveData != null){
            return;
        }
        recyclingCompanyRepository = RecyclingCompanyRepository.getInstance();
        mMutableLiveData = recyclingCompanyRepository.getCompanies();

    }

    public LiveData<RecyclingCompanyResponse> getCompaniesRepository() {
        return mMutableLiveData;
    }
}