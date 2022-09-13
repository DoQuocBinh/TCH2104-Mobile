package fpt.grw.reviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ExamDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String exam_date = intent.getStringExtra("exam_date");

        TextView inputName = findViewById(R.id.inputName2);
        TextView inputExamDate = findViewById(R.id.inputExamDate2);
        inputName.setText(name);
        inputExamDate.setText(exam_date);
    }
}