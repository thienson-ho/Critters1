/* CRITTERS Critter3.java
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

import java.util.ArrayList;

public class Critter3 extends Critter{
	
	static int count = 0;
	static ArrayList<int[]> position = new ArrayList<int[]>();
	int index;
	int life;
	
	public Critter3() {
		count++;
		this.index = count - 1;
		int[] newVal = {0,0,this.getEnergy()};
		position.add(newVal);
		life = 1000;
	}
	
	//          2
	//       3  ^  1
	//        \ | /
	//    4 < - * - > 0
	//        / | \
	//       5  V  7
	//          6
	public Critter3(int direction, int index, int life) {
		int x = position.get(index)[0];
		int y = position.get(index)[1];
		this.life = life;
		switch (direction) {
        case 0:
        	int[] newVal0 = {x - 1, y, this.getEnergy()};
        	position.add(newVal0);
            break;

        case 1:
        	int[] newVal1 = {x - 1, y + 1, this.getEnergy()};
        	position.add(newVal1);
            break;

        case 2:
        	int[] newVal2 = {x, y + 1, this.getEnergy()};
        	position.add(newVal2);
            break;

        case 3:
        	int[] newVal3 = {x + 1, y + 1, this.getEnergy()};
        	position.add(newVal3);
            break;

        case 4:
        	int[] newVal4 = {x + 1, y, this.getEnergy()};
        	position.add(newVal4);
            break;

        case 5:
        	int[] newVal5 = {x + 1, y - 1, this.getEnergy()};
        	position.add(newVal5);
            break;

        case 6:
        	int[] newVal6 = {x, y - 1, this.getEnergy()};
        	position.add(newVal6);
            break;

        case 7:
        	int[] newVal7 = {x - 1, y - 1, this.getEnergy()};
        	position.add(newVal7);
            break;
		}
	}
	
	@Override
	public void doTimeStep() {
		if(life <= 0 || this.getEnergy() < 20) {
			int x = position.get(this.index)[0];
			int y = position.get(this.index)[1];
			int kingX = position.get(0)[0];
			int kingY = position.get(0)[1];
			
			if(x < kingX) {
				if(y < kingY) {
					walk(7);
					int[] newVal0 = {x + 1, y + 1, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}else if(y > kingY) {
					walk(1);
					int[] newVal0 = {x + 1, y - 1, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}else {
					walk(0);
					int[] newVal0 = {x + 1, y, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}
			}else if(x > kingX) {
				if(y < kingY) {
					walk(5);
					int[] newVal0 = {x - 1, y + 1, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}else if(y > kingY) {
					walk(3);
					int[] newVal0 = {x - 1, y - 1, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}else {
					walk(4);
					int[] newVal0 = {x - 1, y, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}
			}else {
				if(y < kingY) {
					walk(6);
					int[] newVal0 = {x, y + 1, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}else if(y > kingY) {
					walk(2);
					int[] newVal0 = {x, y - 1, this.getEnergy()};
		        	position.set(this.index, newVal0);
				}
			}
			
		}else {
			int rand = getRandomInt(8);
			int x = position.get(index)[0];
			int y = position.get(index)[1];
			
			for(int i = 0; i < 8; i++) {
				switch ((i + rand) % 8) {
		        case 0:
		        	int[] newVal0 = {x + 1, y, this.getEnergy()};
		        	position.set(this.index, newVal0);
		            break;

		        case 1:
		        	int[] newVal1 = {x + 1, y - 1, this.getEnergy()};
		        	position.set(this.index, newVal1);
		            break;

		        case 2:
		        	int[] newVal2 = {x, y - 1, this.getEnergy()};
		        	position.set(this.index, newVal2);
		            break;

		        case 3:
		        	int[] newVal3 = {x - 1, y - 1, this.getEnergy()};
		        	position.set(this.index, newVal3);
		            break;

		        case 4:
		        	int[] newVal4 = {x - 1, y, this.getEnergy()};
		        	position.set(this.index, newVal4);
		            break;

		        case 5:
		        	int[] newVal5 = {x - 1, y + 1, this.getEnergy()};
		        	position.set(this.index, newVal5);
		            break;

		        case 6:
		        	int[] newVal6 = {x, y + 1, this.getEnergy()};
		        	position.set(this.index, newVal6);
		            break;

		        case 7:
		        	int[] newVal7 = {x + 1, y + 1, this.getEnergy()};
		        	position.set(this.index, newVal7);
		            break;
				}
				
				boolean isUnique = true;
				for(int j = 0; j < position.size(); j++) {
					if(j != index && position.get(j)[0] == x && position.get(j)[1] == y) {
						j = position.size();
						isUnique = false;
					}
				}
				
				if(isUnique && life > 0) {
					life--;
					walk((i + rand) % 8);
					Critter3 newCritter = new Critter3((i+rand) %  8,this.index, 5);
					reproduce(newCritter, (i + rand + 4) % 8);
				}
			}
		}
	}
	
	
	public boolean fight(String opponent) {
		if(position.get(this.index)[0] == position.get(0)[0] && position.get(this.index)[1] == position.get(0)[1]) {
			if(this.index != 0) {
				return false;
			}
		}
		life++;
		return true;
	}
	
	public String toString() {
		return("3");
	}
	
	public static void runStats(java.util.List<Critter> critter3s) {

        int pivotX = position.get(0)[0];
        int pivotY = position.get(0)[1];
        
        int colony = 0;
        int maxEnergy = 0;
        int maxIndex = 0;
        
        for(Object obj : critter3s) {
            Critter3 c = (Critter3) obj;
            colony++;
            int energy = position.get(c.index)[2];
            if(energy > maxEnergy) {
            	maxEnergy = energy;
            	maxIndex = c.index;
            }
        }
                
        if(colony > 1) {
        	System.out.println("The greatest among a colony of " + colony + " has an energy of " + maxEnergy);
        	System.out.println("It resides at [" + position.get(maxIndex)[0] + "," + position.get(maxIndex)[1] + "], and pivots about [" + position.get(0)[0] + "," + position.get(0)[1] + "]");
        }else if(colony < 1) {
        	System.out.println("R.I.P. Colony");
        }else {
        	System.out.println(" A lone ant resides at [" + position.get(maxIndex)[0] + "," + position.get(maxIndex)[1] + "], and pivots about [" + position.get(0)[0] + "," + position.get(0)[1] + "]");
        }
	}
}

