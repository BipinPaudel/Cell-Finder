package com.samir.trace;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;

import com.samir.trace.javaclass.MessageService;
import com.samir.trace.javaclass.MyService;

//


/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */

public class BootUpReciever extends BroadcastReceiver {


    String email;
    String currentSimSerial;
    SharedPreferences sharedPreferences;


    public static final String MyPreferences = "secure";

    @Override
    public void onReceive(Context context, Intent intent) {


        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();


        sharedPreferences = context.getSharedPreferences(MyPreferences, 0);
        String storedSimSerial = sharedPreferences.getString("simSerial", null);
        String phoneNumbertoSend = sharedPreferences.getString("number", null);


        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
        String networkOperator = tm.getNetworkOperator();
        currentSimSerial = tm.getSimSerialNumber();

        email = sharedPreferences.getString("email", null);

        if (email == null) return;
        Log.d("Stored Sim Serial::", storedSimSerial);
        Log.d("Current Sim Serial", "::" + currentSimSerial);
        Log.d("goo", "goo");
        if (tm.getSimSerialNumber() == null) return;


        if (tm.getSimSerialNumber().toString().equals(storedSimSerial.toString())) {
            return;
        } else {
            if (activeNetInfo != null) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI ||
                        activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                    try {
                        Log.d("above that", "");
                        Intent intent1 = new Intent(context, MyService.class);
                        context.startService(intent1);
                    } catch (Exception ex) {
                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            }
            Log.d("Inside different", "::" + "");

            Log.d("msg gayo", "gayo");

            Intent intent2 = new Intent(context, MessageService.class);

            intent2.putExtra("phoneNumber", phoneNumbertoSend);
            context.startService(intent2);
            return;
        }

    }


}