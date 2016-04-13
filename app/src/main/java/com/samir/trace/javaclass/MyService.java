package com.samir.trace.javaclass;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;


/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */

/*
Cell-ID
        A unique number used to identify the GSM base transceiver station the phone is connected to.
 MNC - Mobile Network Code
        This number is used to uniquely identify the GSM network operator in combination with the MCC
        the phone is currently connected to.
        00 - BT
        01 - UK01
        02 - O2 Telefonica
        03 - Airtel-Vodafone
 MCC - Mobile Country Code
        This is a unique number for the country with the GSM net the phone is currently connected to.
        235 - United Kingdom
        262 - Germany
        208 - France
        214 - Spain
LAC - Location Area Code
        A 16-digit fixed length code that identifies the phone's location area within a GSM public
         land mobile network.
*/
public class MyService extends Service {
    SharedPreferences sharedPreferences;
    String senderEmail;
    String emailPassword;
    String receiverEmail;
    GMailSender sender;
    String emailText;
    String emailSubject;
    String currentSimSerial;

    public static final String MyPreferences = "secure";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //this is called on when service is instantiated
    @Override
    public void onCreate() {
        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);


    }

    //onStartCommand is called after object of service is created and this
    //function helps in sending mail .
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        senderEmail = sharedPreferences.getString("sendingEmail", null);
        emailPassword = sharedPreferences.getString("emailPassword", null);
        receiverEmail = sharedPreferences.getString("email", null);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
        String networkOperator = tm.getNetworkOperator();//get information from network operator
        currentSimSerial = tm.getSimSerialNumber();


        Integer lac;
        Integer cid;
String mcc;
        String mnc;
        if(location==null) stopSelf();
        lac = (location == null ? 0 : location.getLac());
        cid = (location == null ? 0 : location.getCid());
        mcc=(networkOperator.isEmpty()?"":networkOperator.substring(0,3));
        mnc=(networkOperator.isEmpty()?"":networkOperator.substring(3));
        emailText = "Caution! SIM card has been replaced in your Smartphone. Please, verify. " +
                "Replaced SIM serial:" + currentSimSerial + " Network Operator: "
                + networkOperator + " Location Area code:" +lac +
                " Cellular ID:" + cid +
                " MCC:" + mcc + " MNC:" + mnc;

        emailSubject = "New Sim inserted in your phone";

        sender = new GMailSender(senderEmail.toString(), emailPassword.toString());//email and password that is sender
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
                Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            new MyAsyncClass().execute();//this called Async class to send email
        } catch (Exception ex) {

        }
        stopSelf();//service is stopped as soon as this function is encountered
        return START_STICKY;
    }

    //AsyncTask is a class that runs in background. We send email in background process.
    //So, this class is used for sending email.
    class MyAsyncClass extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                //this is the function that sends email
                sender.sendMail(emailSubject, emailText, senderEmail.toString(), receiverEmail.toString());
            } catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }


    }
}
