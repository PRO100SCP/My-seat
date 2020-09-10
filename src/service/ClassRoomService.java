package service;

import model.SchoolClass;
import model.ClassRoom;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomService {

    private ClassRoom classRoom;

    public ClassRoomService(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public ClassRoom create(long id, String name, int desksNum, SchoolClass schoolClass){
        ClassRoom classRoom=new ClassRoom(desksNum);
        classRoom.setName(name);
        classRoom.setDeskAmount(desksNum);
        classRoom.setId(id);
        classRoom.setSchoolClass(schoolClass);
        return classRoom;
    }

    public ClassRoom create(long id, String name, int desksNum){
        ClassRoom classRoom=new ClassRoom(desksNum);
        classRoom.setName(name);
        classRoom.setDeskAmount(desksNum);
        classRoom.setId(id);
        return classRoom;
    }
    public ClassRoom edit(ClassRoom classRoom, String classNumber, int desksNum, SchoolClass schoolClass){
        classRoom.setName(classNumber);
        classRoom.setDeskAmount(desksNum);
        classRoom.setSchoolClass(schoolClass);
        return classRoom;
    }

    public void putInDesk(Student student, int id){
        ClassRoom.Desk desk = classRoom.getDesks().get(id);
        if (desk.getRightSeat() == null){
            desk.setRightSeat(student);
        }else if (desk.getLeftSeat() == null){
                desk.setLeftSeat(student);
        }
        classRoom.getDesks().set(id, desk);
    }
    public void putInNextDesk(Student student){
        for (ClassRoom.Desk d:classRoom.getDesks()){
            if (d.getRightSeat() == null){
                d.setRightSeat(student);
                break;
            }else if (d.getLeftSeat() == null){
                d.setLeftSeat(student);
                break;
            }
        }
    }

}
