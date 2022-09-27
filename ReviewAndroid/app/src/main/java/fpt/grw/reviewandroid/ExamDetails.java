package fpt.grw.reviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import entities.ExamDetailEntity;

public class ExamDetails extends AppCompatActivity {
    ProgressDialog progressDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String exam_date = intent.getStringExtra("exam_date");

        TextView inputName = findViewById(R.id.inputName2);
        inputName.setText(name);

        Button btnPrev = findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(view -> {
            EditText inputURL = findViewById(R.id.inputPictureURL);

            ImageView imageView = findViewById(R.id.imageView);
            DownloadImageTask task = new DownloadImageTask(progressDlg,this,imageView);
            task.execute(inputURL.getText().toString());

        });

        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(view -> {
            int exam_id = intent.getIntExtra("id",0);
            EditText inputURL = findViewById(R.id.inputPictureURL);
            EditText inputQuestion = findViewById(R.id.inputExamQuestion);

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            long detail_id = dbHelper.insertDetail(exam_id,inputQuestion.getText().toString(),
                    inputURL.getText().toString());

            Toast.makeText(this, String.valueOf(detail_id), Toast.LENGTH_SHORT).show();

        });
        Button btnViewAll = findViewById(R.id.btnViewAll);
        btnViewAll.setOnClickListener(view -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            int examId = intent.getIntExtra("id",0);
            List<ExamDetailEntity> exam_details = dbHelper.getExamDetails(examId);
            ArrayAdapter<ExamDetailEntity> adapter = new ArrayAdapter<ExamDetailEntity>
                        (this, android.R.layout.simple_list_item_1,exam_details);

            ListView listView = findViewById(R.id.exam_detail_listview);
            listView.setAdapter(adapter);

        });
    }
}