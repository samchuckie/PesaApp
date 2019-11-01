package com.example.pesaapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.pesaapp.data.More;
import com.example.pesaapp.Model.ManyModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pesaapp.data.Constants.PREFKEY;
import static com.example.pesaapp.data.Constants.PREFNAME;

public class ManyVM extends AndroidViewModel {
    private ManyModel manyModel;
    private MutableLiveData<List<More>> moreList;
    private long phone;
    public ManyVM(@NonNull Application application) {
        super(application);
        SharedPreferences sharedPreferences = application.getSharedPreferences(PREFNAME,MODE_PRIVATE);
        phone = sharedPreferences.getLong(PREFKEY,0);
        manyModel = new ManyModel();
        moreList = manyModel.getCategoryList();
    }

    public MutableLiveData<List<More>> getMoreList() {
        return moreList;
    }
    public void getCategory(String category) {
        manyModel.getCatCall(category);
    }
    public void getAll() {
        manyModel.getAll();
    }
    public void getFavourite() {
        manyModel.getfav(phone);
    }
    public void saveFavourite(String  title) {
        manyModel.saveFavourite(phone ,title);
    }

}
