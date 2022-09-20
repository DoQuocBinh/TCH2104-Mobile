package entities;

public class ExamDetailsEntity {
    private int detailId;
    private int examId;

    public ExamDetailsEntity(int detailId, int examId, String question, String pictureURL) {
        this.detailId = detailId;
        this.examId = examId;
        this.question = question;
        this.pictureURL = pictureURL;
    }

    private String question;
    private String pictureURL;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
