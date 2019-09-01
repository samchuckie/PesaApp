package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.example.pesaapp.Data.Constants.PREFNAME;
import static com.example.pesaapp.Login.animate;

public class SignUp extends AppCompatActivity {
    EditText sign_phone_number, sign_password,email_et;
    ValidVM validVM;
    SharedPreferences sharedPreferences;
    Long phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sharedPreferences = this.getSharedPreferences(PREFNAME,MODE_PRIVATE);



        //TODO SWITCH FROM TOAST TO DIALOG INFORMING USER TO GO BACK. ONCLICK GO TO SIGN UP


        sign_password= findViewById(R.id.sign_password);
        sign_phone_number= findViewById(R.id.sign_phone_number);
        email_et = findViewById(R.id.email);
        TextView already_tv = findViewById(R.id.already_tv);
        Button signup_btn = findViewById(R.id.signup_btn);
        TextView password_label = findViewById(R.id.password_label);
        TextView phone_label = findViewById(R.id.phone_label);
        TextView email_label = findViewById(R.id.email_label);
        validVM = ViewModelProviders.of(this).get(ValidVM.class);
        validVM.getMutableLiveData().observe(this, code -> {
            Log.e("sam" , "The livedata response is" + code);
            if(code.equals(200)){
                userRegistered();
            }
            else if(code.equals(650)){
                userExists();
                }
        });

        sign_phone_number.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(phone_label.getVisibility() == View.INVISIBLE)){
                animate(phone_label);
            }
        });
        sign_password.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(password_label.getVisibility() ==View.INVISIBLE)){
                animate(password_label);
            }
        });
        email_et.setOnFocusChangeListener((view,hasFocus) ->{
            if(hasFocus &&(email_label.getVisibility() ==View.INVISIBLE)){
                animate(email_label);
            }
        });
        already_tv.setOnClickListener(signListener -> {
            startActivity(new Intent(this ,Login.class));
            finish();
        });

        signup_btn.setOnClickListener(signListener -> {
            String pass =  sign_password.getText().toString();
            String email =  email_et.getText().toString();
            if(TextUtils.isEmpty(sign_phone_number.getText().toString()) || TextUtils.isEmpty(pass) ||TextUtils.isEmpty(email) ){
                Toast.makeText(this,"Please input all the fields",Toast.LENGTH_SHORT).show();
            }
            else {
                phonenumber =  Long.parseLong(sign_phone_number.getText().toString());
                PesaUsers user = new PesaUsers(phonenumber,pass,email);
                validVM.signUp(user);
            }
        });
    }
    private void userExists() {
        Toast.makeText(this,"User is already registered",Toast.LENGTH_SHORT).show();
    }

    private void userRegistered() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(PREFNAME, phonenumber);
        editor.apply();
        startActivity(new Intent(SignUp.this, Landing.class));
        finish();
    }
}