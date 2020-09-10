package service;

import model.Subject;

public class SubjectService {

    private Subject subject;

    public SubjectService(Subject subject) {
        this.subject = subject;
    }

    public Subject create(long idSub, String subName, double averageMark){
        Subject subject=new Subject();
        subject.setId(idSub);
        subject.setName(subName);
        subject.setAverageMark(averageMark);
        return subject;
    }

    public Subject edit(String subName, double averageMark){
        this.subject.setAverageMark(averageMark);
        this.subject.setName(subName);
        return subject;
    }


}
