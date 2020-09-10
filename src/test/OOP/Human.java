package test.OOP;

public class Human extends Being{
    private Action action;
    private String name;
    public void voice (){
        System.out.println("Hhhhhhhi");
        action.doing();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Human(String name, String name1) {
        super(name);
        this.name = name1;
    }

    public Human(String name) {
        this.name = name;
        this.action = new BeingAction(this);
    }

    public Human() {
    }
}
