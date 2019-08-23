package com.example.pesaapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.pesaapp.Data.Categories;
import com.example.pesaapp.R;
import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<Categories> moreArrayList = new ArrayList<>();
    public CategInt categInt;

    public CategoriesAdapter(CategInt categInt) {
        this.categInt = categInt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_items ,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // SWITCH TO LOADING ALL THE VARIABLES IN THE CLASS AN SENDING IT HERE

        Categories categories = moreArrayList.get(i);
        String switcher = categories.getName();
        switch (switcher){
            case "Music":
                viewHolder.categImage.setBackgroundResource(R.drawable.ic_music_note_black_24dp);
                viewHolder.categLinear.setBackgroundResource(R.drawable.music_cat);
                viewHolder.categTitle.setText(switcher);
                break;
            case "Art":
                viewHolder.categImage.setBackgroundResource(R.drawable.ic_palette_black_24dp);
                viewHolder.categLinear.setBackgroundResource(R.drawable.art_cat);
                viewHolder.categTitle.setText(switcher);
                break;
            case "Gaming":
                viewHolder.categImage.setBackgroundResource(R.drawable.ic_gamepad_black_24dp);
                viewHolder.categLinear.setBackgroundResource(R.drawable.gaming_cat);
                viewHolder.categTitle.setText(switcher);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(moreArrayList.size()!=0){
            return moreArrayList.size();
        }
        return 0;
    }


    public void addCategories(List<Categories> categoriesList) {
        moreArrayList = categoriesList;
        notifyDataSetChanged();
    }
    public interface CategInt{
        void categoryCliked(String category);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categImage;
        LinearLayout categLinear;
        TextView categTitle;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            categImage =itemView.findViewById(R.id.categImage);
            categLinear = itemView.findViewById(R.id.categLinear);
            categTitle =itemView.findViewById(R.id.categTitle);
            itemView.setOnClickListener(categoryClicked ->{
                categInt.categoryCliked(moreArrayList.get(getAdapterPosition()).getName());
            });
        }
    }
}
