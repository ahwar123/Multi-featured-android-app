package com.example.ahwar.project;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class accessory extends Fragment {

    public Button calender;
    public Button clock;
    public Button torch;

    public Button calculater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_accessory, container, false);
        calculater =(Button)v.findViewById(R.id.calculator);
        calculater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),calculater.class);
                startActivity(go);
            }
        });
        calender=(Button)v.findViewById(R.id.calendar);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),calender.class);
                startActivity(go);
            }
        });

        clock =(Button)v.findViewById(R.id.clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),clock.class);
                startActivity(go);
            }
        });

        torch =(Button)v.findViewById(R.id.torch);
        torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),torch.class);
                startActivity(go);
            }
        });

        return v;
    }
}
