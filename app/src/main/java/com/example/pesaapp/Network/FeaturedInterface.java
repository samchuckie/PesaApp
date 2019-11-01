package com.example.pesaapp.Network;

import com.example.pesaapp.data.More;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FeaturedInterface {
    @GET("/featured")
    public Call<List<More>> getFeatured ();
}
