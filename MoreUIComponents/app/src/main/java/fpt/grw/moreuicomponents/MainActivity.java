package fpt.grw.moreuicomponents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner sp = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,countries);
        sp.setAdapter(adapter);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPressed();
            }
        });

        BottomNavigationView nav_bottom = findViewById(R.id.bottomNavigationView);
        nav_bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.music:
                        Toast.makeText(getApplicationContext(),"Music",Toast.LENGTH_SHORT).show();
                        openMusicPage();
                        return true;
                    case R.id.movie:
                        Toast.makeText(getApplicationContext(),"Movie",Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }
    private void openMusicPage() {
        Intent intent = new Intent(this,Music.class);
        startActivity(intent);
    }

    private void buttonPressed() {
        TextInputLayout textInput = findViewById(R.id.textInputLayout);
        String name = textInput.getEditText().getText().toString();
        Toast.makeText(getApplicationContext(),"Hello " + name
                        ,Toast.LENGTH_LONG).show();

    }

    private String[] countries = new String[] {"Vietnam","Singapor","Malaysia"};
}