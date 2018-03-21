package assignment4;

import java.util.ArrayList;

public class Critter3 extends Critter{
	
	static int count = 0;
	static ArrayList<int[]> position = new ArrayList<int[]>();
	int index;
	int life = 15;
	
	public Critter3() {
		count++;
		this.index = count - 1;
		int[] newVal = {0,0,this.getEnergy()};
		position.add(newVal);
	}
	
	//          2
	//       3  ^  1
	//        \ | /
	//    4 < - * - > 0
	//        / | \
	//       5  V  7
	//          6
	public Critter3(int direction, int index) {
		int x = position.get(index)[0];
		int y = position.get(index)[1];
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
			
		}
	}
	public boolean fight(String opponent) {
		return true;
	}
	
	public String toString() {
		return("3");
	}
	
	
}

