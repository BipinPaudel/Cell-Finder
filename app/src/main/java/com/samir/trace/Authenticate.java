package com.samir.trace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Student’s Name: Samir Babu Gharti
 * Final Year Project
 * Islington College
 * LMU ID: 11069886
 * Supervisor: Mr. Dhruba Sen
 */
public class Authenticate extends AppCompatActivity {
    public static final String MyPreferences = "secure";//for shared preferences
    String pinNumber;
    SharedPreferences sharedPreferences;
    Button okButton;
    Button forgotPinButton;
    EditText givenPin;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        pinNumber = sharedPreferences.getString("pin", "8888");//stored pin number is retrieved from
        //shared preferences.

        givenPin = (EditText) findViewById(R.id.id_authenticate_pin);//pin entered
        okButton = (Button) findViewById(R.id.id_authenticate_button);//ok button
        forgotPinButton = (Button) findViewById(R.id.forgot_pin_button);//forgot pin button
        okButton.setOnClickListener(new View.OnClickListener() {//listener for okButton
            @Override
            public void onClick(View v) {
                if (givenPin.getText().toString().isEmpty()) {
                    //if pin is empty this message will pop up as toast message
                    Toast.makeText(Authenticate.this, "Enter valid pin", Toast.LENGTH_SHORT).show();
                } else {
                    //stored pinNumber and givenPin number is checked as integer
                    //if true redirected to MainPage
                    if (Integer.parseInt(pinNumber) == Integer.parseInt(givenPin.getText().toString())) {
                        Intent intent = new Intent(Authenticate.this, MainPage.class);
                        startActivity(intent);
                        givenPin.setText("");

                    } else {
                        //Wrong answer will be displayed as a toast message
                        Toast.makeText(Authenticate.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgotPinButton.setOnClickListener(new View.OnClickListener() {//listener for forgot button
            @Override
            public void onClick(View v) {
                intent = new Intent(Authenticate.this, EmailAuthenticate.class);//goes to email authenticate activity
                startActivity(intent);
            }
        });


    }
}
