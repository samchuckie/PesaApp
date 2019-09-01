package com.example.pesaapp.Network;

import com.example.pesaapp.Data.More;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavouriteInterface {
    @GET("favorite")
    Call<List<More>> getFavourite(@Query("phone") long phone);

    @POST("favorite")
    Call<More> postFavourite(@Query("phone") long phone,@Query("title") String title);
}
