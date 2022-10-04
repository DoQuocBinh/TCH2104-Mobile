package com.example.cameraxdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
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
        startCamera();
        imageView = findViewById(R.id.imageView);
        previewView = findViewById(R.id.previewView);
        inputPictureUri = findViewById(R.id.inputPictureUri);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view->{
            final String[] items = {"Take photo","Choose from Library","View picture from an URI"};
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setItems(items,(dialog,item)->{
               if(items[item].equals("Take photo")){
                   Toast.makeText(this, "Take Photo", Toast.LENGTH_SHORT).show();
                   takePhoto();
               }else if(items[item].equals("Choose from Library")){
                   Toast.makeText(this, "From Library", Toast.LENGTH_SHORT).show();
               }else if(items[item].equals("View picture from an URI")){
                   Toast.makeText(this, "From URI", Toast.LENGTH_SHORT).show();
               }
            });
            dlg.show();
        });
    }
    private void takePhoto() {
        long timestamp = System.currentTimeMillis();
        File savedFile = new File(getApplicationContext().getFilesDir(),String.valueOf(timestamp));
        ImageCapture.OutputFileOptions option2 = new ImageCapture.OutputFileOptions.Builder(savedFile)
                .build();

        imageCapture.takePicture(option2,
                executor,
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Uri selectingImage = outputFileResults.getSavedUri();
                        runOnUiThread(()->{
                            try {
                                imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver()
                                        ,selectingImage));
                                inputPictureUri.setText(selectingImage.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {

                    }
                });
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture
                = ProcessCameraProvider.getInstance(this);

        cameraProviderListenableFuture.addListener(()->{
            try {
                ProcessCameraProvider cameraProvider = cameraProviderListenableFuture.get();
                CameraSelector cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());
                imageCapture = new ImageCapture.Builder().build();
                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll();
                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                            this, cameraSelector, preview, imageCapture);
                } catch(Exception ex) {
                    Log.e("BinhDQ", "Use case binding failed");
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },ContextCompat.getMainExecutor(this));
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