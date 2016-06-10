package com.bipin.cellfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */
public class MainPage extends AppCompatActivity {

    Button editButton;
    SharedPreferences sharedPreferences;
    public static final String MyPreferences = "secure";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

}
