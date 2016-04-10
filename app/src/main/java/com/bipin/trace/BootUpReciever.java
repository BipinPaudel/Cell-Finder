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
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.bipin.trace.javaclass.GMailSender;

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
    Session session=null;
    ProgressDialog pdialog=null;



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

        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");


        session=Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("paudelbipin.bp@gmail.com", "deerwalkinternational");
            }
        });

        pdialog= ProgressDialog.show(context, "", "Sending Mail ....", true);
        RetrieveFeedTask task=new RetrieveFeedTask();

        //email work end
        if(tm.getSimSerialNumber().toString().equals(storedSimSerial.toString())){
            try{
                Thread.sleep(60000);
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            //task.execute();
            Toast.makeText(context, "same", Toast.LENGTH_SHORT).show();
        }else
        {
            try{
                Thread.sleep(60000);
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            //task.execute();
            Toast.makeText(context, "different", Toast.LENGTH_SHORT).show();
            //new GmailAsync().execute("");
            String sms = "Sim card has changed," +
                    "Sim Serial Number of this card is: "+currentSimSerial+
                    " Network Operator: "+tm.getNetworkOperatorName();
//            try{
//
//                //SmsManager smsManager=SmsManager.getDefault();
//                try{
//                    Thread.sleep(60000);
//                } catch(InterruptedException ex){
//                    Thread.currentThread().interrupt();
//                }
////                smsManager.sendTextMessage(phoneNumbertoSend, null, sms, null, null);
//
////                new GmailAsync().execute("");
////                GMailSender sender=new GMailSender("bipin.paudel@deerwalk.edu.np","*deerwalk*");
////                sender.sendMail("this is subject",
////                        "this is body",
////                        "bipin.paudel@deerwalk.edu.np",
////                        "paudelbipin.bp@gmail.com");
////
////                Log.i("Sms sent","");
//            }
//            catch(Exception e){
//                e.printStackTrace();
//                Log.i("Sending failed","");
//            }


        }

//        Intent sms = new Intent(context, .class);
//        sms.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(sms);

//        Intent netAvailability = new Intent(context, CheckingNetworkAvailability.class);
//        netAvailability.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(netAvailability);
        try{
                    Thread.sleep(60000);
                } catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                }

    }


class RetrieveFeedTask extends AsyncTask<String,Void,String>{
    @Override
    protected String doInBackground(String... params) {
        try{
            Message message=new MimeMessage(session);

            message.setFrom(new InternetAddress("paudelbipin.bp@gmail.com"));

            //message.setRecipient(Message.RecipientType.TO, InternetAddress.parse("paudelbipin@yahoo.com"));
            message.setSubject("this is subject");
            message.setContent("this is content hai","text/html; charset=utf-8");

        }catch (MessagingException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        pdialog.dismiss();
        Toast.makeText(context,"message sent",Toast.LENGTH_SHORT).show();
    }
}



//
//    public class GmailAsync extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String mail_body = "Sim serial number is "+currentSimSerial;
//            String subject = "Your Sim has changed!!!";
//            GMailSender sender = new GMailSender("paudelbipin.bp@gmail.com", "deerwalkinternational");
//            try {
//                sender.sendMail("this is subject",
//                        "this is body",
//                        "paudelbipin.bp@gmail.com",
//                        "bipin.paudel@deerwalk.edu.np");
//            } catch (Exception e) {
//                Log.e("SendMail", e.getMessage(), e);
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(String result) {
//            //
//            super.onPostExecute(result);
//            Log.d("Mail", "Sent");
//        }
//    }



}