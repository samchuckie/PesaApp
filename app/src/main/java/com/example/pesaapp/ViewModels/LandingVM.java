package com.example.pesaapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.pesaapp.Data.More;
import com.example.pesaapp.Model.LandingModel;
import java.util.ArrayList;
import java.util.List;

public class LandingVM extends AndroidViewModel {
    private LandingModel landingModel;
    private MutableLiveData<List<More>> featuredlist ;
    private MutableLiveData<List<More>> searchlist;
    private MutableLiveData<List<More>> allList;
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

        landingModel= new LandingModel();
        landingModel.getFeatures();
        landingModel.getAll();

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

    public void saveFavourite(long phone, More more) {
        landingModel.saveFavourite(phone ,more);
    }

    public void getFavourite(long phone) {
        landingModel.getFavorite(phone);

    }

    public MutableLiveData<List<More>> getFavlist() {
        return favlist;
    }
}
