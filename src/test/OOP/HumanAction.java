package test.OOP;

public class HumanAction implements Action {
    private Human human;
    public HumanAction(Human human) {
        this.human = human;
    }

    public void doing (){
        System.out.println(human.getName()+" прыгает");
    }
}
