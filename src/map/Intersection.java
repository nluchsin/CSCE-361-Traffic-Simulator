package map;

public class Intersection {
	private int type;	//represents the type of intersection, 0 is Traffic Light, 1 is E-W Stop, 2 is N-S stop, 3 is all-way stop
	
	public Intersection() {
		type = 0;
	}
	
	/*
	 * Changes the type of an intersection
	 */
	void changeIntersection(int type) {
		this.setType(type);
	}
	
	/*
	 * returns the type of intersection
	 */
	public int getType() {
		if (type == 0) {
			return 4;
		}
		else if (type == 1) {
			return 1;
		}
		else if (type == 2) {
			return 2;
		}
		else if (type == 3) {
			return 0;
		}
		return 5;
	}
	
	/*
	 * changes the type of intersection
	 */
	private void setType(int newType) {
		type = newType;
	}

}
