package com.prueba.pruebadog.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitK;
    private static final String URL_DOG = "https://dog.ceo/";

public static Retrofit getRetrofitInstance(){
    if (retrofitK == null) {
        retrofitK = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_DOG)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return retrofitK;


    }
}
