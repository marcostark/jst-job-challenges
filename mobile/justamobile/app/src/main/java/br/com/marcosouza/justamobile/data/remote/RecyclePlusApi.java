package br.com.marcosouza.justamobile.data.remote;

import br.com.marcosouza.justamobile.model.NeighborhoodsResponse;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclePlusApi {

    @GET("api/v1/core/recycling_company/")
    Call<RecyclingCompanyResponse> getRecyclingCompanies();

    @GET("api/v1/core/collection_neighbordhoods/")
    Call<NeighborhoodsResponse> getNeighbordhoods();

    @GET("api/v1/core/collection_points/")
    Call<NeighborhoodsResponse> getCollectionPoints();
}
