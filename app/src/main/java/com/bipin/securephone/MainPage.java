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
import android.widget.Toast;

public class MainPage extends AppCompatActivity {

//    int checkToGoValue;
    SharedPreferences sharedPreferences;
    public static final String MyPreferences="secure";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       

//        Intent intent=getIntent();
//        checkToGoValue=intent.getIntExtra("checkToGoValue",0);
//        if(checkToGoValue==1){
//            Intent intent1=new Intent(MainPage.this,AddInfoPage.class);
//            intent1.putExtra("addOrEdit",1);
//            startActivity(intent1);
//        }
        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        displayInfo();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void displayInfo(){
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        String name=sharedPreferences.getString("name","none");
        String email=sharedPreferences.getString("email","none");
        String number=sharedPreferences.getString("number","none");
        String pin=sharedPreferences.getString("pin","none");
        String simSerial=sharedPreferences.getString("simSerial","none");
        Toast.makeText(MainPage.this,name+" " +email + " "+number+" "+pin+" "+simSerial,Toast.LENGTH_SHORT).show();
    }

}
