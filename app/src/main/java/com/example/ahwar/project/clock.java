package com.example.ahwar.project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Ahwar on 1/19/2017.
 */

public class clock extends FragmentActivity {

    Fragment st;
    Fragment tim;

    FragmentManager fm ;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        st= new Stopwatch();
        tim= new Timer();

        RelativeLayout s=(RelativeLayout)findViewById(R.id.sb);
        RelativeLayout t=(RelativeLayout)findViewById(R.id.tb);


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, st);
                fragmentTransaction.commit();

            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, tim);
                fragmentTransaction.commit();
            }
        });
    }

}
