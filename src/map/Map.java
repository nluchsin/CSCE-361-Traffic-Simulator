package map;
import java.util.ArrayList;

public class Map {
	ArrayList<Intersection> intersections;		//holds all of the intersections on the map

	public Map() {
		intersections = new ArrayList<Intersection>();
	}
	
	/*
	 * adds an intersection to the arraylist of intersections
	 */
	public void addIntersection(int index) {
		Intersection intersection = new Intersection();
		intersections.add(index, intersection);
	}
	
	 /*
	  * changes the type of an intersection to the user's choice
	  */
	public void changeIntersection(int index, int type) {
		intersections.get(index).changeIntersection(type);  
	}
	
	public Intersection getIntersection(int index) {
		return this.intersections.get(index);
	}
}
