package com.bipin.cellfinder.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by bips on 8/17/16.
 */
public class EnablePhoneDialog extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog builder=new AlertDialog.Builder(getActivity())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                        System.exit(0);
                    }
                })
                .create();

        builder.setTitle("Allow Phone Access");
        builder.setMessage("This app will not work without enabling the Phone access permission." +
                "You can go to app setting to enable it manually.");
        return builder;

    }


}
