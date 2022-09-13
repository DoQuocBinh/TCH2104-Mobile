package fpt.grw.reviewandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Exams";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EXAM_DATE = "exam_date";
    public static final String DESCRIPTION = "description";

    private SQLiteDatabase database;

    private static final String DATABASE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            DATABASE_NAME, ID, NAME, EXAM_DATE, DESCRIPTION);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertExam(String name, String exam_date, String description) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME, name);
        rowValues.put(EXAM_DATE, exam_date);
        rowValues.put(DESCRIPTION, description);

        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }

    public String getDetails() {
        Cursor results = database.query(DATABASE_NAME, new String[] {ID, NAME, EXAM_DATE, DESCRIPTION},
                null, null, null, null, NAME);

        String resultText = "";

        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);

            resultText += id + " " + name + " " + dob + " " + email + "\n";

            results.moveToNext();
        }

        return resultText;

    }
}
