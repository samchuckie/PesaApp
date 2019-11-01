package com.example.pesaapp;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pesaapp.data.More;
import com.example.pesaapp.data.moreparceble;
import com.example.pesaapp.databinding.ActivityEventsBinding;
import com.squareup.picasso.Picasso;
import static com.example.pesaapp.data.Constants.ACCOUNT;
import static com.example.pesaapp.data.Constants.EVENT_EXTRA;
import static com.example.pesaapp.data.Constants.LOCALHOSTIMAGES;
import static com.example.pesaapp.data.Constants.TOTAL_AMOUNT;

public class Event extends AppCompatActivity {
    ActivityEventsBinding activityEventsBinding;
    moreparceble more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventsBinding = DataBindingUtil.setContentView(this,R.layout.activity_events);

        activityEventsBinding.eProceed.setOnClickListener(e_proceed ->{
            int earlytickets = getquantity(activityEventsBinding.eEarlyQty)*more.getEarly_price();
            int latetickets  = getquantity(activityEventsBinding.eAdvanceQty)*more.getAdvance_price();
            int total = earlytickets +latetickets;
            Intent intent = new Intent(Event.this ,Payment.class);
            intent.putExtra(TOTAL_AMOUNT , total);
            intent.putExtra(ACCOUNT , more.getHost());
            startActivity(intent);
        });
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(EVENT_EXTRA)){
            more = intent.getParcelableExtra(EVENT_EXTRA);
            More mores = new More(more.getTitle(),more.getPhoto(),more.getStart_date(),more.getLocation()
                    ,more.getDescription(),more.getTime_from(),"df","dfdfd","dfsdsfd",
                    "dsfewef",more.getId(),111,220);
//            startActivity(intent);
            activityEventsBinding.setMore(mores);
//            updateUi(More);
        }
    }
//    @BindingAdapter("android:background")
//    public static void setImage(ImageView view, String photo){
//        Picasso.get().load(LOCALHOSTIMAGES + photo).into(view);
//
//    }

    private void updateUi(More more) {
//        Picasso.get().load(LOCALHOSTIMAGES+More.getPhoto()).into(title_photo);
//        e_title.setText(More.getTitle());
//        host.setText(More.getHost());
        //TODO Use string resources with placeholders
        String finaldate = more.eventdateformated() + ", " + more.getTime_from() + " - " + more.getTime_to();
//        date.setText(finaldate);
//        location.setText(More.getLocation());
//        description.setText(More.getDescription());
//        price_early.setText(String.valueOf(More.getEarly_price()));
        String closeson = more.favdateformated() + "@" + more.getClose_time();
//        early_close.setText(closeson );
//        advance_close.setText(closeson);
//        price_advance.setText(String.valueOf(More.getAdvance_price()));
    }

    public void add(View view) {
        if(view.getId()== R.id.early_add){
            activityEventsBinding.eEarlyQty.setText(String.valueOf(getquantity(activityEventsBinding.eEarlyQty) + 1));
        }
        else {
            activityEventsBinding.eAdvanceQty.setText(String.valueOf(getquantity( activityEventsBinding.eAdvanceQty) + 1));
        }
    }

    public void subtract(View view) {
        if(view.getId()== R.id.early_sub){
            if(getquantity(activityEventsBinding.eEarlyQty)>0) {
                activityEventsBinding.eEarlyQty.setText(String.valueOf(getquantity(activityEventsBinding.eEarlyQty) - 1));
            }
        }
        else {
            if(getquantity(activityEventsBinding.eAdvanceQty)>0) {
                activityEventsBinding.eAdvanceQty.setText(String.valueOf(getquantity( activityEventsBinding.eAdvanceQty) - 1));
            }
        }
    }
    private int getquantity(TextView textView){
        return Integer.valueOf(textView.getText().toString());
    }
}
