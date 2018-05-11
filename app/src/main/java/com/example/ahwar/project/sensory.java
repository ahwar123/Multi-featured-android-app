package com.example.ahwar.project;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class sensory extends Fragment {
    public Button prox;
    public Button acc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_sensory, container, false);


        prox = (Button) v.findViewById(R.id.proximity);
        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), proximity.class);
                startActivity(go);
            }
        });
        acc = (Button) v.findViewById(R.id.acceler);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), accelerometer.class);
                startActivity(go);
            }
        });
        return v;

    }
}