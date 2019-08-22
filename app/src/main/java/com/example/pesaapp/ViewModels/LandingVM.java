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

    public LandingVM(@NonNull Application application) {
        super(application);
        landingModel= new LandingModel();
        landingModel.getFeatures();
        featuredlist = landingModel.getFeaturedlist();
    }

    public MutableLiveData<List<More>> getFeaturedlist() {
        return featuredlist;
    }
}
