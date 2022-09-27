package fpt.grw.reviewandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import entities.ExamEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputDate  = findViewById(R.id.inputDate);
        EditText inputDescrip   = findViewById(R.id.inputDescription);

        inputDate.setOnFocusChangeListener((view, b) -> {
            if(b){
                MyDatePicker dlg = new MyDatePicker();
                dlg.setExamDate(inputDate);
                dlg.show(getSupportFragmentManager(),"dateTimePicker");

            }
        });

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
            List<ExamEntity> examEntities = dbHelpler.getExams();

            ArrayAdapter<ExamEntity> arrayAdapter = new ArrayAdapter<ExamEntity>(this,
                            android.R.layout.simple_list_item_1, examEntities);
            ListView examList = findViewById(R.id.examListView);
            examList.setAdapter(arrayAdapter);

            examList.setOnItemClickListener((adapterView, view1, i, l) -> {
                ExamEntity selectedExamEntity = examEntities.get(i);
                openExamDetail(selectedExamEntity);
            });
        });

    }

    private void openExamDetail(ExamEntity selectedExamEntity) {
        Intent intent = new Intent(this,ExamDetails.class);
        intent.putExtra("name", selectedExamEntity.getName());
        intent.putExtra("id", selectedExamEntity.getId());
        intent.putExtra("exam_date", selectedExamEntity.getExam_date());
        intent.putExtra("description", selectedExamEntity.getDescription());
        startActivity(intent);
    }

    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        public EditText getExamDate() {
            return examDate;
        }
        public void setExamDate(EditText examDate) {
            this.examDate = examDate;
        }
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        EditText examDate;
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String dateValue =  i2 + "/" + i1 + "/" + i;
            examDate.setText(dateValue);
        }
    }
}