package com.example.ahwar.project;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ImageView;

/**
 * Created by Ahwar on 1/18/2017.
 */

public class proximity extends Activity implements SensorEventListener {
    MediaPlayer player;
    ImageView image;
    SensorManager sensorManager;
    Sensor proximity;
public static int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proximity_activity);

        image=(ImageView)findViewById(R.id.proxImage);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        proximity=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sensorManager.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //if hand is close to sensor
        if(sensorEvent.values[0]==0.0)
        {
            //get vibrate service to vibrate
           Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 500 milliseconds
            mVibrator.vibrate(500);
            stopPlaying();
            player=MediaPlayer.create(this, R.raw.evillaugh);
            //play mp3
            player.start();

            image.setImageResource(R.mipmap.pic2);
        }
        else
        {
            //stop mp3 when hand high from sensor
            stopPlaying();
            image.setImageResource(R.mipmap.pic1);
        }
    }

    public void stopPlaying() {
        if (player != null) {
            player.stop();
            player.release();
            player= null;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onBackPressed() {
        sensorManager.unregisterListener(this);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);

        super.onStop();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this);

        super.onDestroy();
    }
}
