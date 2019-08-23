package com.example.pesaapp;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.pesaapp.Data.More;
import com.example.pesaapp.ViewModels.LandingVM;

import static com.example.pesaapp.Data.Constants.EVENT_EXTRA;

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
                    intent.putExtra(EVENT_EXTRA, more);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                });
        return builder.create();
    }
}
