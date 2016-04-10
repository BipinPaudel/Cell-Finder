package com.bipin.trace;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bipin.trace.javaclass.GPSTracker;

import org.w3c.dom.Text;

public class MainPage extends AppCompatActivity {

    Button editButton;
    SharedPreferences sharedPreferences;
    public static final String MyPreferences = "secure";


    private LocationManager locationManager;
    private LocationListener locationListener;
    Button gpsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gpsButton = (Button) findViewById(R.id.gpsButton);


        editButton = (Button) findViewById(R.id.id_main_edit_info);

        //when edit button is clicked, it will be redirected to AddInfoPage class
        //this is the same class where we fill the form
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, AddInfoPage.class);
                intent.putExtra("editValue", 1);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        displayInfo();//retrieves stored information from sharedpreferences and displays in this activity
        /// for gps
        //Button gpsButton = (Button) findViewById(R.id.gpsButton);
//        configureButton();
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Toast.makeText(MainPage.this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainPage.this, "ggggg", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//            }
//        };
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{
//                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.INTERNET
//                },10);
//
//                return;
//            }
//        }else {
//            configureButton();
//
//        }

        ///for gps




    }

    public void displayInfo() {

        TextView nameView = (TextView) findViewById(R.id.main_page_name);
        TextView senderEmailView = (TextView) findViewById(R.id.main_page_senderEmail);
        TextView numberView = (TextView) findViewById(R.id.main_page_number);
        TextView receiverEmailView = (TextView) findViewById(R.id.main_page_receiverEmail);
        TextView pinNumber = (TextView) findViewById(R.id.main_page_pin);
        TextView serialNumber = (TextView) findViewById(R.id.main_page_serialNumber);
        //information is retrieved from sharedpreferences one by one and stored in a variable
        String name = sharedPreferences.getString("name", "none");
        String receiverEmail = sharedPreferences.getString("email", "none");
        String senderEmail = sharedPreferences.getString("sendingEmail", "none");
        String number = sharedPreferences.getString("number", "none");
        String pin = sharedPreferences.getString("pin", "none");
        String simSerial = sharedPreferences.getString("simSerial", "none");

        //retrieved information is display in a textview.
        nameView.setText(name);
        senderEmailView.setText(senderEmail);
        numberView.setText(number);
        receiverEmailView.setText(receiverEmail);
        pinNumber.setText(pin);
        serialNumber.setText(simSerial);


    }



//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode){
//            case 10:
//                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
//                    configureButton();
//                return;
//        }
//    }
//
//    private void configureButton() {
//        gpsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
//                //Toast.makeText(MainPage.this, "gg", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

}
