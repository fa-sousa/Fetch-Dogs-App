package com.example.fetchdogsapp.service;

import com.example.fetchdogsapp.models.DogsList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/breeds/list/all")
    Call<DogsList> getDogs();

}
