package com.samir.trace.javaclass;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */
public class MessageService extends Service {

    SmsManager smsManager;
    String currentSimSerial;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        TelephonyManager tm = (TelephonyManager) getApplicationContext().
                getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
        String networkOperator = tm.getNetworkOperator();

        currentSimSerial = tm.getSimSerialNumber();

        PendingIntent sentPI;
        String SENT = "SMS_SENT";

        sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT), 0);
       String sms;
        String phoneNumbertoSend = intent.getStringExtra("phoneNumber");

        Integer lac;
        Integer cid;
        String mcc;
        String mnc;
        mcc=(networkOperator.isEmpty()?"":networkOperator.substring(0,3));
        mnc=(networkOperator.isEmpty()?"":networkOperator.substring(3));
        lac = (location == null ? 0 : location.getLac());
        cid = (location == null ? 0 : location.getCid());

        sms = "Replaced SIM serial:" + currentSimSerial
                + " Location Area code: " + lac +
                " Cellular ID:" + cid
                +" MCC:" + mcc + " MNC:" + mnc;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumbertoSend, null, sms, sentPI, null);
        Log.d("la hai", "lahai ");
        stopSelf();
        return START_STICKY;


    }
}
