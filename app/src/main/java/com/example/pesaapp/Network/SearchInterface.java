package com.example.pesaapp.Network;

import com.example.pesaapp.data.More;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchInterface {
    @GET("search/{searchParam}")
    Call<List<More>> searchResults(@Path("searchParam") String searchParam);
}
