package com.example.cameraxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    PreviewView previewView;
    ImageCapture imageCapture;
    Executor executor = Executors.newSingleThreadExecutor();

    final int REQUEST_CODE_PERMISSIONS = 1001;
    final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA","android.permission.RECORD_AUDIO"};

    ImageView imageView;
    EditText inputPictureUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!allPermissionsGranted()){
            ActivityCompat.requestPermissions(this,REQUIRED_PERMISSIONS,REQUEST_CODE_PERMISSIONS);
        }
    }
    boolean allPermissionsGranted(){
        for (String permission: REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }
        return true;
    }
}