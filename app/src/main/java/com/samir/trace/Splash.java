package com.samir.trace;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */
public class Splash extends AppCompatActivity {


    public static final String MyPreferences = "secure";//name of preference file
    SharedPreferences sharedPreferences;//shared preferences object
    private final int splash_length = 3000;//length of time to display splash screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences(MyPreferences, MODE_PRIVATE);

                String name = sharedPreferences.getString("name", null);//gets name of user if exists
                String number = sharedPreferences.getString("number", null);//get number to send message if exists
                String email = sharedPreferences.getString("email", null);//gets email to send message if exists
                String pin = sharedPreferences.getString("pin", null);//gets pin if exists
                String senderEmail = sharedPreferences.getString("sendingEmail", null);//gets
                String emailPassword = sharedPreferences.getString("emailPassword", null);


                //Now we have to check if form is previously filled or not
                if (name == null && number == null && email == null && pin == null
                        && senderEmail == null && emailPassword == null) {
                    Log.d("la sakkyo", "sakkyo");
                    Intent intent = new Intent(Splash.this, AddInfoPage.class);//intent to go to addInfo page
                    Splash.this.startActivity(intent);
                    Splash.this.finish();
                } else {
                    //it means form is already filled and should be redirected for authentication
                    Log.d("la sakkena", "sakkena");
                    Intent intent = new Intent(Splash.this, Authenticate.class);
                    startActivity(intent);
                    Splash.this.finish();
                }
            }
        }, splash_length);
    }
}
