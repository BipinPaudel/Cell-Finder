package com.bipin.cellfinder;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity {
    private SensorManager mSensorManager;

    private ShakeEventListener mSensorListener;
    Ringtone r=null;
    Vibrator v;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        final TextView tt= (TextView) findViewById(R.id.shake_text_id);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                    v= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(5);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onBackPressed() {
        if(r!=null){
            r.stop();
        }
        if(v!=null){
            v.cancel();
        }

//        v.cancel();

        mSensorManager.unregisterListener(mSensorListener);
        finish();
    }



    @Override
    protected void onPause() {
        //mSensorManager.unregisterListener(mSensorListener);

        super.onPause();
    }

}
