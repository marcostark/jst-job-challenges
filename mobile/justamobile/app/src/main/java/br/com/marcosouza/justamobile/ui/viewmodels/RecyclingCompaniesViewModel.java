package br.com.marcosouza.justamobile.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecyclingCompaniesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecyclingCompaniesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recycling companies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}