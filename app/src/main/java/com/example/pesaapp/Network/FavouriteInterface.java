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
    Call<More> postFavourite(@Query("phone") long phone,@Query("title") String title,@Query("host") String host, @Query("photo") String photo,
                             @Query("start_date") String start_date,
                             @Query("location") String location,@Query("description") String description,
                             @Query("time_from") String time_from,@Query("time_to") String time_to,
                             @Query("early_price") int early_price,
                             @Query("advance_price") int advance_price,
                             @Query("close_date") String close_date,@Query("close_time") String close_time);

}
