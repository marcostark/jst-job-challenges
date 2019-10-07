package br.com.marcosouza.justamobile.http;

import br.com.marcosouza.justamobile.model.NewsResponse;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecyclingCompanyApi {
    @GET("api/v1/core/recycling_company/")
    Call<RecyclingCompanyResponse> getRecyclingCompanies();
}
