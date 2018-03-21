package assignment4;

public class Critter3 extends Critter{
	
	//	0 -> 1
	//  ^    v
	//	3 <- 2
	int position;
	
	public Critter3() {
		position = 0;
	}
	
	@Override
	public void doTimeStep() {
		if(position == 0) {
			walk(0);
			position = 1;
			
		}else if(position == 1) {
			walk(6);
			position = 2;
			
		}else if(position == 2) {
			walk(4);
			position = 3;
			
		}else {
			walk(2);
			position = 0;
			
		}
	}
	public boolean fight(String opponent) {
		return true;
	}
	
	public String toString() {
		return("3");
	}
	
	
}
