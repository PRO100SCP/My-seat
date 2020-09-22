package model;

import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    private long id;
    private String name;
    private int deskAmount;
    private SchoolClass schoolClass;
    private List<Desk> desks;

    public ClassRoom(int deskNum) {
        this.deskAmount = deskNum;
        desks =new ArrayList<>();
        for(int i=0; i<deskNum; i++){
            Desk desk=new ClassRoom.Desk();
            desk.setId(i);
            desks.add(i, desk);
        }
    }

    public ClassRoom() {

    }

    public class Desk {
        private long id;
        private Student leftSeat;
        private Student rightSeat;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Student getLeftSeat() {
            return leftSeat;
        }

        public void setLeftSeat(Student leftSeat) {
            this.leftSeat = leftSeat;
        }

        public Student getRightSeat() {
            return rightSeat;
        }

        public void setRightSeat(Student rightSeat) {
            this.rightSeat = rightSeat;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeskAmount() {
        return deskAmount;
    }

    public void setDeskAmount(int deskAmount) {
        this.deskAmount = deskAmount;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<Desk> getDesks() {
        return desks;
    }

    public void setDesks(List<Desk> desks) {
        this.desks = desks;
    }
}
