package com.example.pesaapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.pesaapp.Data.Constants.ACCOUNT;
import static com.example.pesaapp.Data.Constants.TOTAL_AMOUNT;

public class Payment extends AppCompatActivity {
    String account_name;
    int amount;
    boolean mpesa;
    boolean card;
    Drawable drawable,icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mpesa = false ;
        card = true;
        drawable= getResources().getDrawable(R.drawable.payment_elected);
        icon= getResources().getDrawable(R.drawable.ic_check_circle_black_24dp);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(TOTAL_AMOUNT) && intent.hasExtra(ACCOUNT)){
            account_name = intent.getStringExtra(ACCOUNT);
            amount = intent.getIntExtra(TOTAL_AMOUNT , 0);
            TextView account = findViewById(R.id.account_tv);
            TextView price = findViewById(R.id.price_pay);
            account.setText(account_name);
            price.setText(String.valueOf(amount));
        }

        //MPESA TO BE DONE THROUGH DARAJA API

        Button btn_card = findViewById(R.id.card);
        Button btn_mpesa = findViewById(R.id.mpesa);

        btn_card.setOnClickListener( btn_cardlistener ->{
            if(card){
                mpesa = true;
                card=false;
                btn_card.setBackground(drawable);
                btn_card.setCompoundDrawablesWithIntrinsicBounds(icon,null,null,null);
                btn_mpesa.setBackgroundResource(0);
                btn_mpesa.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);

            }
        });
        btn_mpesa.setOnClickListener( mpesa_cardlistener ->{
            if(mpesa){
                card = true;
                mpesa= false;
                btn_mpesa.setBackground(drawable);
                btn_mpesa.setCompoundDrawablesWithIntrinsicBounds(icon,null,null,null);
                btn_card.setBackgroundResource(0);
                btn_card.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);            }
        });

    }
}
