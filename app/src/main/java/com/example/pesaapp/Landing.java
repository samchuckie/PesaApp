package com.example.pesaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

import com.example.pesaapp.Adapters.CategoriesAdapter;
import com.example.pesaapp.Adapters.FavAdapter;
import com.example.pesaapp.Adapters.FeaturedAdapter;
import com.example.pesaapp.Adapters.MoreAdapters;

public class Landing extends AppCompatActivity {
    MoreAdapters moreAdapters;
    FeaturedAdapter featuredAdapter;
    FavAdapter favAdapter ;
    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        RecyclerView featured_rv = findViewById(R.id.featured_rv);
        RecyclerView more_rv = findViewById(R.id.more_rv);
        RecyclerView fav_rv = findViewById(R.id.fav_rv);
        RecyclerView categories_rv = findViewById(R.id.categories_rv);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        LinearLayoutManager verticalLinear =  new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager favlinearLayout = new LinearLayoutManager(this);
        LinearLayoutManager catlinearLayout = new LinearLayoutManager(this);
        featured_rv.setLayoutManager(verticalLinear);
        more_rv.setLayoutManager(linearLayout);
        fav_rv.setLayoutManager(favlinearLayout);
        featuredAdapter = new FeaturedAdapter();
        moreAdapters =  new MoreAdapters();
        favAdapter = new FavAdapter();
        categoriesAdapter = new CategoriesAdapter();
        featured_rv.setAdapter(featuredAdapter);
        more_rv.setAdapter(moreAdapters);
        fav_rv.setAdapter(favAdapter);
        categories_rv.setAdapter(categoriesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Landing.this, Login.class));
        finish();
    }
}
