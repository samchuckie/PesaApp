package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.pesaapp.Adapters.CategoriesAdapter;
import com.example.pesaapp.Adapters.FavAdapter;
import com.example.pesaapp.Adapters.FeaturedAdapter;
import com.example.pesaapp.Adapters.MoreAdapters;
import com.example.pesaapp.Data.Categories;
import com.example.pesaapp.Model.LandingModel;
import com.example.pesaapp.ViewModels.LandingVM;

import java.util.ArrayList;
import java.util.List;

public class Landing extends AppCompatActivity {
    MoreAdapters moreAdapters;
    FeaturedAdapter featuredAdapter;
    FavAdapter favAdapter ;
    CategoriesAdapter categoriesAdapter;
    LandingVM landingVM;
    SearchView searchView;

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
        LinearLayoutManager catlinearLayout =new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);

        featured_rv.setLayoutManager(verticalLinear);
        more_rv.setLayoutManager(linearLayout);
        fav_rv.setLayoutManager(favlinearLayout);
        categories_rv.setLayoutManager(catlinearLayout);
        featuredAdapter = new FeaturedAdapter();
        moreAdapters =  new MoreAdapters();
        favAdapter = new FavAdapter();
        categoriesAdapter = new CategoriesAdapter();
        featured_rv.setAdapter(featuredAdapter);
        more_rv.setAdapter(moreAdapters);
        fav_rv.setAdapter(favAdapter);
        categories_rv.setAdapter(categoriesAdapter);

        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(new Categories("Music"));
        categoriesList.add(new Categories("Art"));
        categoriesList.add(new Categories("Gaming"));
        categoriesAdapter.addCategories(categoriesList);

        //TODO UNIT TESTING ON MORE CLASS THE DATE CLASS SETMETHODS TO RETURN AN ARRAY OF TWO ITEMS. DAY AND TIME

        landingVM = ViewModelProviders.of(this).get(LandingVM.class);
        landingVM.getFeaturedlist().observe(this ,featuredObserver -> {
            featuredAdapter.setFeatured(featuredObserver);
        });
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
