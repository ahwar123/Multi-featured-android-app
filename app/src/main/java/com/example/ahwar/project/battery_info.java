package com.example.ahwar.project;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Ahwar on 1/17/2017.
 */

public class battery_info extends Fragment
{
    TextView batteryVoltage, batteryTemperature, batteryTechnology,
            batteryStatus, batteryHealth,batteryIconSmall,batterPlugged
            ,bpresent,bScale,typePlug;
    Handler handler;
    Runnable runnable;
    ImageView images;
    TextView battery_level,statusMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v  =  inflater.inflate(R.layout.battery_info, container, false);

        images=(ImageView)v.findViewById(R.id.bImage);
        battery_level=(TextView)v.findViewById(R.id.level);
        batteryVoltage = (TextView)v.findViewById(R.id.batteryvoltage);
        batteryTemperature = (TextView)v.findViewById(R.id.batterytemperature);
        batteryTechnology = (TextView)v.findViewById(R.id.batterytechology);
        batteryStatus = (TextView)v.findViewById(R.id.batterystatus);
        batteryHealth = (TextView)v.findViewById(R.id.batteryhealth);
        batteryIconSmall= (TextView)v.findViewById(R.id.iconSmall);
        bpresent = (TextView)v.findViewById(R.id.present);
        statusMain= (TextView)v.findViewById(R.id.status);
        bScale= (TextView)v.findViewById(R.id.scale);
        batterPlugged = (TextView)v.findViewById(R.id.plugged);
        typePlug= (TextView)v.findViewById(R.id.plugtype);



        runnable=new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                int level=(int)battery_status();
                battery_level.setText("Battery LEVEL: "+level+"%");

                if(level >= 80)
                    images.setImageResource(R.drawable.battery_100);
                if(level >= 55 && level<80)
                    images.setImageResource(R.drawable.battery_70);
                if(level >= 30 && level<55)
                    images.setImageResource(R.drawable.battery_40);
                if(level >= 0 && level<30)
                    images.setImageResource(R.drawable.battery_20);

                if(Objects.equals(statusMain.getText().toString(), "Charging"))
                {
                    if(level>0 && level<=60)
                    {
                        images.setImageResource(R.drawable.charge50);

                    }
                    else if (level>60 && level<=100)
                    {
                        images.setImageResource(R.drawable.charge100);

                    }
                }
                notification();

                handler.postDelayed(runnable,5000);
            }


        };

        getActivity().registerReceiver(this.batteryInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        handler=new Handler();
        handler.postDelayed(runnable,0);



        exit(v);
        return v;
    }

    public void notification()
    {
        Intent intent=new Intent(getActivity(),battery_info.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(getActivity(),(int)System.currentTimeMillis(),intent,0);


        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(getActivity())
                .setSmallIcon(android.R.drawable.ic_lock_idle_low_battery)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("BATTERY LEVEL")
                .setContentText(battery_level.getText().toString())
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setAutoCancel(false);

//        notificationBuilder.setDefaults(
//                Notification.DEFAULT_ALL);
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(getActivity());
        notificationManager.notify(0,notificationBuilder.build());

    }

    public Button exit;
    public void exit(View v)
    {
        try {

            exit =(Button)v.findViewById(R.id.btnexit);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    public float battery_status()
    {
        Intent bIntent=getActivity().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = bIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = bIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if(level== -1||scale== -1){
            return 50.0f;
        }
        return  ((float)level / (float)scale) * 100.0f;
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            int  icon_small= intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,0);

            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);


            int  plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
            boolean  present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            int  scale= intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
            String  technology= intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int  temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
            int  voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);

            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if(usbCharge){
                typePlug.setText("Plug Type: USB");
            }
            else if(acCharge)
                typePlug.setText("Plug Type: AC");
            else
            {
                typePlug.setText("Plug Type: NONE");

            }
            batteryIconSmall.setText("Icon Small:  "+icon_small+"\n");
            batterPlugged.setText("Plugged:  "+plugged+"\n");
            bpresent.setText("Present:  "+present+"\n");
            bScale.setText("Scale:  "+scale+"\n");
            batteryTechnology.setText("Technology:  "+technology+"\n");
            batteryTemperature.setText("Temperature:  "+temperature+"\n");
            batteryVoltage.setText("Voltage:  "+voltage+"\n");
            int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
            String strStatus;
            if (status == BatteryManager.BATTERY_STATUS_CHARGING){
                strStatus = "Charging";

            }
            else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING){
                strStatus = "Dis-charging";
            }
            else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                strStatus = "Not charging";
            }
            else if (status == BatteryManager.BATTERY_STATUS_FULL){
                strStatus = "Full";
            }
            else {
                strStatus = "Unknown";
            }
            batteryStatus.setText("Status: " + strStatus);
            statusMain.setText(strStatus);

            int health = intent.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);
            String strHealth;
            if (health == BatteryManager.BATTERY_HEALTH_GOOD){
                strHealth = "Good";
            } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT){
                strHealth = "Over Heat";
            } else if (health == BatteryManager.BATTERY_HEALTH_DEAD){
                strHealth = "Dead";
            } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){
                strHealth = "Over Voltage";
            } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){
                strHealth = "Unspecified Failure";
            } else{
                strHealth = "Unknown";
            }
            batteryHealth.setText("Health: " + strHealth);

        }


    };

    @Override
    public void onStop() {
        handler.removeCallbacks(runnable);
        super.onStop();
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable);

        super.onPause();
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(this.batteryInfoReceiver);
        super.onDestroy();
    }
}
