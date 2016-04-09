package com.bipin.securephone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authenticate extends AppCompatActivity {
    public static final String MyPreferences="secure";
    public static final String pinValueKey="pinValue";
    String pinNumber;
    SharedPreferences sharedPreferences;
    Button okButton;
    EditText givenPin;
    int checkToGoValue=0;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        pinNumber=sharedPreferences.getString("pin", "8888");

        givenPin= (EditText) findViewById(R.id.id_authenticate_pin);
        okButton= (Button) findViewById(R.id.id_authenticate_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (givenPin.getText().toString().isEmpty()) {
                    Toast.makeText(Authenticate.this, "Enter valid pin", Toast.LENGTH_SHORT).show();
                } else
                {
                    //int enteredPin = Integer.parseInt(givenPin.getText().toString());
                if(Integer.parseInt(pinNumber)==Integer.parseInt(givenPin.getText().toString())){


                        Intent intent = new Intent(Authenticate.this, MainPage.class);
                        startActivity(intent);

                }
                else{
                    Toast.makeText(Authenticate.this,"wrong answer",Toast.LENGTH_SHORT).show();
                }
                }
            }
        });













    }
}
