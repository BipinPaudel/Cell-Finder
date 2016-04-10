package com.bipin.trace.javaclass;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;


/**
 * Created by bips on 4/10/16.
 */
public class MyService extends Service{
    Context context;
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

    @Override
    public void onCreate() {
        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        senderEmail=sharedPreferences.getString("sendingEmail",null);
        emailPassword=sharedPreferences.getString("emailPassword",null);
        receiverEmail=sharedPreferences.getString("email",null);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        currentSimSerial = tm.getSimSerialNumber();
        emailText="Sim card has changed," +
                "Sim Serial Number of this card is: "+currentSimSerial+
                " Network Operator: "+tm.getNetworkOperatorName();
        emailSubject="New Sim inserted in your phone";

        sender=new GMailSender(senderEmail.toString(),emailPassword.toString());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
                Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try{
            new MyAsyncClass().execute();
        }catch (Exception ex){

        }
        stopSelf();
        return START_STICKY;
    }

    class MyAsyncClass extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... params) {
            try{
                sender.sendMail(emailSubject,emailText,senderEmail.toString(),receiverEmail.toString());
//           sender.sendMail("this is sub","this is bod","paudelbipin.bp@gmail.com","paudelbipin@yahoo.com");
            }catch (Exception ex){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }


    }
}
