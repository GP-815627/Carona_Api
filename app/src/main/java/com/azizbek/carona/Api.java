package com.azizbek.carona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("countries")
    Call<List<Corona>> getAllcountry();
}
