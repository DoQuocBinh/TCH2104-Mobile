package com.example.acourseworkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import entities.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputAge = findViewById(R.id.inputAge);
        Button button = findViewById(R.id.button);
        ListView listView = findViewById(R.id.listview);

        //save click
        button.setOnClickListener(view -> {
            String name = inputName.getText().toString();
            if(name.equals("")){
                inputName.setError("Ten khong de trang!");
                return;
            }
            int age = Integer.parseInt(inputAge.getText().toString());
            if(age  <20){
                inputAge.setError("Age khong nho hon 20!");
                return;
            }
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            long id = dbHelper.insertStudent(name,age);
            Toast.makeText(this, "New student id:" + String.valueOf(id), Toast.LENGTH_SHORT).show();
            refreshListView(listView);
        });
        //show click button

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(view -> {
            refreshListView(listView);
        });

        //register event user click vao Item trong Listview
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            DatabaseHelper db = new DatabaseHelper(this);
            List<Student> dsStudent = db.getStudents();
            Student current = dsStudent.get(i);
            Intent intent = new Intent(this,StudentDetail.class);
            intent.putExtra("id",current.getStudentId());
            intent.putExtra("name",current.getName());
            intent.putExtra("age",current.getAge());
            startActivity(intent);
        });

    }

    private void refreshListView(ListView listView) {
        DatabaseHelper db = new DatabaseHelper(this);
        List<Student> dsStudent = db.getStudents();
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsStudent);
        listView.setAdapter(adapter);
    }
}