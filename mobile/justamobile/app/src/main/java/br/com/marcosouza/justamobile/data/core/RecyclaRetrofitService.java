package br.com.marcosouza.justamobile.data.core;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*TODO mudar para tratamento de url dinamica com okhttp*/

public class RecyclaRetrofitService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.100.3:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
