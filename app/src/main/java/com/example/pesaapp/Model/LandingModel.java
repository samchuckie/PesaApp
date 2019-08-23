package com.example.pesaapp.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.pesaapp.Data.More;
import com.example.pesaapp.Network.AllInterface;
import com.example.pesaapp.Network.FavouriteInterface;
import com.example.pesaapp.Network.FeaturedInterface;
import com.example.pesaapp.Network.SearchInterface;
import com.example.pesaapp.Network.ValidRetro;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingModel {
    private MutableLiveData<List<More>> featuredlist;
    private MutableLiveData<List<More>> searchlist;
    private MutableLiveData<List<More>> alllist;
    private MutableLiveData<List<More>> favouritelist;

    public MutableLiveData<List<More>> getFavouritelist() {
        return favouritelist;
    }
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
        favouritelist = new MutableLiveData<>();
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
//                Log.e("sam","The size of search list is " + response.body().size());
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

    public void saveFavourite(long phone, More more) {
        FavouriteInterface favouriteInterface = ValidRetro.getValidRetro().create(FavouriteInterface.class);
        Call<More> insertfav = favouriteInterface.postFavourite(phone, more.getTitle(),more.getHost(),more.getPhoto(),
                more.getStart_date(),more.getLocation(),more.getDescription(),more.getTime_from(),more.getTime_to()
                ,more.getEarly_price(),more.getAdvance_price(),more.getClose_date(),more.getClose_time());

        insertfav.enqueue(new Callback<More>() {
            @Override
            public void onResponse(Call<More> call, Response<More> response) {
            }
            @Override
            public void onFailure(Call<More> call, Throwable t) {

            }
        });
    }

    public void getFavorite(long phone) {
        FavouriteInterface favouriteInterface = ValidRetro.getValidRetro().create(FavouriteInterface.class);
        Call<List<More>> userFav = favouriteInterface.getFavourite(phone);
        userFav.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                Log.e("sam" , "Response code is from array" + response.code());
                favouritelist.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {

            }
        });
    }
}
