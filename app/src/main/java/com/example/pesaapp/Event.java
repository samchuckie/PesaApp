package com.example.pesaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pesaapp.Data.More;
import com.squareup.picasso.Picasso;
import static com.example.pesaapp.Data.Constants.ACCOUNT;
import static com.example.pesaapp.Data.Constants.EVENT_EXTRA;
import static com.example.pesaapp.Data.Constants.LOCALHOSTIMAGES;
import static com.example.pesaapp.Data.Constants.TOTAL_AMOUNT;

public class Event extends AppCompatActivity {
    ImageView title_photo;
    TextView e_title ,host,date,location, description ,price_early,early_quantity
            ,early_close,advance_qty,advance_close,price_advance;
    Button e_proceed;
    More more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        // TODO use databanding .Too many elements. Databinding and picasso issue

        title_photo = findViewById(R.id.title_photo);
        e_title = findViewById(R.id.e_title);
        host = findViewById(R.id.e_host);
        date = findViewById(R.id.e_date);
        location = findViewById(R.id.e_location);
        description = findViewById(R.id.e_description);
        price_early = findViewById(R.id.e_early);
        early_quantity = findViewById(R.id.e_early_qty);
        early_close = findViewById(R.id.e_early_close);
        advance_qty = findViewById(R.id.e_advance_qty);
        advance_close = findViewById(R.id.e_early_advance);
        price_advance = findViewById(R.id.e_advance);
        e_proceed = findViewById(R.id.e_proceed);
        e_proceed.setOnClickListener(e_proceed ->{
            int earlytickets = getquantity(early_quantity)*more.getEarly_price();
            int latetickets  = getquantity(advance_qty)*more.getAdvance_price();
            int total = earlytickets +latetickets;
            Intent intent = new Intent(Event.this ,Payment.class);
            intent.putExtra(TOTAL_AMOUNT , total);
            intent.putExtra(ACCOUNT , more.getHost());
            startActivity(intent);
        });
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(EVENT_EXTRA)){
            more = intent.getParcelableExtra(EVENT_EXTRA);
            updateUi(more);
        }
    }

    private void updateUi(More more) {
        Picasso.get().load(LOCALHOSTIMAGES+more.getPhoto()).into(title_photo);
        e_title.setText(more.getTitle());
        host.setText(more.getHost());
        //TODO Use string resources with placeholders
        String finaldate = more.eventdateformated() + ", " + more.getTime_from() + " - " + more.getTime_to();
        date.setText(finaldate);
        location.setText(more.getLocation());
        description.setText(more.getDescription());
        price_early.setText(String.valueOf(more.getEarly_price()));
        String closeson = more.favdateformated() + "@" + more.getClose_time();
        early_close.setText(closeson );
        advance_close.setText(closeson);
        price_advance.setText(String.valueOf(more.getAdvance_price()));
    }

    public void add(View view) {
        if(view.getId()== R.id.early_add){
            int quantity = getquantity(early_quantity) + 1;
            early_quantity.setText(String.valueOf(quantity));

        }
        else {
            int quantity = getquantity(advance_qty) + 1;
            advance_qty.setText(String.valueOf(quantity));
        }
    }

    public void subtract(View view) {
        if(view.getId()== R.id.early_sub){
            if(getquantity(early_quantity)>0) {
                int quantity = getquantity(early_quantity) - 1;
                early_quantity.setText(String.valueOf(quantity));
            }
        }
        else {
            if(getquantity(advance_qty)>0) {
                int quantity = getquantity(advance_qty) - 1;
                advance_qty.setText(String.valueOf(quantity));
            }
        }
    }
    private int getquantity(TextView textView){
        return Integer.valueOf(textView.getText().toString());
    }
}
