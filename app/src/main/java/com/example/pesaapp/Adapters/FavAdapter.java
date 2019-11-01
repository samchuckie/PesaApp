package com.example.pesaapp.Adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.pesaapp.data.More;
import com.example.pesaapp.R;
import com.example.pesaapp.databinding.FavItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.pesaapp.data.Constants.LOCALHOSTIMAGES;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    private List<More> moreArrayList = new ArrayList<>();
    private Itemclicked itemclicked;

    public FavAdapter(Itemclicked itemclicked) {
        this.itemclicked =itemclicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FavItemsBinding favItemsBinding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.fav_items,viewGroup,false);
        return new ViewHolder(favItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.favItemsBinding.setMore(moreArrayList.get(i));

        //TODO impliment binding adapter for background.
        Picasso.get().load(LOCALHOSTIMAGES +  moreArrayList.get(i).getPhoto()).fit().into(viewHolder.favItemsBinding.favImage);


        
//        viewHolder.fav_date.setText(moreArrayList.get(i).favdateformated());
//        viewHolder.fav_title.setText(moreArrayList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        if(moreArrayList!=null){
            if( moreArrayList.size()!= 0) {
                if (moreArrayList.size() > 3) {
                    return 3;
                }
            }
            return moreArrayList.size();
        }
        return 0;
    }

    public void setFavourites(List<More> favobserver) {
        moreArrayList = favobserver;
        notifyDataSetChanged();
    }
    public interface Itemclicked{
        void eventClicked(More more);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        FavItemsBinding favItemsBinding;

        ViewHolder(@NonNull FavItemsBinding itemView) {
            super(itemView.getRoot());
            favItemsBinding = itemView;
            itemView.getRoot().setOnClickListener(clicked -> itemclicked.eventClicked(moreArrayList.get(getAdapterPosition())));



        }
    }
}
