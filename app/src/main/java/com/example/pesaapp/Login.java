package com.example.pesaapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pesaapp.Data.PesaUsers;
import com.example.pesaapp.ViewModels.ValidVM;


public class Login extends AppCompatActivity {
    ValidVM validVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText phone_number, password;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_number= findViewById(R.id.phone_number);
        password= findViewById(R.id.password);
        TextView signup_tv = findViewById(R.id.signup_tv);
        Button login_btn = findViewById(R.id.login_btn);
        TextView password_label = findViewById(R.id.password_label);
        TextView phone_label = findViewById(R.id.phone_label);
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

        login_btn.setOnClickListener(listener ->{
            String pass =  password.getText().toString();
            if(TextUtils.isEmpty(phone_number.getText().toString()) || TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Please input all the fields",Toast.LENGTH_SHORT).show();
            }
            else {
                Long phonenumber =  Long.parseLong(phone_number.getText().toString());
                PesaUsers user = new PesaUsers(phonenumber,pass);
                validVM.login(user);

            }
        });

        signup_tv.setOnClickListener(signListener -> {
            startActivity(new Intent(this ,SignUp.class));
            finish();
        });

        phone_number.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(phone_label.getVisibility() == View.INVISIBLE)){
                animate(phone_label);
            }
        });
        password.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(password_label.getVisibility() ==View.INVISIBLE)){
                animate(password_label);
            }
        });
    }

    private void userAuthenticated() {
        startActivity(new Intent(Login.this, Landing.class));
        //STORE PREFERENCE TO SKIP LOGIN PART
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
