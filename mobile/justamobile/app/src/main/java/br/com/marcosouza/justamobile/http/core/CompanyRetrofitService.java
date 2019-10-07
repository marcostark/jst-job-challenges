package br.com.marcosouza.justamobile.http.core;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompanyRetrofitService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.100.3:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
