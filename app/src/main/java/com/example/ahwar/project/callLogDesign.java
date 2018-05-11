package com.example.ahwar.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahwar on 12/18/2016.
 */

public class callLogDesign extends BaseAdapter {

    Context context;
    ArrayList<String> names;
    ArrayList<String> types;
    ArrayList<String> times;
    ArrayList<String> dates;
    ArrayList<String> numbers;
    LayoutInflater inflator;

    TextView name;
    TextView type;
    TextView time;
    TextView date;
    TextView number;

    public callLogDesign(Context context,ArrayList<String> number, ArrayList<String> name, ArrayList<String> type, ArrayList<String> time, ArrayList<String> date) {

        this.context = context;
        this.names = name;
        this.types = type;
        this.dates= date;
        this.times=time;
        this.numbers=number;

        inflator = LayoutInflater.from(this.context);

    }
    @Override
    public int getCount() {

        return names.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflator.inflate(R.layout.calllog_history_design, parent, false);

        }
        name = (TextView) convertView.findViewById(R.id.name);
        type = (TextView) convertView.findViewById(R.id.type);
        time= (TextView) convertView.findViewById(R.id.time);
        date = (TextView) convertView.findViewById(R.id.date);
        number=(TextView) convertView.findViewById(R.id.number);

        if(names.get(position)==null)
        {
            name.setText("UNKNOWN");
        }
        else
        {
            name.setText(names.get(position));
        }
        type.setText(types.get(position));
        time.setText(times.get(position));
        date.setText(dates.get(position));
        number.setText(numbers.get(position));

        return convertView;
    }


}
