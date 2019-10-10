package br.com.marcosouza.justamobile.data.core;

import br.com.marcosouza.justamobile.util.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstants.RECICLA_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
