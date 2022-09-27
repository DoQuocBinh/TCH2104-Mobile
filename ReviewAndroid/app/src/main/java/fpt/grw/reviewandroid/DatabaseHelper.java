package fpt.grw.reviewandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import entities.ExamDetailEntity;
import entities.ExamEntity;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Exams";
    private static final String TABLE_EXAM = "Exams";
    private static final String TABLE_DETAILS= "ExamDetails";

    public static final String EXAM_ID = "exam_id";
    public static final String EXAM_NAME = "exam_name";
    public static final String EXAM_DATE = "exam_date";
    public static final String DESCRIPTION = "exam_description";


    private static final String DETAIL_ID = "detail_id";
    private static final String DETAIL_QUESTION = "detail_question";
    private static final String DETAIL_PICTURE_URL = "detail_pic_url";

    private SQLiteDatabase database;

    private static final String DETAIL_TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s INTEGER, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            TABLE_DETAILS, DETAIL_ID, EXAM_ID,DETAIL_QUESTION , DETAIL_PICTURE_URL);

    private static final String EXAM_TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            TABLE_EXAM, EXAM_ID, EXAM_NAME, EXAM_DATE, DESCRIPTION);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 7);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EXAM_TABLE_CREATE);
        db.execSQL(DETAIL_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertDetail(int examId,String question,String pictureURL){
        ContentValues rowValues = new ContentValues();
        rowValues.put(EXAM_ID,examId);
        rowValues.put(DETAIL_QUESTION,question);
        rowValues.put(DETAIL_PICTURE_URL,pictureURL);
        return database.insertOrThrow(TABLE_DETAILS, null, rowValues);
    }

    public long insertExam(String name, String exam_date, String description) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(EXAM_NAME, name);
        rowValues.put(EXAM_DATE, exam_date);
        rowValues.put(DESCRIPTION, description);

        return database.insertOrThrow(TABLE_EXAM, null, rowValues);
    }

    public List<ExamDetailEntity>  getExamDetails(int examId) {
        String MY_QUERY = "SELECT b.detail_id, b.exam_id, a.exam_name,b.detail_pic_url, b.detail_question FROM "+ TABLE_EXAM+ " a INNER JOIN "+
                TABLE_DETAILS + " b ON a.exam_id=b.exam_id WHERE a.exam_id=?";
        Cursor cursor = database.rawQuery(MY_QUERY,new String[]{String.valueOf(examId)});


        List<ExamDetailEntity> results = new ArrayList<ExamDetailEntity>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int detail_id = cursor.getInt(0);
            int exam_id = cursor.getInt(1);
            String exam_name = cursor.getString(2);
            String detail_picture_url = cursor.getString(3);
            String detail_question = cursor.getString(4);

            ExamDetailEntity examDetailEntity = new ExamDetailEntity();
            examDetailEntity.setDetail_id(detail_id);
            examDetailEntity.setExam_id(exam_id);
            examDetailEntity.setExam_name(exam_name);
            examDetailEntity.setDetail_picture_url(detail_picture_url);
            examDetailEntity.setDetail_question(detail_question);
            results.add(examDetailEntity);

            cursor.moveToNext();
        }
        return results;
    }

    public List<ExamEntity>  getExams() {
        Cursor cursor = database.query(TABLE_EXAM, new String[] {EXAM_ID, EXAM_NAME, EXAM_DATE, DESCRIPTION},
                null, null, null, null, EXAM_NAME);

        List<ExamEntity> results = new ArrayList<ExamEntity>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String exam_date = cursor.getString(2);
            String description = cursor.getString(3);

            ExamEntity examEntity = new ExamEntity(id,name,exam_date,description);
            results.add(examEntity);

            cursor.moveToNext();
        }
        return results;
    }

}
