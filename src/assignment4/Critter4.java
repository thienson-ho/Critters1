/* CRITTERS Critter4.java
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
 * 
 * The Critter4 class describes a vicious Critter that multiplies and eats all the Algae it can. It is like a microbe
 * that only feeds on algae, it doesnt  eat any other organism. It will rapidly teraform the map with this and potentially
 * feed many predators, a bloom.  
 *  
 */
package assignment4;

import java.util.ArrayList;

public class Critter4 extends Critter{
	
	int rand;
	int proCreate;
	int reload;
	int algaeKill;
	
	public Critter4() {
		rand = Critter.getRandomInt(8);
		proCreate = -5;
		reload = 5;
		algaeKill = 0;
	}
	
	public Critter4(int limit) {
		rand = Critter.getRandomInt(8);
		proCreate = limit;
		reload = limit;
	}

	
	@Override
	public void doTimeStep() {
		if(proCreate >= 3) {
			reload--;
		}
		if(reload == 0) {
			proCreate = 0;
			reload = 5;
		}
		for(int i = 0; i < 2; i++) {
			if(proCreate < 3) {
				rand = Critter.getRandomInt(8);
				proCreate++;
				Critter4 aCritter = new Critter4(-10);
				
				reproduce(aCritter, (i+rand)%8);
			}
		}
	}
	public boolean fight(String opponent) {
		if(opponent.equals("@")) {
			algaeKill++;
			return true;
		}
		if(this.getEnergy() > 50) {
			proCreate--;
			return true;
		}
		return false;
	}
	
	public String toString() {
		return("4");
	}
	
	public static void runStats(java.util.List<Critter> critter4s) {

        int algaeFought = 0;
        int army = 0;

        for (Object obj : critter4s) {
            Critter4 c = (Critter4) obj;
            algaeFought += c.algaeKill;
            army++;
        }
        if(army > 1) {
        	System.out.println(army + " Critter4s threw hands with " + algaeFought + " @");
        }else if(army < 1) {
        	System.out.println("R.I.P. Army");
        }else if(army == 1 && critter4s.size() != 1){
        	System.out.println(army + " Critter4 threw hands with " + algaeFought + " @");
        }else {
        	System.out.println("A Critter4 threw hands with " + algaeFought + " @");
        }
    }
}
