package com.example.pesaapp.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.pesaapp.Data.More;
import com.example.pesaapp.Network.AllInterface;
import com.example.pesaapp.Network.FeaturedInterface;
import com.example.pesaapp.Network.SearchInterface;
import com.example.pesaapp.Network.ValidRetro;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LandingModel {
    private MutableLiveData<List<More>> featuredlist;
    private MutableLiveData<List<More>> searchlist;
    private MutableLiveData<List<More>> alllist;

    public MutableLiveData<List<More>> getAlllist() {
        return alllist;
    }

    public MutableLiveData<List<More>> getSearchlist() {
        return searchlist;
    }

    public MutableLiveData<List<More>> getFeaturedlist() {
        return featuredlist;
    }

    public LandingModel(){
        featuredlist = new MutableLiveData<>();
        searchlist = new MutableLiveData<>();
        alllist = new MutableLiveData<>();
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
    public  void searchResults(String search){
        SearchInterface searchInterface = ValidRetro.getValidRetro().create(SearchInterface.class);
        Call<List<More>> listCall= searchInterface.searchResults(search);
        listCall.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                Log.e("sam","The size of search list is " + response.body().size());
                searchlist.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {
            }
        });
    }

    public void getAll() {
        AllInterface allInterface = ValidRetro.getValidRetro().create(AllInterface.class);
        Call<List<More>> listCall= allInterface.getall();
        listCall.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                Log.e("sam","The size of search list is " + response.body().size());
                alllist.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {
            }
        });
    }
}
