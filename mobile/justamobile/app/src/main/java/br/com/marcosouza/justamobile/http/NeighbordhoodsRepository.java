package br.com.marcosouza.justamobile.http;

import androidx.lifecycle.MutableLiveData;

import br.com.marcosouza.justamobile.http.core.RecyclaRetrofitService;
import br.com.marcosouza.justamobile.model.NeighborhoodsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeighbordhoodsRepository {
    private static NeighbordhoodsRepository neighbordhoodsRepository;

    public static NeighbordhoodsRepository getInstance(){
        if (neighbordhoodsRepository == null){
            neighbordhoodsRepository = new NeighbordhoodsRepository();
        }
        return neighbordhoodsRepository;
    }

    private RecyclingCompanyApi recyclingCompanyApi;

    public NeighbordhoodsRepository(){
        recyclingCompanyApi = RecyclaRetrofitService.createService(RecyclingCompanyApi.class);
    }

    public MutableLiveData<NeighborhoodsResponse> getCompanies(){
        final MutableLiveData<NeighborhoodsResponse> newsData = new MutableLiveData<>();
        recyclingCompanyApi.getNeighbordhoods().enqueue(new Callback<NeighborhoodsResponse>() {
            @Override
            public void onResponse(Call<NeighborhoodsResponse> call,
                                   Response<NeighborhoodsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NeighborhoodsResponse> call, Throwable t) {
                newsData.setValue(new NeighborhoodsResponse(t));
            }
        });
        return newsData;
    }
}
