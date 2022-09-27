package entities;

import androidx.annotation.NonNull;

public class ExamDetailEntity {
    private int exam_id;
    private String exam_name;

    @NonNull
    @Override
    public String toString() {
        return exam_name + "-" + detail_question;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getExam_description() {
        return exam_description;
    }

    public void setExam_description(String exam_description) {
        this.exam_description = exam_description;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public String getDetail_question() {
        return detail_question;
    }

    public void setDetail_question(String detail_question) {
        this.detail_question = detail_question;
    }

    public String getDetail_picture_url() {
        return detail_picture_url;
    }

    public void setDetail_picture_url(String detail_picture_url) {
        this.detail_picture_url = detail_picture_url;
    }

    private String exam_description;
    private int detail_id;
    private String detail_question;
    private String detail_picture_url;

}
