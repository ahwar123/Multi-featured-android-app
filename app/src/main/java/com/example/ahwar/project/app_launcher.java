package com.example.ahwar.project;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Created by Ahwar on 1/28/2017.
 */

public class app_launcher extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= (View) inflater.inflate(R.layout.launcher_activity, container, false);

        ImageButton b=(ImageButton)v.findViewById(R.id.launch);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(getActivity(),launcher.class);
                startActivity(go);
            }
        });
        return v;
    }
}


