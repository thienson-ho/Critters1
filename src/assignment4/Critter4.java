package assignment4;

import java.util.ArrayList;

public class Critter4 extends Critter{
	
	int rand;
	
	public Critter4() {
		rand = Critter.getRandomInt(8);
	}

	
	@Override
	public void doTimeStep() {
		
		for(int i = 0; i < 8; i++) {
			Critter4 aCritter = new Critter4();
			reproduce(aCritter, (i+rand)%8);
		}
	}
	public boolean fight(String opponent) {
		if(this.getEnergy() > 50) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return("4");
	}
}
