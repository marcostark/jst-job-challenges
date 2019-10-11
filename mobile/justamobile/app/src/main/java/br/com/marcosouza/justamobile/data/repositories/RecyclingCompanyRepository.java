package br.com.marcosouza.justamobile.data.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.net.SocketTimeoutException;

import br.com.marcosouza.justamobile.data.core.RetrofitService;
import br.com.marcosouza.justamobile.data.remote.RecyclePlusApi;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclingCompanyRepository {

    private static RecyclingCompanyRepository recyclingCompanyRepository;

    public static RecyclingCompanyRepository getInstance(){
        if (recyclingCompanyRepository == null){
            recyclingCompanyRepository = new RecyclingCompanyRepository();
        }
        return recyclingCompanyRepository;
    }

    private RecyclePlusApi recyclePlusApi;

    public RecyclingCompanyRepository(){
        recyclePlusApi = RetrofitService.createService(RecyclePlusApi.class);
    }

    public MutableLiveData<RecyclingCompanyResponse> getCompanies(){
        final MutableLiveData<RecyclingCompanyResponse> newsData = new MutableLiveData<>();
        recyclePlusApi.getRecyclingCompanies().enqueue(new Callback<RecyclingCompanyResponse>() {
            @Override
            public void onResponse(Call<RecyclingCompanyResponse> call,
                                   Response<RecyclingCompanyResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RecyclingCompanyResponse> call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    newsData.setValue(new RecyclingCompanyResponse(t));
                }
                newsData.setValue(new RecyclingCompanyResponse(t));
            }
        });
        return newsData;
    }
}
