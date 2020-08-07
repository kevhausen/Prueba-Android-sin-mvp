package com.prueba.pruebadog.retrofit;

import com.prueba.pruebadog.model.BreedImageResponse;
import com.prueba.pruebadog.model.BreedListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApi {

    @GET("api/breeds/list")
    Call<BreedListResponse> getBreedListApi();

    @GET("api/breed/{breed}/images")
    Call<BreedImageResponse> getBreedImagesApi(@Path("breed") String breed);

}
