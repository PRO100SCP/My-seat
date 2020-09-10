package model;

import java.util.List;

public class SchoolClass {
    private long id;
    private String name;
    private int studAmount;
    private List <Student> students;

    public SchoolClass(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudAmount() {
        return studAmount;
    }

    public void setStudAmount(int studAmount) {
        this.studAmount = studAmount;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
