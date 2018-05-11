package com.example.ahwar.project;


import android.app.Activity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
* Created by Ahwar on 1/18/2017.
*/

public class accelerometer extends Activity implements SensorEventListener{

    TextView x,y,z;
    Sensor sensor;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelero_activity);


        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        x= (TextView)findViewById(R.id.xcordinate);
        y= (TextView)findViewById(R.id.ycordinate);
        z= (TextView)findViewById(R.id.zcordinate);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x.setText("X:  " + event.values[0]);
        y.setText("Y:  " + event.values[1]);
        z.setText("Z:  " + event.values[2]);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
