package com.prueba.pruebadog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BreedImageResponse {

    @SerializedName("message")
    private ArrayList<String> imageUrl =new ArrayList<>();

    public ArrayList<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
