package com.example.acourseworkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class StudentDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        EditText inputName = findViewById(R.id.inputNameDetail);
        EditText inputAge = findViewById(R.id.inputAgeDetail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",0);

        inputName.setText(name);
        inputAge.setText(String.valueOf(age));


    }
}