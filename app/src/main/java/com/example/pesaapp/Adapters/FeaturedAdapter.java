package com.example.pesaapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pesaapp.data.More;
import com.example.pesaapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import static com.example.pesaapp.data.Constants.LOCALHOSTIMAGES;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {
    private List<More> moreArrayList = new ArrayList<>();
    private Itemclicked itemclicked;

    public FeaturedAdapter(Itemclicked itemclicked) {
        this.itemclicked =itemclicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.featured_items ,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(LOCALHOSTIMAGES +  moreArrayList.get(i).getPhoto()).fit().into(viewHolder.imageView);
        viewHolder.more_title.setText(moreArrayList.get(i).getTitle());
        viewHolder.host_tv.setText(moreArrayList.get(i).getHost());
        viewHolder.month.setText(moreArrayList.get(i).dayconvertor());
        viewHolder.day.setText(moreArrayList.get(i).dateformat()[2]);
    }


    @Override
    public int getItemCount() {
        if(moreArrayList.size()!=0){
            return moreArrayList.size();
        }
        return 0;
    }

    public void setFeatured(List<More> featuredObserver) {
        moreArrayList =featuredObserver;
        notifyDataSetChanged();
    }
    public interface Itemclicked{
        void eventClicked(More more);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView more_title ,host_tv, month ,day;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            more_title = itemView.findViewById(R.id.more_title);
            host_tv = itemView.findViewById(R.id.host_tv);
            month = itemView.findViewById(R.id.month);
            day = itemView.findViewById(R.id.day);
            itemView.setOnClickListener(clicked -> itemclicked.eventClicked(moreArrayList.get(getAdapterPosition())));

        }
    }
}
