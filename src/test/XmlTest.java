package test;

import model.ClassRoom;
import model.SchoolClass;
import model.Student;
import service.ClassRoomService;
import service.SchoolClassService;
import service.xml.ClassRoomsXmlService;
import service.xml.SchoolClassesXmlService;

public class XmlTest {
    public static void main(String[] args) {
        SchoolClassesXmlService xml = new SchoolClassesXmlService();
        ClassRoomsXmlService classRoomsXmlService = new ClassRoomsXmlService();
        SchoolClass schoolClass = xml.getByName("7A");
        ClassRoom classRoom=new ClassRoom(5);
        classRoom.setId(0);
        classRoom.setName("54");
        classRoom.setSchoolClass(schoolClass);
        //classRoomsXmlService.add(classRoom);
        System.out.println();
        ClassRoomService crs=new ClassRoomService(classRoom);
        crs.putInNextDesk(schoolClass.getStudents().get(0));
        crs.putInNextDesk(schoolClass.getStudents().get(1));
        crs.putInNextDesk(schoolClass.getStudents().get(2));
        classRoomsXmlService.deleteByName("0");
        classRoomsXmlService.add(classRoom);
        //classRoom=classRoomsXmlService.getById("0");
        //System.out.println(classRoom.getName());
        Student student=new SchoolClassService(schoolClass).getFromStudentList(1);
        System.out.println(student.getFirstName());
    }
}
