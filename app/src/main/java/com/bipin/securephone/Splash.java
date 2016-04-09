package com.bipin.securephone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.os.Handler;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    public static final String MyPreferences="secure";
    SharedPreferences sharedPreferences;
    private final int splash_length=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



//        Toast.makeText(Splash.this,number,Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                sharedPreferences=getSharedPreferences(MyPreferences,MODE_PRIVATE);
                String name=sharedPreferences.getString("name",null);
                String number=sharedPreferences.getString("number",null);
                String email=sharedPreferences.getString("email",null);
                String pin=sharedPreferences.getString("pin",null);
                if(name==null && number==null && email==null && pin==null){

                    Intent intent = new Intent(Splash.this, AddInfoPage.class);

                    Splash.this.startActivity(intent);
                    Splash.this.finish();
                }
                else{

                    Intent intent= new Intent(Splash.this,Authenticate.class);
                    startActivity(intent);
                    Splash.this.finish();
                }

             }


        }, splash_length);




    }

}
