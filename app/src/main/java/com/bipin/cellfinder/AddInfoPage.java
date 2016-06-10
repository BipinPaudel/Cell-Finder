package com.bipin.cellfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddInfoPage extends AppCompatActivity {

    EditText enteredName;
    EditText enteredNumber;
    EditText receivingEmail;
    EditText sendingEmail;
    EditText emailPassword;
    EditText enteredPin;
    Button addButton;
    SharedPreferences sharedPreferences;
    String simSerialNumber;

    public static final String MyPreferences = "secure";
    int addOrEditValue = 0;  //add=1, edit=0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enteredName = (EditText) findViewById(R.id.id_edittext_add_name);
        sendingEmail = (EditText) findViewById(R.id.id_edittext_your_email);
        emailPassword = (EditText) findViewById(R.id.id_edittext_email_password);
        enteredNumber = (EditText) findViewById(R.id.id_edittext_add_number);
        receivingEmail = (EditText) findViewById(R.id.id_edittext_add_email);
        enteredPin = (EditText) findViewById(R.id.id_edittext_add_pin);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        simSerialNumber = tm.getSimSerialNumber();


        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        Intent intent = getIntent();
        addOrEditValue = intent.getIntExtra("editValue", 0);

        if (addOrEditValue == 0) {
            addInfo();
        } else if (addOrEditValue == 1) {
            editInfo();

        }

    }

    //this function uses regular expression to validate email id.
    //if both email are valid , only then this function returns true
    public boolean validateEmail(String passedEmail, String passedEmail1) {
        //passed email is trimmed to check
        String email = passedEmail.trim();
        String email1 = passedEmail1.trim();
        //regular expression for email checking
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-zA-Z0-9.]+";

        if (email.matches(emailPattern) && email1.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }

    //this function helps to add information for the first time.
    public void addInfo() {

        addButton = (Button) findViewById(R.id.id_add_info_button);
        //this code executed after add Button is clicked.
        //information are retrieved from edittext from user and stored in sharedpreferences
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = enteredName.getText().toString();
                String sendingEmailText = sendingEmail.getText().toString();
                String emailPasswordText = emailPassword.getText().toString();
                String numberText = enteredNumber.getText().toString();
                String receivingEmailText = receivingEmail.getText().toString();
                String pinText = enteredPin.getText().toString();


                SharedPreferences.Editor editor = sharedPreferences.edit();
                //no any edittext should be empty
                if (nameText.isEmpty() && sendingEmailText.isEmpty() && emailPasswordText.isEmpty() && numberText.isEmpty()
                        && receivingEmailText.isEmpty() && pinText.isEmpty()) {//
                    Toast.makeText(AddInfoPage.this, "Enter all data", Toast.LENGTH_SHORT).show();
                } else {
                    if (pinText.length() != 4) {//pin should be only of 4 length
                        Toast.makeText(AddInfoPage.this, "Pin number has to be of length 4", Toast.LENGTH_SHORT).show();
                    } else if (validateEmail(sendingEmailText, receivingEmailText) == false) {//email should be valid
                        Toast.makeText(AddInfoPage.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                    } else {//if everythings ok this will execute
                        //user entered information is stored as key value pair in sharedpreferences.
                        editor.putString("name", nameText);
                        editor.putString("sendingEmail", sendingEmailText);
                        editor.putString("emailPassword", emailPasswordText);
                        editor.putString("number", numberText);
                        editor.putString("email", receivingEmailText);
                        editor.putString("pin", pinText);
                        editor.putString("simSerial", simSerialNumber);
                        editor.commit();
                        Toast.makeText(AddInfoPage.this, "Added info", Toast.LENGTH_SHORT).show();

                        enteredName.setText("");
                        sendingEmail.setText("");
                        emailPassword.setText("");
                        enteredNumber.setText("");
                        receivingEmail.setText("");
                        enteredPin.setText("");
                        Intent intent = new Intent(AddInfoPage.this, MainPage.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }

    //this function is called to edit information.
    //stored information will be retrieved and shown in edittext
    // and then they will be replaced
    public void editInfo() {

        //edittext is filled with retrieved information from sharedpreferences
        enteredName.setText(sharedPreferences.getString("name", "none"));
        sendingEmail.setText(sharedPreferences.getString("sendingEmail", "none"));
        emailPassword.setText(sharedPreferences.getString("emailPassword", "none"));
        enteredNumber.setText(sharedPreferences.getString("number", "none"));
        receivingEmail.setText(sharedPreferences.getString("email", "none"));
        enteredPin.setText(sharedPreferences.getString("pin", "none"));


        addButton = (Button) findViewById(R.id.id_add_info_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = enteredName.getText().toString();
                String sendingEmailText = sendingEmail.getText().toString();
                String emailPasswordText = emailPassword.getText().toString();
                String numberText = enteredNumber.getText().toString();
                String receivingEmailText = receivingEmail.getText().toString();
                String pinText = enteredPin.getText().toString();


                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (nameText.isEmpty() && sendingEmailText.isEmpty() && emailPasswordText.isEmpty() && numberText.isEmpty()
                        && receivingEmailText.isEmpty() && pinText.isEmpty()) {
                    Toast.makeText(AddInfoPage.this, "Enter all data", Toast.LENGTH_SHORT).show();
                } else {
                    if (pinText.length() != 4) {
                        Toast.makeText(AddInfoPage.this, "Pin number has to be of length 4", Toast.LENGTH_SHORT).show();
                    } else if (validateEmail(sendingEmailText, receivingEmailText) == false) {
                        Toast.makeText(AddInfoPage.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                    } else {
                        //if everything goes all right value will be replaced
                        editor.putString("name", nameText);
                        editor.putString("sendingEmail", sendingEmailText);
                        editor.putString("emailPassword", emailPasswordText);
                        editor.putString("number", numberText);
                        editor.putString("email", receivingEmailText);
                        editor.putString("pin", pinText);
                        editor.commit();
                        Toast.makeText(AddInfoPage.this, "Information Added", Toast.LENGTH_SHORT).show();

                        enteredName.setText("");
                        sendingEmail.setText("");
                        emailPassword.setText("");
                        enteredNumber.setText("");
                        receivingEmail.setText("");
                        enteredPin.setText("");
                        Intent intent = new Intent(AddInfoPage.this, MainPage.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }

}
