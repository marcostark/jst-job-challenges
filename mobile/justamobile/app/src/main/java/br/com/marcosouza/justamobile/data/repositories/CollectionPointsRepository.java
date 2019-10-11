package br.com.marcosouza.justamobile.data.repositories;

import androidx.lifecycle.MutableLiveData;

import java.net.SocketTimeoutException;

import br.com.marcosouza.justamobile.data.core.RetrofitService;
import br.com.marcosouza.justamobile.data.remote.RecyclePlusApi;
import br.com.marcosouza.justamobile.model.CollectionPointsResponse;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionPointsRepository {

    private static CollectionPointsRepository recyclingCompanyRepository;

    public static CollectionPointsRepository getInstance(){
        if (recyclingCompanyRepository == null){
            recyclingCompanyRepository = new CollectionPointsRepository();
        }
        return recyclingCompanyRepository;
    }

    private RecyclePlusApi recyclePlusApi;

    public CollectionPointsRepository(){
        recyclePlusApi = RetrofitService.createService(RecyclePlusApi.class);
    }

    public MutableLiveData<CollectionPointsResponse> getCollectionPoints(){
        final MutableLiveData<CollectionPointsResponse> newsData = new MutableLiveData<>();
        recyclePlusApi.getCollectionPoints().enqueue(new Callback<CollectionPointsResponse>() {
            @Override
            public void onResponse(Call<CollectionPointsResponse> call,
                                   Response<CollectionPointsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CollectionPointsResponse> call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    newsData.setValue(new CollectionPointsResponse(t));
                }
                newsData.setValue(new CollectionPointsResponse(t));
            }
        });
        return newsData;
    }
}
