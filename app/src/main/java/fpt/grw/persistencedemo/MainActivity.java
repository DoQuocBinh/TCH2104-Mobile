package fpt.grw.persistencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSharedPreferences();
            }
        });
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSharedPreferences();
            }
        });
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AppSettings.class);
                startActivity(intent);
            }
        });

    }
    private void showSharedPreferences() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myfile", Context.MODE_PRIVATE);
        String name = pref.getString("user_name","");
        String email = pref.getString("user_email","");
        Toast.makeText(getApplicationContext(),name + ";" + email,Toast.LENGTH_SHORT).show();
    }

    private void saveSharedPreferences() {
        EditText nameEditText = findViewById(R.id.name);
        String name = nameEditText.getText().toString();
        EditText emailEditText = findViewById(R.id.email);
        String email = emailEditText.getText().toString();
        EditText dobEditText = findViewById(R.id.dob);
        String dob = dobEditText.getText().toString();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_name", name);
        editor.putString("user_email", email);
        editor.putString("user_dob",dob);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Information saved!",Toast.LENGTH_SHORT).show();
    }
}