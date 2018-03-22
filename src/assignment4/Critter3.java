package assignment4;

import java.util.ArrayList;

public class Critter3 extends Critter{
	
	static int count = 0;
	static ArrayList<int[]> position = new ArrayList<int[]>();
	int index;
	int life = 10;
	
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
				
				if(isUnique) {
					walk((i + rand) % 8);
					Critter3 newCritter = new Critter3();
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
		return true;
	}
	
	public String toString() {
		return("3");
	}
}

