package com.example.pesaapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static com.example.pesaapp.Data.Constants.LOCALHOST;
import static com.example.pesaapp.Data.Constants.LOCALHOSTIMAGES;

public class MoreAdapters  extends RecyclerView.Adapter<MoreAdapters.ViewHolder> {
    private List<More> moreArrayList = new ArrayList<>();
    private Itemclicked itemclicked;
    private HeartClicked heartClicked;

    public MoreAdapters(Itemclicked itemclicked ,HeartClicked heartClicked) {
        this.itemclicked =itemclicked;
        this.heartClicked = heartClicked;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.more_items ,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(LOCALHOSTIMAGES +  moreArrayList.get(i).getPhoto()).into(viewHolder.imageView);
        Log.e("sam","Url path is " + LOCALHOST +"loadImages"  +  moreArrayList.get(i).getPhoto());
        viewHolder.more_title.setText(moreArrayList.get(i).getTitle());
        viewHolder.host_tv.setText(moreArrayList.get(i).getHost());
        viewHolder.month.setText(moreArrayList.get(i).getStart_date());
        viewHolder.day.setText(moreArrayList.get(i).getStart_date());
    }

    @Override
    public int getItemCount() {
        if(moreArrayList.size()!=0){
            return moreArrayList.size();
        }
        return 0;
    }

    public void setAll(List<More> allObserver) {
        moreArrayList = allObserver;
        notifyDataSetChanged();
    }
    public interface Itemclicked{
        void eventClicked(More more);
    }
    public interface HeartClicked{
        void voiHeartclicked(More more);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,favourite_heart;
        TextView more_title ,host_tv, month ,day;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            favourite_heart = itemView.findViewById(R.id.favourite_heart);
            more_title = itemView.findViewById(R.id.more_title);
            host_tv = itemView.findViewById(R.id.host_tv);
            month = itemView.findViewById(R.id.month);
            day = itemView.findViewById(R.id.day);
            itemView.setOnClickListener(clicked ->{
                itemclicked.eventClicked(moreArrayList.get(getAdapterPosition()));
            });
            favourite_heart.setOnClickListener(heartlistener -> {

            });
        }
    }
}
