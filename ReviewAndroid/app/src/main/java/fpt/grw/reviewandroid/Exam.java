package fpt.grw.reviewandroid;

import androidx.annotation.NonNull;

public class Exam {
    protected int id;
    protected String name;

    public Exam(int id, String name, String exam_date, String description) {
        this.id = id;
        this.name = name;
        this.exam_date = exam_date;
        this.description = description;
    }

    public Exam() {
    }

    @NonNull
    @Override
    public String toString() {
        return id + "-" +  name + "-" + exam_date + "-" + description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String exam_date;
    protected String description;
}
