package com.example.pesaapp.Network;

import com.example.pesaapp.Data.More;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllInterface {
    @GET("/getall")
    public Call<List<More>> getall();
}
