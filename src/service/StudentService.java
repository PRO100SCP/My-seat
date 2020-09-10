package service;

import model.Student;
import model.Subject;

import java.util.List;

public class StudentService {

    private Student student;

    public StudentService(Student student) {
        this.student = student;
    }

    public Student create(long idStudent, String firstName, String lastName, int age, int height, String special, double vision, List <Subject> subjectList){
        Student student=new Student();
        student.setId(idStudent);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setHeight(height);
        student.setSpecial(special);
        student.setEyesight(vision);
        student.setSubjects(subjectList);
        return student;
    }

    public Student edit(String firstName, String lastName, int age, int height, String special, double vision, List <Subject> subjectList){
        this.student.setFirstName(firstName);
        this.student.setLastName(lastName);
        this.student.setAge(age);
        this.student.setHeight(height);
        this.student.setSpecial(special);
        this.student.setEyesight(vision);
        this.student.setSubjects(subjectList);
        return this.student;
    }

    public void addToSubjectList(Subject subject){
        this.student.getSubjects().add(subject);
    }
    public void removeFromSubjectList(Subject subject){
        this.student.getSubjects().remove(subject);
    }
    public Subject getSubjectFromList(long id){
        for(int i=0; i<this.student.getSubjects().size(); i++){
            if (id==this.student.getSubjects().get(i).getId()){
                return this.student.getSubjects().get(i);
            }
        }
    return null;
    }
}
