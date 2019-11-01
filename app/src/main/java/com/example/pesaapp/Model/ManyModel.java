package com.example.pesaapp.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.pesaapp.data.More;
import com.example.pesaapp.Network.AllInterface;
import com.example.pesaapp.Network.CategoryInterface;
import com.example.pesaapp.Network.FavouriteInterface;
import com.example.pesaapp.Network.ValidRetro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManyModel {
    private MutableLiveData<List<More>> categoryList;

    public ManyModel() {
        categoryList = new MutableLiveData<>();
    }

    public MutableLiveData<List<More>> getCategoryList() {
        return categoryList;
    }

    public void getCatCall(String category) {
        CategoryInterface categoryInterface = ValidRetro.getValidRetro().create(CategoryInterface.class);
        Call<List<More>> categorycall = categoryInterface.getCategory(category);
        categorycall.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                categoryList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {
            }
        });
    }

    public void getAll() {
        AllInterface allInterface = ValidRetro.getValidRetro().create(AllInterface.class);
        Call<List<More>> listCall = allInterface.getall();
        listCall.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                Log.e("sam", "The size of search list is " + response.body().size());
                categoryList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {
            }
        });
    }

    public void getfav(long phone) {
        FavouriteInterface favouriteInterface = ValidRetro.getValidRetro().create(FavouriteInterface.class);
        Call<List<More>> userFav = favouriteInterface.getFavourite(phone);
        userFav.enqueue(new Callback<List<More>>() {
            @Override
            public void onResponse(Call<List<More>> call, Response<List<More>> response) {
                categoryList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<More>> call, Throwable t) {

            }
        });
    }

    public void saveFavourite(long phone, String title) {
        FavouriteInterface favouriteInterface = ValidRetro.getValidRetro().create(FavouriteInterface.class);
        Call<More> insertfav = favouriteInterface.postFavourite(phone, title);

        insertfav.enqueue(new Callback<More>() {
            @Override
            public void onResponse(Call<More> call, Response<More> response) {
                // handle already favourite been clicked. Delete ?inform user?
            }
            @Override
            public void onFailure(Call<More> call, Throwable t) {
            }
        });
    }
}
