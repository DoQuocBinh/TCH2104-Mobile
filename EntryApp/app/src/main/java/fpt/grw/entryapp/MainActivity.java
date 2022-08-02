package fpt.grw.entryapp;

import androidx.appcompat.app.AppCompatActivity;

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

        Button btn = findViewById(R.id.btnOk);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayHi();
            }
        });
    }

    private void sayHi() {
        //find the the component
        EditText txtName = findViewById(R.id.txtName);
        //get the text from user's input
        String name = txtName.getText().toString();
        //display the message to user via Toast
        Toast.makeText(getApplicationContext(),"Hi " + name,Toast.LENGTH_LONG).show();

    }
}