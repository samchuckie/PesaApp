package com.example.pesaapp.Network;

import com.example.pesaapp.Data.PesaUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ValidInterface {
    @GET("/valid")
    Call<PesaUsers> signin(@Query("phone") Long phone, @Query("password") String password);

    @POST("/valid")
    Call<PesaUsers> signup(@Query("phone") Long phone, @Query("password") String password, @Query("email") String email);
}
