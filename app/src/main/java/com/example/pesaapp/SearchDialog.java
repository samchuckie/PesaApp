package com.example.pesaapp;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.pesaapp.data.More;
import com.example.pesaapp.ViewModels.LandingVM;
import com.example.pesaapp.data.moreparceble;

import static com.example.pesaapp.data.Constants.EVENT_EXTRA;

public class SearchDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LandingVM landingVM = ViewModelProviders.of(getActivity()).get(LandingVM.class);
        More more =landingVM.getResultlist().get(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(more.getTitle())
                .setTitle("Result found")
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    Intent intent =  new Intent(getActivity() ,Event.class);
                    moreparceble moreparceble = new moreparceble(more.getTitle(),more.getPhoto(),more.getStart_date(),more.getLocation()
                            ,more.getDescription(),more.getTime_from(),"df","dfdfd","dfsdsfd",
                            "dsfewef",more.getId(),1500,2000);
                    intent.putExtra(EVENT_EXTRA, moreparceble);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                });
        return builder.create();
    }
}
