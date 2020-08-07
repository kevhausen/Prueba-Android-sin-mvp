package com.prueba.pruebadog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BreedListResponse {

    @SerializedName("message")
    private ArrayList<String> breedList =new ArrayList<String>();
    private String status;


    public ArrayList<String> getBreedList() {
        return breedList;
    }

    public void setBreedList(ArrayList<String> breedList) {
        this.breedList = breedList;
    }
}
