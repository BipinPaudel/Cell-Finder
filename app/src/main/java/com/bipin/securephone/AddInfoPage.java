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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddInfoPage extends AppCompatActivity {

    EditText enteredName;
    EditText enteredNumber;
    EditText enteredEmail;
    EditText enteredPin;
    Button addButton;
    SharedPreferences sharedPreferences;
    String simSerialNumber;

    public static final String MyPreferences="secure";
    public static final String nameValue="name";
    public static final String numberValue="phonenumber";
    public static final String emailValue="emailValue";
    public static final String pinValueKey="pinValue";
    int addOrEditValue=0;  //add=1, edit=0
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enteredName= (EditText) findViewById(R.id.id_edittext_add_name);
        enteredNumber=(EditText)findViewById(R.id.id_edittext_add_number);
        enteredEmail=(EditText)findViewById(R.id.id_edittext_add_email);
        enteredPin=(EditText)findViewById(R.id.id_edittext_add_pin);

        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        simSerialNumber=tm.getSimSerialNumber();

        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        Intent intent=getIntent();
        addOrEditValue=intent.getIntExtra("addOrEdit", 0);
        if (addOrEditValue==0){
            addInfo();
        }
//        else (addOrEditValue==0){
//            //editInfo();
//        }









        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public  void addInfo(){

        addButton= (Button) findViewById(R.id.id_add_info_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddInfoPage.this,"la hai",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", enteredName.getText().toString());
                editor.putString("number", enteredNumber.getText().toString());
                editor.putString("email", enteredEmail.getText().toString());
                editor.putString("pin", enteredPin.getText().toString());
                editor.putString("simSerial",simSerialNumber);
                editor.commit();
                Toast.makeText(AddInfoPage.this, "Added info", Toast.LENGTH_SHORT).show();

                enteredName.setText("");
                enteredNumber.setText("");
                enteredEmail.setText("");
                enteredPin.setText("");
                Intent intent=new Intent(AddInfoPage.this,MainPage.class);
                startActivity(intent);
            }
        });



    }

    public void editInfo(){

    }

}
