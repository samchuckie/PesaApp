package com.example.pesaapp;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import com.example.pesaapp.Adapters.CategoriesAdapter;
import com.example.pesaapp.Adapters.FavAdapter;
import com.example.pesaapp.Adapters.FeaturedAdapter;
import com.example.pesaapp.Adapters.MoreAdapters;
import com.example.pesaapp.Data.Categories;
import com.example.pesaapp.Data.More;
import com.example.pesaapp.ViewModels.LandingVM;
import java.util.ArrayList;
import java.util.List;

import static com.example.pesaapp.Data.Constants.CATEGORY_KEY;
import static com.example.pesaapp.Data.Constants.EVENT_EXTRA;

public class Landing extends AppCompatActivity implements FeaturedAdapter.Itemclicked, CategoriesAdapter.CategInt, MoreAdapters.Itemclicked, MoreAdapters.HeartClicked {
    MoreAdapters moreAdapters;
    FeaturedAdapter featuredAdapter;
    FavAdapter favAdapter ;
    CategoriesAdapter categoriesAdapter;
    LandingVM landingVM;
    SearchView events_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //TODO TRANSFER THE INTERFACE OUT TO PULBLIC. REUSE

        RecyclerView featured_rv = findViewById(R.id.featured_rv);
        RecyclerView more_rv = findViewById(R.id.more_rv);
        RecyclerView fav_rv = findViewById(R.id.fav_rv);
        RecyclerView categories_rv = findViewById(R.id.categories_rv);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        LinearLayoutManager verticalLinear =  new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager favlinearLayout = new LinearLayoutManager(this);
        LinearLayoutManager catlinearLayout =new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);
        events_search = findViewById(R.id.events_search);

        featured_rv.setLayoutManager(verticalLinear);
        more_rv.setLayoutManager(linearLayout);
        fav_rv.setLayoutManager(favlinearLayout);
        categories_rv.setLayoutManager(catlinearLayout);
        featuredAdapter = new FeaturedAdapter(this);
        moreAdapters =  new MoreAdapters(this,this);
        favAdapter = new FavAdapter();
        categoriesAdapter = new CategoriesAdapter(this);
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

        landingVM.getAllList().observe(this , allObserver ->{
            moreAdapters.setAll(allObserver);
        });

        events_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                landingVM.searchQuery(s);
                landingVM.getSearchlist().observe((LifecycleOwner) getApplication(), searchlist ->{
                });
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
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

    @Override
    public void eventClicked(More more) {
        Intent intent =  new Intent(Landing.this ,Event.class);
        intent.putExtra(EVENT_EXTRA, more);
        startActivity(intent);
    }

    @Override
    public void categoryCliked(String category) {
        Intent intent = new Intent(Landing.this , ManyEvents.class);
        intent.putExtra(CATEGORY_KEY , category);
        startActivity(intent);
    }

    @Override
    public void clicked(More more) {

    }
}
