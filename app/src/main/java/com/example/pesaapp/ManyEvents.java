package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pesaapp.Adapters.MoreAdapters;
import com.example.pesaapp.Data.More;
import com.example.pesaapp.Adapters.AllAdapter;
import com.example.pesaapp.ViewModels.ManyVM;

import static com.example.pesaapp.Data.Constants.CATEGORY_KEY;
import static com.example.pesaapp.Data.Constants.EVENT_EXTRA;
import static com.example.pesaapp.Data.Constants.FAVOURITES;
import static com.example.pesaapp.Data.Constants.LOADALL;

public class ManyEvents extends AppCompatActivity implements AllAdapter.Itemclicked, MoreAdapters.HeartClicked {
    ManyVM manyVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_events);

        RecyclerView allevents_rv = findViewById(R.id.allevents_rv);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        allevents_rv.setLayoutManager(linearLayout);
        AllAdapter allAdapter = new AllAdapter(this,this);
        allevents_rv.setAdapter(allAdapter);
        manyVM = ViewModelProviders.of(this).get(ManyVM.class);

        manyVM.getMoreList().observe(this , allevents->{
            allAdapter.setAll(allevents,false);
        });
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(CATEGORY_KEY)){
            String category = intent.getStringExtra(CATEGORY_KEY);
            manyVM.getCategory(category);
        }
        if(intent!=null && intent.hasExtra(LOADALL)){
            manyVM.getAll();
        }
        if(intent!=null && intent.hasExtra(FAVOURITES)){
            manyVM.getFavourite();
        }
    }

    @Override
    public void eventClicked(More more) {
        Intent intent =  new Intent(ManyEvents.this ,Event.class);
        intent.putExtra(EVENT_EXTRA, more);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ManyEvents.this , Landing.class));
        finish();
    }

    @Override
    public void clicked(More more) {
        Toast.makeText(this ,"Added to favourites" ,Toast.LENGTH_SHORT).show();
        manyVM.saveFavourite(more.getTitle());
    }
}
