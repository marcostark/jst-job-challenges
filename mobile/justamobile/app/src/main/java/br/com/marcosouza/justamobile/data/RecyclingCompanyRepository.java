package br.com.marcosouza.justamobile.data;

import androidx.lifecycle.MutableLiveData;

import br.com.marcosouza.justamobile.data.core.RecyclaRetrofitService;
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
        recyclePlusApi = RecyclaRetrofitService.createService(RecyclePlusApi.class);
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
                newsData.setValue(new RecyclingCompanyResponse(t));
            }
        });
        return newsData;
    }
}
