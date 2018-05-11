package com.example.ahwar.project;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Ahwar on 1/19/2017.
 */

public class torch extends Activity {

    ImageButton imageButton;
    static Camera camera;
    Camera.Parameters parameters;
    boolean checkFlash=false;
    boolean isOn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.torch_activity);

        imageButton=(ImageButton)findViewById(R.id.pushup);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            //ask for authorisation
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        else
            try{
                if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
                {
                    releaseCameraAndPreview();
                    camera= Camera.open();
                    parameters=camera.getParameters();
                    checkFlash=true;
                }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFlash == true)
                {
                    if (!isOn)
                    {
                        imageButton.setImageResource(R.drawable.pushdown);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        isOn = true;
                    }
                    else
                    {

                        imageButton.setImageResource(R.drawable.pushup);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        isOn = false;
                    }

                }

                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(torch.this);
                    builder.setTitle("Error");
                    builder.setMessage("FlashLight is not available on this device...");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        if (checkFlash == true)
        {
            if (camera != null)
            {
                camera.release();
                camera = null;
            }
            if (camera == null)
            {
                camera = Camera.open();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera!=null)
        {
            camera.release();
            camera=null;
        }
    }
    @Override
    protected void onDestroy() {
        if (camera != null) {
            camera.release();
        }
        super.onDestroy();
    }
    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.release();
            camera= null;
        }
    }
}

