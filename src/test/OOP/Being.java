package test.OOP;

public class Being {
    private String name;
    public void voice(){
        System.out.println("Aghhrrr");
    }
    public void bite(){
        System.out.println("*Bite*");
    }

    public String getName() {
        return name;
    }

    public Being(String name) {
        this.name = name;
    }

    public Being() {
        this.name="существо";
    }
}
