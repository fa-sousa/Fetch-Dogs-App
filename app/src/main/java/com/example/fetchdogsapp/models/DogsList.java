package com.example.fetchdogsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DogsList {

    @SerializedName("dogs")
    @Expose
    private ArrayList<Dogs> dogs = null;

    public ArrayList<Dogs> getDogs() {
        return dogs;
    }

    public void setDogs(ArrayList<Dogs> dogs) {
        this.dogs = dogs;
    }

}
