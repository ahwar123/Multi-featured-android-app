package com.example.ahwar.project;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ahwar on 1/5/2017.
 */

public class dialer extends Activity {
    private static final int MY_PERMISSIONS_REQUEST = 1;

    TextView number;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bclear, bcall, bstar, bhash;

    String numbers = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialer_activity);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST);

            }
        }


        number = (TextView) findViewById(R.id.number);

        b1 = (Button) findViewById(R.id.d_1);
        b2 = (Button) findViewById(R.id.d_2);
        b3 = (Button) findViewById(R.id.d_3);
        b4 = (Button) findViewById(R.id.d_4);
        b5 = (Button) findViewById(R.id.d_5);
        b6 = (Button) findViewById(R.id.d_6);
        b7 = (Button) findViewById(R.id.d_7);
        b8 = (Button) findViewById(R.id.d_8);
        b9 = (Button) findViewById(R.id.d_9);
        b0 = (Button) findViewById(R.id.d_0);
        bstar = (Button) findViewById(R.id.d_star);
        bhash = (Button) findViewById(R.id.d_hash);

        bclear = (Button) findViewById(R.id.d_clear);
        bcall = (Button) findViewById(R.id.d_call);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "1";
                number.setText(numbers);
            }
        });

        bstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "*";
                number.setText(numbers);
            }
        });

        bhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "#";
                number.setText(numbers);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "2";
                number.setText(numbers);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "3";
                number.setText(numbers);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "4";
                number.setText(numbers);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "5";
                number.setText(numbers);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "6";
                number.setText(numbers);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "7";
                number.setText(numbers);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "8";
                number.setText(numbers);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "9";
                number.setText(numbers);
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers += "0";
                number.setText(numbers);
            }
        });
        bcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numbers.contains("#")) {
                    numbers = numbers.replace("#", "%23");
                }
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + numbers));
                if (ActivityCompat.checkSelfPermission(dialer.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers="";
                number.setText(numbers);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST:{
                if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(dialer.this,"Permission granted",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(dialer.this,"No Permission granted",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        }
    }

}
