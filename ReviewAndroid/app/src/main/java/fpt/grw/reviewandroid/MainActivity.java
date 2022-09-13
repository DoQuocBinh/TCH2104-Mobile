package fpt.grw.reviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

            examList.setOnItemClickListener((adapterView, view1, i, l) -> {
                Exam selectedExam = exams.get(i);
                openExamDetail(selectedExam);
            });
        });

    }

    private void openExamDetail(Exam selectedExam) {
        Intent intent = new Intent(this,ExamDetails.class);
        intent.putExtra("name",selectedExam.name);
        intent.putExtra("id",selectedExam.id);
        intent.putExtra("exam_date",selectedExam.exam_date);
        intent.putExtra("description",selectedExam.description);
        startActivity(intent);
    }
}