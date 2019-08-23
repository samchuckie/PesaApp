package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
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
import static com.example.pesaapp.Data.Constants.FAVOURITES;
import static com.example.pesaapp.Data.Constants.LOADALL;
import static com.example.pesaapp.Data.Constants.PREFKEY;
import static com.example.pesaapp.Data.Constants.PREFNAME;

public class Landing extends AppCompatActivity implements FeaturedAdapter.Itemclicked, CategoriesAdapter.CategInt, MoreAdapters.Itemclicked, MoreAdapters.HeartClicked {
    MoreAdapters moreAdapters;
    FeaturedAdapter featuredAdapter;
    FavAdapter favAdapter ;
    CategoriesAdapter categoriesAdapter;
    LandingVM landingVM;
    SearchView events_search;
    long phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //TODO TRANSFER THE INTERFACE OUT TO PULBLIC. REUSE
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFNAME,MODE_PRIVATE);
         phone =sharedPreferences.getLong(PREFKEY,0);
        Log.e("sam" ,"Shared preference is " + phone);

        RecyclerView featured_rv = findViewById(R.id.featured_rv);
        RecyclerView more_rv = findViewById(R.id.more_rv);
        RecyclerView fav_rv = findViewById(R.id.fav_rv);
        RecyclerView categories_rv = findViewById(R.id.categories_rv);
        TextView see_all = findViewById(R.id.see_all);
        events_search = findViewById(R.id.events_search);
        Button load_all = findViewById(R.id.load_all);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        LinearLayoutManager verticalLinear =  new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager favlinearLayout = new LinearLayoutManager(this);
        LinearLayoutManager catlinearLayout =new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);

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
        landingVM.getFavourite(phone);
        landingVM.getFeaturedlist().observe(this ,featuredObserver -> featuredAdapter.setFeatured(featuredObserver));
        landingVM.getAllList().observe(this , allObserver -> moreAdapters.setAll(allObserver));
        landingVM.getFavlist().observe(this , favobserver ->favAdapter.setFavourites(favobserver));

        events_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                landingVM.searchQuery(s);
                landingVM.getSearchlist().observe(Landing.this, searchlist ->{
                    if(searchlist!=null){
                        //SO I WILL RETURN THE FIRST ITEM IN LIST ONLY FOR USER TO SEE
                        landingVM.setResult(searchlist);
                        SearchDialog searchDialog = new SearchDialog();
                        searchDialog.show(getSupportFragmentManager(),"Good");
                    }
                    else{
                        Log.e("sam","The searchlist is empty ");
                    }
                });
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        load_all.setOnClickListener(clicked -> {
            Intent intent = new Intent(Landing.this , ManyEvents.class);
            intent.putExtra(LOADALL , "load");
            startActivity(intent);
        });
        see_all.setOnClickListener(seefav ->{
            Intent intent = new Intent(Landing.this , ManyEvents.class);
            intent.putExtra(FAVOURITES , "fav");
            startActivity(intent);
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
        landingVM.saveFavourite(phone ,more);

    }
}
