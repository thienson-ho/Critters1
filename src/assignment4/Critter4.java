package assignment4;

import java.util.ArrayList;

public class Critter4 extends Critter{
	
	int rand;
	int proCreate;
	int reload;
	
	public Critter4() {
		rand = Critter.getRandomInt(8);
		proCreate = 0;
		reload = 5;
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
				Critter4 aCritter = new Critter4(proCreate);
				
				reproduce(aCritter, (i+rand)%8);
			}
		}
	}
	public boolean fight(String opponent) {
		if(this.getEnergy() > 50) {
			proCreate--;
			return true;
		}
		return false;
	}
	
	public String toString() {
		return("4");
	}
}
