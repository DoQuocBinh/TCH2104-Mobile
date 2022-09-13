package fpt.grw.reviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
            DatabaseHelper dbHelpler = new DatabaseHelper(this);
            List<Exam> exams = dbHelpler.getExams();

            ArrayAdapter<Exam> arrayAdapter = new ArrayAdapter<Exam>(this,
                            android.R.layout.simple_list_item_1,exams);
            ListView examList = findViewById(R.id.examListView);
            examList.setAdapter(arrayAdapter);
        });

    }
}