package fpt.grw.reviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputDate  = findViewById(R.id.inputDate);
        EditText inputDescrip   = findViewById(R.id.inputDescription);

        Button saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(view -> {
            String name = inputName.getText().toString();
            String exam_date = inputDate.getText().toString();
            String description = inputDescrip.getText().toString();
            DatabaseHelper dbHelpler = new DatabaseHelper(this);
            dbHelpler.insertExam(name,exam_date,description);
            Toast.makeText(this,"Exam was saved!",Toast.LENGTH_SHORT).show();
        });

        Button viewButton = findViewById(R.id.btnView);
        viewButton.setOnClickListener(view ->{
            TextView tv = findViewById(R.id.textView);
            DatabaseHelper dbHelpler = new DatabaseHelper(this);
            tv.setText(dbHelpler.getDetails());
        });

    }
}