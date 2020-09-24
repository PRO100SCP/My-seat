package test;

import model.ClassRoom;
import model.SchoolClass;
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
        classRoomsXmlService.add(classRoom);
        System.out.println();
    }
}
