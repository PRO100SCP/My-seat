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
        crs.putInNextDesk(schoolClass.getStudents().get(3));
        classRoomsXmlService.deleteByName("0");
        classRoomsXmlService.add(classRoom);
        classRoom=classRoomsXmlService.getById("0");
        System.out.println("Class Number: "+classRoom.getName());
        System.out.println("Class Name: " +classRoom.getSchoolClass().getName());
        for (ClassRoom.Desk desk : classRoom.getDesks()){
            String leftSeat;
            String rightSeat;
            if (desk.getLeftSeat() == null){
                leftSeat = "none";
            }
            else leftSeat = desk.getLeftSeat().getFirstName() + " " +desk.getLeftSeat().getLastName();
            if (desk.getRightSeat() == null){
                rightSeat = "none";
            }
            else rightSeat = desk.getRightSeat().getFirstName() + " " +desk.getRightSeat().getLastName();
            System.out.println("Left Seat: " +leftSeat + " || Right Seat: " + rightSeat);

        }
    }
}
