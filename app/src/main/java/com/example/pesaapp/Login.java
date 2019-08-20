package com.example.pesaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText phone_number, password;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_number= findViewById(R.id.phone_number);
        password= findViewById(R.id.password);
        TextView signup_tv = findViewById(R.id.signup_tv);
        Button login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(listener ->{
            int phonenumber =  Integer.parseInt(phone_number.getText().toString());
            String pass =  password.getText().toString();


        });

        signup_tv.setOnClickListener(signListener ->{
            startActivity(new Intent(this ,SignUp.class));
        });

    }
}
