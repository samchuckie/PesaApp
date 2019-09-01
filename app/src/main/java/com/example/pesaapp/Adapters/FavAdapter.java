package com.example.pesaapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pesaapp.Data.More;
import com.example.pesaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.pesaapp.Data.Constants.LOCALHOSTIMAGES;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    private List<More> moreArrayList = new ArrayList<>();
    private Itemclicked itemclicked;

    public FavAdapter(Itemclicked itemclicked) {
        this.itemclicked =itemclicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_items ,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(LOCALHOSTIMAGES +  moreArrayList.get(i).getPhoto()).fit().into(viewHolder.fav_image);
        viewHolder.fav_date.setText(moreArrayList.get(i).favdateformated());
        viewHolder.fav_title.setText(moreArrayList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        if(moreArrayList!=null){
            if( moreArrayList.size()!=0) {
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
        ImageView fav_image;
        TextView fav_title,fav_date;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fav_image =  itemView.findViewById(R.id.fav_image);
            fav_title =  itemView.findViewById(R.id.fav_title);
            fav_date =  itemView.findViewById(R.id.fav_date);
            itemView.setOnClickListener(clicked -> itemclicked.eventClicked(moreArrayList.get(getAdapterPosition())));



        }
    }
}
