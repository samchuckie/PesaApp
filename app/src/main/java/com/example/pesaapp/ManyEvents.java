package com.example.pesaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.pesaapp.Data.Constants.CATEGORY_KEY;

public class ManyEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_events);

        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(CATEGORY_KEY)){
            Log.e("sam","Intent has category extra");
        }
    }
}
