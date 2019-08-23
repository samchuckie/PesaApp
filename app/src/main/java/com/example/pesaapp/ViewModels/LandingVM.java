package com.example.pesaapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.pesaapp.Data.More;
import com.example.pesaapp.Model.LandingModel;

import java.util.List;

public class LandingVM extends AndroidViewModel {
    private LandingModel landingModel;
    private MutableLiveData<List<More>> featuredlist ;
    private MutableLiveData<List<More>> searchlist;
    private MutableLiveData<List<More>> allList;

    public MutableLiveData<List<More>> getAllList() {
        return allList;
    }

    public LandingVM(@NonNull Application application) {
        super(application);

        landingModel= new LandingModel();
        landingModel.getFeatures();
        landingModel.getAll();

        featuredlist = landingModel.getFeaturedlist();
        searchlist = landingModel.getSearchlist();
        allList = landingModel.getAlllist();

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
}
