package fpt.grw.reviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    }
}