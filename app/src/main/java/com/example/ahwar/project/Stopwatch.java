package com.example.ahwar.project;

/**
 * Created by Ahwar on 12/31/2016.
 */

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;

public class Stopwatch extends Fragment {
    TextView timer;
    boolean stop = false;
    final stopWatchTime[] mChrono = {null};
    final stopWatchTime[] finalMChrono = {mChrono[0]};

    final Thread[] mThreadChrono = new Thread[1];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v  =  inflater.inflate(R.layout.stopwatch_activity, container, false);

        final int[] counter = {1};


        final Stopwatch context=this;

        Button startb = (Button)  v.findViewById(R.id.start);
        Button lapb = (Button) v.findViewById(R.id.lap);
        final Button stopb = (Button)  v.findViewById(R.id.stop);
        final TextView timer = (TextView)  v.findViewById(R.id.timer);
        final EditText list = (EditText)  v.findViewById(R.id.lapList);
        final ScrollView sView = (ScrollView)  v.findViewById(R.id.scroll);

        list.setEnabled(false);


        startb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (finalMChrono[0] == null) {
                    finalMChrono[0] = new stopWatchTime(context,0);
                    mThreadChrono[0] = new Thread(finalMChrono[0]);
                    mThreadChrono[0].start();
                    finalMChrono[0].start();
                    list.setText("");
                    counter[0] = 1;
                }
            }

        });

        //btn_stop click handler
        stopb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(finalMChrono[0] != null) {
                    finalMChrono[0].stop();
                    mThreadChrono[0].interrupt();
                    mThreadChrono[0] = null;
                    finalMChrono[0] = null;
                }
            }

        });

        lapb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(finalMChrono[0] == null) {
                    return;
                }
                list.append("LAP " + String.valueOf(counter[0]++)
                        + ": " + timer.getText() + "\n");
                sView.post(new Runnable() {
                    @Override
                    public void run() {
                        sView.smoothScrollTo(0, list.getBottom());
                    }
                });
            }
        });

        return v;
    }

    public class LoadTime extends AsyncTask<String, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(final String ... value) {


            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    timer = (TextView) getView().findViewById(R.id.timer);
                    timer.setText(Arrays.toString(value));

                }
            });


            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }
    }





    @Override
    public void onStop()
    {

        stopNow();
        super.onStop();
    }

    @Override
    public void onPause() {
        stopNow();

        super.onPause();
    }

    @Override
    public void onResume() {
        startNow();
        super.onResume();
    }



    @Override
    public void onStart() {

        startNow();
        super.onStart();
    }

    public void stopNow() {
        stop = true;
    }
    public void startNow() {
        stop = false;
    }

    public void updateTimerText(final String timeAsText) {

        if (timeAsText == null) {
            return;
        }
        else {
            new Stopwatch.LoadTime().execute(timeAsText);
        }


    }

    @Override
    public void onDestroy () {

        stopNow();
        super.onDestroy ();

    }


}