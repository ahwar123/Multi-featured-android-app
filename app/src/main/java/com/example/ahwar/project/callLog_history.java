package com.example.ahwar.project;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class callLog_history extends AppCompatActivity {
    callLogDesign design;
    ListView simpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calllog_history);

        if (ContextCompat.checkSelfPermission(callLog_history.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(callLog_history.this,
                    Manifest.permission.READ_CALL_LOG)) {
                ActivityCompat.requestPermissions(callLog_history.this,
                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);

            } else {
                ActivityCompat.requestPermissions(callLog_history.this,
                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);

            }
        } else {
            //do stuff
            TextView date = (TextView) findViewById(R.id.date);
            TextView type = (TextView) findViewById(R.id.type);
            TextView name = (TextView) findViewById(R.id.name);
            TextView time = (TextView) findViewById(R.id.time);
            TextView number = (TextView) findViewById(R.id.number);
            getCallLogDetails(date, type, name, time, number);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(callLog_history.this,
                            Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                        TextView date = (TextView) findViewById(R.id.date);
                        TextView type = (TextView) findViewById(R.id.type);
                        TextView name = (TextView) findViewById(R.id.name);
                        TextView time = (TextView) findViewById(R.id.time);
                        TextView number = (TextView) findViewById(R.id.number);

                        ///////FUNTION CALL////////
                        getCallLogDetails(date, type, name, time, number);

                    } else {
                        Toast.makeText(this, "Permission not Granted", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
        }
    }

    private void getCallLogDetails(TextView d, TextView t, TextView n, TextView tim, TextView num) {
        StringBuffer sb = new StringBuffer();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor stringCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int name = stringCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = stringCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = stringCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = stringCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = stringCursor.getColumnIndex(CallLog.Calls.DURATION);
        ArrayList<String> names=new ArrayList<String>();
        ArrayList<String> numbers=new ArrayList<String>();
        ArrayList<String> dates=new ArrayList<String>();
        ArrayList<String> times=new ArrayList<String>();
        ArrayList<String> types=new ArrayList<String>();

        //sb.append("CALL LOG Detail:\n\n");
        int i=0;
        while (stringCursor.moveToNext()) {

            String personName=stringCursor.getString(name);
            String phoneNumber=stringCursor.getString(number);
            String callType=stringCursor.getString(type);
            String callDate = stringCursor.getString(date);
            Date callDateTime = new Date(Long.valueOf(callDate));

            SimpleDateFormat datess= new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm a");

            String dateString=datess.format(callDateTime);
            String timeString=time.format(callDateTime);
            String callDuration=stringCursor.getString(duration);
            String typeString = null;
            int typeCode = Integer.parseInt(callType);

            switch (typeCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    typeString = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    typeString = "INCOMING";
                    break;
                case CallLog.Calls.REJECTED_TYPE:
                    typeString="Rejected";
                    break;
                case CallLog.Calls.BLOCKED_TYPE:
                    typeString="Blocked";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    typeString = "MISSED";
                    break;

            }
//            retrieveContactPhoto(images);


            names.add(personName);
            types.add(typeString);
            numbers.add(phoneNumber);
            dates.add(dateString);
            times.add(timeString);

            i++;
        }
        simpleList = (ListView) findViewById(R.id.logList);
        design = new callLogDesign(callLog_history.this,numbers, names,types,times,dates);
        simpleList.setAdapter(design);
        stringCursor.close();
    }
}
