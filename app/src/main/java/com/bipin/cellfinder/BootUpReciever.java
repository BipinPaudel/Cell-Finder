package com.bipin.cellfinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.bipin.cellfinder.javaclass.MessageService;
import com.bipin.cellfinder.javaclass.MyService;

//


/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */

//this class is called broadcast receiver
// It listens the action that happens in the background and executes this
//We call message and email service from this broadcastreceiver
public class BootUpReciever extends BroadcastReceiver {


    String email;
    String currentSimSerial;
    SharedPreferences sharedPreferences;


    public static final String MyPreferences = "secure";

    //this is the main function that is called when
    //broadcastreceiver is active
    @Override
    public void onReceive(Context context, Intent intent) {


        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        sharedPreferences = context.getSharedPreferences(MyPreferences, 0);
        String storedSimSerial = sharedPreferences.getString("simSerial", null);
        String phoneNumbertoSend = sharedPreferences.getString("number", null);


        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        currentSimSerial = tm.getSimSerialNumber();

        email = sharedPreferences.getString("email", null);

        if (email == null) return;
        Log.d("Stored Sim Serial::", storedSimSerial);
        Log.d("Current Sim Serial", "::" + currentSimSerial);
        Log.d("goose", "goose");
        //this checks if there is sim card or not.
        // if there is no sim card function will return
        if (tm.getSimSerialNumber() == null) return;

//this will check if previous stored sim serial is equal to current sim serial
        //if it is equal, it does nothing and simply returns.
        //else it will call message service and email service
        if (tm.getSimSerialNumber().toString().equals(storedSimSerial.toString())) {
            return;
        } else {
            //checks if there is internet connection
            if (activeNetInfo != null) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                //this will check if the internet connection is through wifi or mobile data
                if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI ||
                        activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                    try {
                        Log.d("above that", "");
                        //this intent will call emailservice called MyService class to send email
                        Intent intent1 = new Intent(context, MyService.class);
                        context.startService(intent1);
                    } catch (Exception ex) {
                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            }
            Log.d("Inside different", "::" + "");

            Log.d("msg gayo", "gayo");
//this intent helps in calling Messageservice class to send message
            Intent intent2 = new Intent(context, MessageService.class);

            intent2.putExtra("phoneNumber", phoneNumbertoSend);
            context.startService(intent2);
            return;
        }

    }


}