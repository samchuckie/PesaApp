package com.example.pesaapp.Network;

import com.example.pesaapp.Data.More;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AllInterface {
    @GET("/getall")
    Call<List<More>> getall();

    @POST("test")
    Call<ResponseBody> gettest(@Body RequestBody params);
}
