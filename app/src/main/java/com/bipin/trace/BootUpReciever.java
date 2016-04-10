package com.bipin.trace;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.bipin.trace.javaclass.GMailSender;
import com.bipin.trace.javaclass.MyService;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

//

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import android.app.Activity;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by bips on 4/9/16.
 */
public class BootUpReciever extends BroadcastReceiver {


    GMailSender sender;


    Context context=null;
    String email;
    String currentSimSerial;
    SharedPreferences sharedPreferences;


    public static final String MyPreferences = "secure";

    @Override
    public void onReceive(Context context, Intent intent) {

        sharedPreferences = context.getSharedPreferences(MyPreferences, 0);
        String storedSimSerial = sharedPreferences.getString("simSerial", null);
        String phoneNumbertoSend = sharedPreferences.getString("number", null);
        Log.d("Stored Sim Serial::", storedSimSerial);

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        currentSimSerial = tm.getSimSerialNumber();
        Log.d("Current Sim Serial", "::" + currentSimSerial);
        String trustedNum = sharedPreferences.getString("number", null);
        email = sharedPreferences.getString("email", null);
        //email work





        //email work end
        //if(tm.getSimSerialNumber().toString().isEmpty()) return;
        if(tm.getSimSerialNumber().toString().equals(storedSimSerial.toString())){
            try{
                Thread.sleep(60000);
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            Toast.makeText(context, "same", Toast.LENGTH_SHORT).show();



        }

        else
        {
            Log.d("Inside different", "::" + "");

            Log.d("Thread completed","");
            try{
                Log.d("above that","");
                Intent intent1=new Intent(context, MyService.class);
                context.startService(intent1);
            }
            catch (Exception ex){
                Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
            }
            try{
                Thread.sleep(50000);
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            String sms = "Sim card has changed," +
                    "Sim Serial Number of this card is: "+currentSimSerial+
                    " Network Operator: "+tm.getNetworkOperatorName();
            //SmsManager smsManager=SmsManager.getDefault();
            //smsManager.sendTextMessage(phoneNumbertoSend, null, sms, null, null);




        }

    }






}