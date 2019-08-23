package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.pesaapp.Adapters.MoreAdapters;
import com.example.pesaapp.Data.More;
import com.example.pesaapp.Model.AllAdapter;
import com.example.pesaapp.Model.ManyModel;
import com.example.pesaapp.ViewModels.ManyVM;

import static com.example.pesaapp.Data.Constants.CATEGORY_KEY;
import static com.example.pesaapp.Data.Constants.EVENT_EXTRA;

public class ManyEvents extends AppCompatActivity implements AllAdapter.Itemclicked {
    ManyVM manyVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_events);

        RecyclerView allevents_rv = findViewById(R.id.allevents_rv);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        allevents_rv.setLayoutManager(linearLayout);
        AllAdapter allAdapter = new AllAdapter(this);
        allevents_rv.setAdapter(allAdapter);
        manyVM = ViewModelProviders.of(this).get(ManyVM.class);

        //if categerory key or all events key from button

        manyVM.getMoreList().observe(this , listObserver-> allAdapter.setAll(listObserver));

        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(CATEGORY_KEY)){
            Log.e("sam","Intent has category extra");
            String category = intent.getStringExtra(CATEGORY_KEY);
            Log.e("sam" ,"The category is" + category);
            manyVM.getCategory(category);
        }
    }

    @Override
    public void eventClicked(More more) {
        Intent intent =  new Intent(ManyEvents.this ,Event.class);
        intent.putExtra(EVENT_EXTRA, more);
        startActivity(intent);

    }
}
