package assignment4;

public class Critter1 extends Critter {
    private int venom;  //venom == instant win in fights

    public Critter1() {
    }

    @Override
    public void doTimeStep() {

    }

    @Override
    public boolean fight(String opponent) {

        return false;
    }

    @Override
    public String toString() {
        return "1";
    }
}
