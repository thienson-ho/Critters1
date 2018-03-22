/* CRITTERS Critter1.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <ThienSon Ho>
 * <tsh848>
 * <15505>
 * <Arjun Singh>
 * <AS78363>
 * <15505>
 * Slip days used: <0>
 * Spring 2018
 */

package assignment4;

public class Critter1 extends Critter {
    private int venom;  //venom == instant win in fights
    private int babies;

    public Critter1() {
        babies = 0;
    }

    public int getBabies() {
        return babies;
    }

    @Override
    public void doTimeStep() {
        if(getEnergy() >= 20) {
            Critter1 child = new Critter1();
            reproduce(child, Critter.getRandomInt(8));
            babies++;
        }
    }

    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("1")) {
            return true;
        } else {
            run(Critter.getRandomInt(8));
            return false;
        }
    }

    public static void runStats(java.util.List<Critter> critter1s) {
        int totalBabies = 0;

        for (Object obj : critter1s) {
            Critter1 c = (Critter1) obj;
            totalBabies += c.babies;

        }

        System.out.print("" + critter1s.size() + " total Critter1's    ");
        System.out.println("" + totalBabies + " total babies made");
    }

    @Override
    public String toString() {
        return "1";
    }
}
