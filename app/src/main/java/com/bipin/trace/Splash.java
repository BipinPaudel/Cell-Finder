package com.bipin.trace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.bipin.trace.javaclass.GMailSender;
import com.bipin.trace.javaclass.GPSTracker;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Splash extends AppCompatActivity {

    String currentSerial;


    public static final String MyPreferences = "secure";//name of preference file
    SharedPreferences sharedPreferences;//shared preferences object
    private final int splash_length = 1000;//length of time to display splash screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        currentSerial=tm.getSimSerialNumber();

//


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              sharedPreferences = getSharedPreferences(MyPreferences, MODE_PRIVATE);
                //new GmailAsync().execute();
//                try {
//                    GMailSender sender=new GMailSender("paudelbipin.bp@gmail.com","deerwalkinternational");
//                    sender.sendMail("subject","body",
//                            "paudelbipin.bp@gmail.com",
//                            "bipin.paudel@deerwalk.edu.np");
//                }  catch (Exception e) {
//                    e.printStackTrace();
//                }
                String name = sharedPreferences.getString("name", null);//gets name of user if exists
                String number = sharedPreferences.getString("number", null);//get number to send message if exists
                String email = sharedPreferences.getString("email", null);//gets email to send message if exists
                String pin = sharedPreferences.getString("pin", null);//gets pin if exists
                String senderEmail=sharedPreferences.getString("sendingEmail",null);//gets
                String emailPassword=sharedPreferences.getString("emailPassword",null);



                //Now we have to check if form is previously filled or not
                if (name == null && number == null && email == null && pin == null
                        &&senderEmail==null && emailPassword==null) {
                    Intent intent = new Intent(Splash.this, AddInfoPage.class);//intent to go to addInfo page
                    Splash.this.startActivity(intent);
                    Splash.this.finish();
                } else {
                    //it means form is already filled and should be redirected for authentication
                    Intent intent = new Intent(Splash.this, Authenticate.class);
                    startActivity(intent);
                    Splash.this.finish();
                }
            }
        }, splash_length);
    }







//    public void sendEmail() throws MessagingException
//    {
//        Log.i("check","start");
//
//        String host = "smtp.gmail.com";
//        String from = "paudelbipin.bp@gmail.com"; //sender email, this is our website email
//        String pass = "deerwalkinternational"; //password of sender email
//
//        Properties props = System.getProperties();
//        props.put("mail.smtp.starttls.enable", "true"); // added this line
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.user", from);
//        props.put("mail.smtp.password", pass);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        Log.i("check","done pops ");
//
//
////creating session
//        Session session = Session.getDefaultInstance(props, null);
//        MimeMessage message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(from));
//        Log.i("check","done sessions ");
//
//        InternetAddress toAddress;
//        toAddress = new InternetAddress("bipin.paudel@deerwalk.edu.np");
//        message.addRecipient(Message.RecipientType.TO, toAddress);
//        Log.i("check","add recipante ");
//
//        message.setSubject("this is subject");
//        message.setText("This is my app");
//
//
//        Log.i("check","transport");
//
//        Transport transport = session.getTransport("smtp");
//
////connecting..
//        Log.i("check","connecting");
//        transport.connect(host, from, pass);
////sending...
//        Log.i("check","wana send");
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
//        Log.i("check","sent");
//    }


//    public class GmailAsync extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String mail_body = "Sim serial number is "+ currentSerial;
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
