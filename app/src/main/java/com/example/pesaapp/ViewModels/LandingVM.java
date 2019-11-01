package com.example.pesaapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.pesaapp.data.More;
import com.example.pesaapp.Model.LandingModel;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pesaapp.data.Constants.PREFKEY;
import static com.example.pesaapp.data.Constants.PREFNAME;

public class LandingVM extends AndroidViewModel {
    private LandingModel landingModel;
    private MutableLiveData<List<More>> featuredlist ;
    private MutableLiveData<List<More>> searchlist;
    private MutableLiveData<List<More>> allList;
    private long phone;
    private MutableLiveData<List<More>> favlist;
    private List<More> resultlist;
    public List<More> getResultlist() {
        return resultlist;
    }
    public MutableLiveData<List<More>> getAllList() {
        return allList;
    }

    public LandingVM(@NonNull Application application) {
        super(application);
        SharedPreferences sharedPreferences = application.getSharedPreferences(PREFNAME,MODE_PRIVATE);
        phone =sharedPreferences.getLong(PREFKEY,0);
        Log.e("sam" ,"Shared preference is " + phone);
        landingModel= new LandingModel();
        landingModel.getFeatures();
        landingModel.getAll();
        landingModel.getFavorite(phone);

        featuredlist = landingModel.getFeaturedlist();
        searchlist = landingModel.getSearchlist();
        allList = landingModel.getAlllist();
        favlist = landingModel.getFavouritelist();
        resultlist = new ArrayList<>();

    }

    public MutableLiveData<List<More>> getFeaturedlist() {
        return featuredlist;
    }
    public void searchQuery(String search){
        landingModel.searchResults(search);
    }

    public MutableLiveData<List<More>> getSearchlist() {
        return searchlist;
    }

    public void setResult(List<More> searchlist) {
        this.resultlist =searchlist;
    }

    public void saveFavourite(String title) {
        landingModel.saveFavourite(phone ,title);
    }
    public MutableLiveData<List<More>> getFavlist() {
        return favlist;
    }
}
