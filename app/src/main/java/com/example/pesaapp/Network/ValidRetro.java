package com.example.pesaapp.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.pesaapp.Data.Constants.LOCALHOST;

public class ValidRetro {
    public static Retrofit getValidRetro(){
        return  new Retrofit.Builder()
                .baseUrl(LOCALHOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
