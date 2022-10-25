package entities;

import androidx.annotation.NonNull;

public class Student {
    private String name;
    private int age;
    private int studentId;

    public Student(String name, int age,int studentId) {
        this.name = name;
        this.age = age;
        this.studentId =studentId;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " +   age;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
