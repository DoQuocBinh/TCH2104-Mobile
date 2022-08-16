package fpt.grw.persistencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        //load Fragment to Activity
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.idFrameLatout, new SettingsFragment())
                .commit();
    }
}