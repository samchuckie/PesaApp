package com.example.pesaapp;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pesaapp.Data.More;

import static com.example.pesaapp.Data.Constants.EVENT_EXTRA;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);

        //use databanding . too many elements 
//        appBarLayout

        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(EVENT_EXTRA)){
            Log.e("sam" , "The intent has an event extra");
//            More more =  intent
        }
    }
}
