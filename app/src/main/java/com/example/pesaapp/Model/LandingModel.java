package com.example.pesaapp.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.pesaapp.Data.More;
import com.example.pesaapp.Network.FeaturedInterface;
import com.example.pesaapp.Network.ValidRetro;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingModel {
    private MutableLiveData<List<More>> featuredlist;

    public MutableLiveData<List<More>> getFeaturedlist() {
        return featuredlist;
    }

    public LandingModel(){
        featuredlist = new MutableLiveData<>();
        Log.e("sam","created Landing Model");

    }
    public void getFeatures(){
        FeaturedInterface featuredInterface = ValidRetro.getValidRetro().create(FeaturedInterface.class);
        Call<List<More>> listCall= featuredInterface.getFeatured();
        listCall.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                Log.e("sam","The size is " + response.body().size());
                featuredlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {

            }
        });

    }
}
