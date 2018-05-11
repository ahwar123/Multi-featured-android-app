package com.example.ahwar.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Ahwar on 1/6/2017.
 */

public class calender extends Activity {

    int day,month,year;
    static final int Dialog_id=0;

    public void afterCalender()
    {
                Intent go=new Intent(calender.this,accessory.class);
                startActivity(go);
    }


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final Calendar cal=Calendar.getInstance();
        year =cal.get(Calendar.YEAR);
        month =cal.get(Calendar.MONTH);
        day =cal.get(Calendar.DAY_OF_MONTH);

        showDialog(Dialog_id);
    }


    private DatePickerDialog.OnDateSetListener dpickerListner=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            year = y;
            month = m + 1;
            day = d;
            Toast.makeText(calender.this, year + "/" + month + "/" + day, Toast.LENGTH_LONG).show();
            finish();
        }


    };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case Dialog_id:
                DatePickerDialog dialog = new DatePickerDialog(this, dpickerListner, year, month,day);


                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface
                        .OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dialog.dismiss();
                            finish();
                        }
                    }
                });
                return dialog;
        }
        return null;
    }
}
