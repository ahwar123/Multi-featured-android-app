package com.example.ahwar.project;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahwar on 1/28/2017.
 */

public class launcher_base extends ArrayAdapter<ApplicationInfo> {

    private List<ApplicationInfo> appList = null;
    private Context context;
    private PackageManager packageManager;

    public launcher_base(Context context, int resource,
                           List<ApplicationInfo> objects) {
        super(context, resource, objects);

        this.context = context;
        this.appList = objects;
        packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        if(null != appList)
            return appList.size();
        else
            return 0;
    }

    @Override
    public ApplicationInfo getItem(int position) {
        if(null != appList)
            return appList.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if(null == convertView) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.app_base, null);
        }

        ApplicationInfo data = appList.get(position);

        if(null != data) {
            TextView appName = (TextView) convertView.findViewById(R.id.app_name);
            TextView packageName = (TextView) convertView.findViewById(R.id.app_package);
            ImageView iconView = (ImageView) convertView.findViewById(R.id.app_icon);

            appName.setText(data.loadLabel(packageManager));
            packageName.setText(data.packageName);
            iconView.setImageDrawable(data.loadIcon(packageManager));
        }
        return convertView;
    }
}