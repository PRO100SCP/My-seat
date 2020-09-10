package service;

import model.SchoolClass;
import model.Student;

import java.util.List;

public class SchoolClassService {

    private SchoolClass schoolClass;

    public SchoolClassService(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public SchoolClass create(long id, String name, int studAmount, List<Student> studentList){
        SchoolClass schoolClass =new SchoolClass();
        schoolClass.setName(name);
        schoolClass.setStudAmount(studAmount);
        schoolClass.setStudents(studentList);
        schoolClass.setId(id);
        return schoolClass;
    }
    public SchoolClass edit(String name, int studAmount, List<Student> studentList){
        this.schoolClass.setName(name);
        this.schoolClass.setStudAmount(studAmount);
        this.schoolClass.setStudents(studentList);
        return this.schoolClass;
    }
    public void addToStudentList(Student student){
        this.schoolClass.getStudents().add(student);
    }

    public void removeFromStudentList(Student student){
        this.schoolClass.getStudents().remove(student);
    }

    public Student getFromStudentList(long id){
        for (int i = 0; i < this.schoolClass.getStudents().size(); i++) {
            if (id == this.schoolClass.getStudents().get(i).getId()){
                return this.schoolClass.getStudents().get(i);
            }
        }
        return null;
    }
}
