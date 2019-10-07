package br.com.marcosouza.justamobile.http;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import br.com.marcosouza.justamobile.http.core.CompanyRetrofitService;
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

    private RecyclingCompanyApi recyclingCompanyApi;

    public RecyclingCompanyRepository(){
        recyclingCompanyApi = CompanyRetrofitService.createService(RecyclingCompanyApi.class);
    }

    public MutableLiveData<RecyclingCompanyResponse> getCompanies(){
        final MutableLiveData<RecyclingCompanyResponse> newsData = new MutableLiveData<>();
        recyclingCompanyApi.getRecyclingCompanies().enqueue(new Callback<RecyclingCompanyResponse>() {
            @Override
            public void onResponse(Call<RecyclingCompanyResponse> call,
                                   Response<RecyclingCompanyResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                    Log.i("REQUISICAO", String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(Call<RecyclingCompanyResponse> call, Throwable t) {
                newsData.setValue(null);
                Log.i("REQUISICAO", "Erro");
            }
        });
        return newsData;
    }
}
