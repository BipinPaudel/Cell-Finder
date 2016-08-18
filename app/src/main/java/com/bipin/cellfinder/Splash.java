package com.bipin.cellfinder;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;

import com.bipin.cellfinder.dialogs.EnablePhoneDialog;


public class Splash extends AppCompatActivity {
    private static final int REQUEST_PHONE_STATE = 0;

    public static final String MyPreferences = "secure";//name of preference file
    SharedPreferences sharedPreferences;//shared preferences object
    private final int splash_length = 3000;//length of time to display splash screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler();

        
        
        
    }

    private void handler(){
        Log.d("inside handler", "inside handler");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

                    Log.d("no no " , " no camera");
                    requestPhoneStatePermission();
                }
                else
                {


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
            }
        }, splash_length);
    }
    private void requestPhoneStatePermission(){
        Log.d("inside requestcamera","inside request camera");
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_PHONE_STATE)){
            Log.d("inside","inside");
            ActivityCompat.requestPermissions(Splash.this,new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_PHONE_STATE);
        }
        else{
            Log.d("stoped here","stopped here");
            ActivityCompat.requestPermissions(Splash.this,new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_PHONE_STATE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("inside on","inside on");
    if (requestCode == REQUEST_PHONE_STATE) {

            // BEGIN_INCLUDE(permission_result)
            // Received permission result for camera permission.


            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed

                handler();
            } else {
                Log.d("last else","last else");
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        EnablePhoneDialog enablePhoneDialog=new EnablePhoneDialog();
                        try{
                            enablePhoneDialog.show(getSupportFragmentManager(),"PhoneEnableFragment");
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }


                    }
                });

                //finish();
                //System.exit(0);
                //onDestroy();


            }
            // END_INCLUDE(permission_result)

        }
        else{
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }
}
