package test.OOP;

public class BeingAction implements Action{
    private Being being;
    public BeingAction(Being being) {
        this.being = being;
    }

    @Override
    public void doing() {
        System.out.println(being.getName()+" крадется");
    }
}
