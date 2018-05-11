package com.example.ahwar.project;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Created by Ahwar on 12/31/2016.
 */

public class Timer extends Fragment {

    CountDownTimer countDownTimer = null;
    MediaPlayer player;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= (View) inflater.inflate(R.layout.timer_activity, container, false);

        Button start=(Button)v.findViewById(R.id.start);
        Button timer_cancel=(Button)v.findViewById(R.id.timer_cancel);
        final EditText input=(EditText)v.findViewById(R.id.value);
        final TextView timer=(TextView)v.findViewById(R.id.countdown);


        View.OnClickListener btnClickListner=new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.start:
                        cancel();
                        String a=input.getText().toString();
                        if(Objects.equals(a, "")){
                            break;
                        }
                        int x=Integer.parseInt(a);

                        start(x,v,timer);
                        break;
                    case R.id.timer_cancel:
                        cancel();
                        break;
                    default:
                        break;
                }
            }
        };

        start.setOnClickListener(btnClickListner);
        timer_cancel.setOnClickListener(btnClickListner);

        return v;
    }


    public void start(int val, View v, final TextView timer)
    {
        timer.setText(val+"");

        countDownTimer =new CountDownTimer(val*1000,1000){


            @Override
            public void onTick(long tick) {
                timer.setText(""+tick/1000);
            }

            @Override
            public void onFinish()
            {
                timer.setText("Time Finished");
                player=MediaPlayer.create(getActivity(), R.raw.closer);

                player.start();

            }
        };
        countDownTimer.start();
    }

    @Override
    public void onPause() {
        if (player != null) {
            player.stop();
            player.release();
            player= null;
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (player != null) {
            player.stop();
            player.release();
            player= null;
        }
        super.onStop();
    }

    public void cancel()
    {

        if(countDownTimer!=null){
            countDownTimer.cancel();
            countDownTimer=null;
        }

    }

}
