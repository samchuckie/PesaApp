package com.example.pesaapp.Model;

import android.arch.lifecycle.MutableLiveData;

import com.example.pesaapp.Data.More;
import com.example.pesaapp.Network.CategoryInterface;
import com.example.pesaapp.Network.ValidRetro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManyModel {
    private MutableLiveData<List<More>> categoryList;

    public ManyModel(){
        categoryList = new MutableLiveData<>();
    }

    public MutableLiveData<List<More>> getCategoryList() {
        return categoryList;
    }

    public void getCatCall(String category) {
        CategoryInterface categoryInterface = ValidRetro.getValidRetro().create(CategoryInterface.class);
        Call<List<More>> categorycall =  categoryInterface.getCategory(category);
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
}
