package com.example.pesaapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.pesaapp.Data.PesaUsers;
import com.example.pesaapp.Model.ValidModel;

public class ValidVM extends AndroidViewModel {
    private ValidModel validModel;
    private MutableLiveData<Integer> mutableLiveData;

    public MutableLiveData<Integer> getMutableLiveData() {
        return mutableLiveData;
    }

    public ValidVM(@NonNull Application application) {
        super(application);
        Log.e("Sam","Valid vm created");
        validModel = new ValidModel();
        mutableLiveData = validModel.getResponse_code();

    }

    public void login(PesaUsers user) {
        validModel.getLogIn(user);
    }

    public void signUp(PesaUsers user) {
        validModel.signUpNew(user);
    }
}
