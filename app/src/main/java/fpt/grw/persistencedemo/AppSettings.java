package fpt.grw.persistencedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        Button btn = findViewById(R.id.showSetting);
        btn.setOnClickListener(view -> {
            String msg = PreferenceManager.getDefaultSharedPreferences(AppSettings.this)
                    .getString("signature", null);
            Toast.makeText(AppSettings.this,msg,Toast.LENGTH_SHORT).show();
        });
    }
}