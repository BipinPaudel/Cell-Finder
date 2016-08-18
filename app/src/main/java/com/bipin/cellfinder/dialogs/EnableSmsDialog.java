package com.bipin.cellfinder.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by bips on 8/17/16.
 */
public class EnableSmsDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog builder=new AlertDialog.Builder(getActivity())
                .setPositiveButton("Ok",null)
                .create();

        builder.setTitle("Enable SMS and Location Permission");
        builder.setMessage("This application will not work without SMS and Location Permission. You can grant the " +
                "permission from app setting manually");
        return builder;
    }
}
