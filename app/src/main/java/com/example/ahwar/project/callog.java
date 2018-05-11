package com.example.ahwar.project;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class callog extends Fragment {

    public Button history;
    public Button dialer;
    public Button contacts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_callog, container, false);

        dialer =(Button)v.findViewById(R.id.dialer);
        dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),dialer.class);
                startActivity(go);
            }
        });

        contacts =(Button)v.findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),contacts.class);
                startActivity(go);
            }
        });

        history =(Button)v.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(getActivity(),callLog_history.class);
                startActivity(go);
            }
        });

        return v;
    }

}

