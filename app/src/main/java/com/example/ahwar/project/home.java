package com.example.ahwar.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.FractionRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class home extends AppCompatActivity {

        DrawerLayout drawerLayout;
        ActionBarDrawerToggle toggle;
        Toolbar toolbar;
        NavigationView navigationView;
        FragmentManager fm;
        FragmentTransaction fragmentTransaction;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            navigationView=(NavigationView)findViewById(R.id.nvView);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
            toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close);

            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            View header=navigationView.inflateHeaderView(R.layout.navigation_header);
            View headerLayout = navigationView.getHeaderView(0);


            drawerLayout.setDrawerListener(toggle);
//            fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.home_frame, new welcome());
//            fragmentTransaction.commit();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId())
                    {
                        case R.id.sensors:
                            fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.home_frame, new sensory());
                            fragmentTransaction.commit();
                            drawerLayout.closeDrawers();
                            break;

                        case R.id.accessory:
                            fm = getFragmentManager();
                            fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.home_frame, new accessory());
                            fragmentTransaction.commit();
                            drawerLayout.closeDrawers();
                            break;
                        case R.id.callog:
                            fm = getFragmentManager();
                            fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.home_frame, new callog());
                            fragmentTransaction.commit();
                            drawerLayout.closeDrawers();
                            break;
                        case R.id.bettery:

                            fm = getFragmentManager();
                            fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.home_frame, new battery_info());
                            fragmentTransaction.commit();
                            drawerLayout.closeDrawers();
                            break;

                        case R.id.launcher:

                            fm = getFragmentManager();
                            fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.home_frame, new app_launcher());
                            fragmentTransaction.commit();
                            drawerLayout.closeDrawers();
                            break;

                        default:
                            break;
                    }

                    return false;
                }
            });


        }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        home.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        drawerLayout.openDrawer(Gravity.LEFT);
        super.onStart();
    }

    @Override
    protected void onResume() {
        drawerLayout.closeDrawers();
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        drawerLayout.closeDrawers();
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        drawerLayout.closeDrawers();
        super.onStop();
    }

    @Override
    protected void onPause() {
        drawerLayout.closeDrawers();
        super.onPause();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
}