package com.example.pesaapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import com.example.pesaapp.Data.More;
import com.example.pesaapp.Model.ManyModel;

import java.util.List;

public class ManyVM extends AndroidViewModel {
    private ManyModel manyModel;
    private MutableLiveData<List<More>> moreList;

    public MutableLiveData<List<More>> getMoreList() {
        return moreList;
    }

    public ManyVM(@NonNull Application application) {
        super(application);
        manyModel = new ManyModel();
        moreList = manyModel.getCategoryList();
    }


    public void getCategory(String category) {
        manyModel.getCatCall(category);
    }

    public void getAll() {
        manyModel.getAll();
    }

    public void getFavourite(long phone) {
        manyModel.getfav(phone);
    }
}
