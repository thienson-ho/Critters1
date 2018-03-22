/* CRITTERS Crtter2.java
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

public class Critter2 extends Critter{

    private int walks;
    private int runs;
    private int fights;

    public Critter2(){
        walks = 0;
        runs = 0;
        fights = 0;

    }

    //flips a coin to either walk or run
    private void walkOrRun() {
        int rand = Critter.getRandomInt(2);

        if(rand == 0) {
            run(getRandomInt(8));
            runs++;
        } else {
            walk(getRandomInt(8));
            walks++;
        }
    }

    @Override
    public void doTimeStep() {
        walkOrRun();
        if(getEnergy()> 100) {
            Critter2 child = new Critter2();
            reproduce(child, Critter.getRandomInt(8));
        }
    }

    @Override
    public boolean fight(String opponent) {
        fights++;

        //tries to run if it is the same species
        if(opponent.equals("2")) {
            walkOrRun();
            return false;
        } else {
            return true;
        }

    }


    public static void runStats(java.util.List<Critter> critter2s) {

        int totalWalks =  0;
        int totalRuns = 0;
        int totalFights = 0;

        for (Object obj : critter2s) {
            Critter2 c = (Critter2) obj;
            totalWalks += c.walks;
            totalRuns += c.runs;
            totalFights += c.fights;
        }

        System.out.print("" + critter2s.size() + " total Critter1's    ");
        System.out.print("Runs: " + totalRuns + "    ");
        System.out.print("Walks: " + totalWalks + "    ");
        System.out.println("Fights: " + totalFights);
    }

    @Override
    public String toString() {
        return "2";
    }
}
