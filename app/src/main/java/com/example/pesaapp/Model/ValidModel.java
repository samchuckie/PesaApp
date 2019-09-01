package com.example.pesaapp.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.pesaapp.Data.PesaUsers;
import com.example.pesaapp.Network.ValidInterface;
import com.example.pesaapp.Network.ValidRetro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ValidModel {
    private MutableLiveData<Integer> response_code ;
    private Retrofit validretro;
    private ValidInterface validInterface;

    public MutableLiveData<Integer> getResponse_code() {
        return response_code;
    }

    public ValidModel() {
        response_code = new MutableLiveData<>();
        validretro = ValidRetro.getValidRetro();
        validInterface = validretro.create(ValidInterface.class);
    }
    Callback<PesaUsers> callback = new Callback<PesaUsers>() {
        @Override
        public void onResponse(Call<PesaUsers> call, Response<PesaUsers> response) {
            Log.e("sam" , "The response code is " + response.code());
            response_code.setValue(response.code());
        }

        @Override
        public void onFailure(Call<PesaUsers> call, Throwable t) {
            Log.e("sam" ,"The error is" + t.getMessage());
            //message starts with failed to connect to seng code for internet noify user to turn on network
        }
    };

    public  void getLogIn(PesaUsers user) {
        Call<PesaUsers> login = validInterface.signin(user.getPhone(),user.getPassword());
        Log.e("sam","phone number is " + user.getPhone());
        Log.e("sam","password is " + user.getPassword());
        login.enqueue(callback);
    }

    public void signUpNew(PesaUsers user) {
        Call<PesaUsers> signup = validInterface.signup(user.getPhone(),user.getPassword(),user.getEmail());
        Log.e("sam","phone number is " + user.getPhone());
        Log.e("sam","password is " + user.getPassword());
        Log.e("sam","Email is " + user.getEmail());
        signup.enqueue(callback);

    }
}
