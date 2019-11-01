package com.example.pesaapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.Toast;
import com.example.pesaapp.Adapters.CategoriesAdapter;
import com.example.pesaapp.Adapters.FavAdapter;
import com.example.pesaapp.Adapters.FeaturedAdapter;
import com.example.pesaapp.Adapters.MoreAdapters;
import com.example.pesaapp.data.Categories;
import com.example.pesaapp.data.More;
import com.example.pesaapp.ViewModels.LandingVM;
import com.example.pesaapp.data.moreparceble;

import java.util.ArrayList;
import java.util.List;
import static com.example.pesaapp.data.Constants.CATEGORY_KEY;
import static com.example.pesaapp.data.Constants.EVENT_EXTRA;
import static com.example.pesaapp.data.Constants.FAVOURITES;
import static com.example.pesaapp.data.Constants.LOADALL;


public class Landing extends AppCompatActivity implements FeaturedAdapter.Itemclicked,
        CategoriesAdapter.CategInt, MoreAdapters.Itemclicked, MoreAdapters.HeartClicked, FavAdapter.Itemclicked {
    MoreAdapters moreAdapters;
    FeaturedAdapter featuredAdapter;
    FavAdapter favAdapter ;
    CategoriesAdapter categoriesAdapter;
    LandingVM landingVM;
    SearchView events_search;
    TextView favourite_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //TODO TRANSFER THE INTERFACE OUT TO PULBLIC. REUSE
        RecyclerView featured_rv = findViewById(R.id.featured_rv);
        RecyclerView more_rv = findViewById(R.id.more_rv);
        RecyclerView fav_rv = findViewById(R.id.fav_rv);
        RecyclerView categories_rv = findViewById(R.id.categories_rv);
        TextView see_all = findViewById(R.id.see_all);
        events_search = findViewById(R.id.events_search);
        Button load_all = findViewById(R.id.load_all);
        favourite_all = findViewById(R.id.my_favourite);

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
        favAdapter = new FavAdapter(this);
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

        //TODO TO LOAD ALL OF THE DATA NEST THE THREE CALLS INTO ONE.CALL THEM IN SEQUENCE AND THEN LOAD DATA AFTER ALL ARE COMPLETED
        landingVM = ViewModelProviders.of(this).get(LandingVM.class);
        landingVM.getFeaturedlist().observe(this ,featuredObserver -> featuredAdapter.setFeatured(featuredObserver));
        landingVM.getAllList().observe(this , allObserver -> moreAdapters.setAll(allObserver));
        landingVM.getFavlist().observe(this , favobserver ->{
            if(favobserver!=null){
                favAdapter.setFavourites(favobserver);
                String fav_title = getResources().getString(R.string.my_favourite) + "(" + favobserver.size() +")";
                favourite_all.setText(fav_title);
                Log.e("sam", "The for full title is " + fav_title);
            }
            else{
                //use string resources
                String fav_title = getResources().getString(R.string.my_favourite) + " (" + "0"+")";
                favourite_all.setText(fav_title);
                Log.e("sam", "The title is " + fav_title);
            }
        });

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

        //TODO FIRST CHANGE THE URL TO RETURN ONLY PHOTO TITLE ,DATE AND TITLE
        //TODO USE DIFFERENT URL FOR THOSE WITH EXTRA LIKE I THINK FAVOURITE

        moreparceble moreparceble = new moreparceble(more.getTitle(),more.getPhoto(),more.getStart_date(),more.getLocation()
        ,more.getDescription(),more.getTime_from(),"df","dfdfd","dfsdsfd",
                "dsfewef",more.getId(),1500,2000);
        intent.putExtra(EVENT_EXTRA, moreparceble);
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
        Toast.makeText(this ,"Added to favourites" ,Toast.LENGTH_SHORT).show();
        landingVM.saveFavourite(more.getTitle());

    }
}
