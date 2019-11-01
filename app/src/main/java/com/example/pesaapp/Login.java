package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pesaapp.data.PesaUsers;
import com.example.pesaapp.ViewModels.ValidVM;
import com.example.pesaapp.databinding.ActivityLoginBinding;
import static com.example.pesaapp.data.Constants.PREFKEY;
import static com.example.pesaapp.data.Constants.PREFNAME;


public class Login extends AppCompatActivity {
    ValidVM validVM;
    Long phonenumber;
    SharedPreferences sharedPreferences;
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences(PREFNAME ,MODE_PRIVATE);
        validVM = ViewModelProviders.of(this).get(ValidVM.class);
        validVM.getMutableLiveData().observe(this, code -> {
            Log.e("sam" , "The livedata response is" + code);
            if(code.equals(200)){
                userAuthenticated();
            }
            else{
                userNotAuth();
            }
        });

        //TODO CHECK DUPLICATION OF VIEWMODEL ALONE AND IN LOG AND SIGN

        activityLoginBinding.loginBtn.setOnClickListener(listener ->{
            String pass =  activityLoginBinding.password.getText().toString();
            if(TextUtils.isEmpty(activityLoginBinding.phoneNumber.getText().toString()) || TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Please input all the fields",Toast.LENGTH_SHORT).show();
            }
            else {
                phonenumber =  Long.parseLong(activityLoginBinding.phoneNumber.getText().toString());
                PesaUsers user = new PesaUsers(phonenumber,pass);
                validVM.login(user);
            }
        });

        activityLoginBinding.signupTv.setOnClickListener(signListener -> {
            startActivity(new Intent(this ,SignUp.class));
            finish();
        });

        activityLoginBinding.phoneNumber.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(activityLoginBinding.phoneLabel.getVisibility() == View.INVISIBLE)){
                animate(activityLoginBinding.phoneLabel);
            }
        });
        activityLoginBinding.password.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(activityLoginBinding.passwordLabel.getVisibility() ==View.INVISIBLE)){
                animate(activityLoginBinding.passwordLabel);
            }
        });
    }

    private void userAuthenticated() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(PREFKEY, phonenumber);
        // consider using commit as the next activity doesnt seem to recognize the preference in the background
        editor.apply();
        startActivity(new Intent(Login.this, Landing.class));
        finish();
    }

    private void userNotAuth() {
        Toast.makeText(this,"User is not registered",Toast.LENGTH_SHORT).show();
    }

    public static void animate(TextView label) {
        label.setAlpha(0f);
        label.setVisibility(View.VISIBLE);
        label.animate()
                .alpha(1f)
                .setDuration(1000);
    }
}
