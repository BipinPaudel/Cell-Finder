package com.samir.trace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 Student’s Name: Samir Babu Gharti
 Final Year Project
 Islington College
 LMU ID: 11069886
 Supervisor: Mr. Dhruba Sen
 */
public class EmailAuthenticate extends AppCompatActivity {

    EditText enteredEmail;
    EditText enteredPassword;
    public static final String MyPreferences="secure";//for sharedpreferences
    Button okButton;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_authenticate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //sharedPreferences object is initialized
        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        enteredEmail= (EditText) findViewById(R.id.enter_email_authentication_edittext_id);//email entered
        enteredPassword=(EditText)findViewById(R.id.enter_password_authentication_edittext_id);//email password entered
        okButton= (Button) findViewById(R.id.email_authentication_button);//ok button

        okButton.setOnClickListener(new View.OnClickListener() {//ok button is clicked
            @Override
            public void onClick(View v) {
                //stored Email and password has to be retrieved from sharedpreferences
                String storedEmail=sharedPreferences.getString("sendingEmail","none");
                String storedPassword=sharedPreferences.getString("emailPassword","none");

                String eMail=enteredEmail.getText().toString();
                String ePassword=enteredPassword.getText().toString();
                //stored email and password is checked with that entered by user
                //if equal, passed to MainPage
                if(eMail.equals(storedEmail.toString())
                        && ePassword.equals(storedPassword.toString()) ){
                    Intent intent = new Intent(EmailAuthenticate.this, MainPage.class);
                    startActivity(intent);
                    enteredEmail.setText("");
                    enteredPassword.setText("");
                }
                else{
                    Toast.makeText(EmailAuthenticate.this,"Enter valid information",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
