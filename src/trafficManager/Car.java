package trafficManager;

public class Car {
	//starting position
	int startX;
	int startY;
	//end position
	int endX;
	int endY;
	//current position
	int currX;
	int currY;
	//the number representing the car
	int carIndex;
	//The current speed of the car
	int speed;
	int stopped;
	//manages the intersection the car is stuck at and the order it is in for that intersection
	int intersectAt;
	int intersectPlace;
	//manages when the car is slowwed
	int slowed;
	int slowMoved;
	//tracks the distance from the current 'dot' the car is on, tracks distance to next 'dot'
	int distFromCurr;
	//Keeps track of the current direction the car is heading 0 is still 1 is north,2 east, 3 south, 4 west
	int currDirection;
	int isActive;
	public Car(int startX, int startY,int endX, int endY,
			int carIndex) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.currX = startX;
		this.currY = startY;
		this.carIndex = carIndex;
		this.speed = 30;
		this.stopped = 0;
		this.distFromCurr = 0;
		this.currDirection = 0;
		this.isActive = 1;
		this.slowed = 0;
		this.slowMoved = 0;
		this.intersectAt = -1;
		this.intersectPlace = 0;
	}
	public int getIntersectAt() {
		return intersectAt;
	}
	public void setIntersectAt(int intersectAt) {
		this.intersectAt = intersectAt;
	}
	public int getIntersectPlace() {
		return intersectPlace;
	}
	public void setIntersectPlace(int intersectPlace) {
		this.intersectPlace = intersectPlace;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public int getEndX() {
		return endX;
	}
	public void setEndX(int endX) {
		this.endX = endX;
	}
	public int getEndY() {
		return endY;
	}
	public void setEndY(int endY) {
		this.endY = endY;
	}
	public int getCurrX() {
		return currX;
	}
	public void setCurrX(int currX) {
		this.currX = currX;
	}
	public int getCurrY() {
		return currY;
	}
	public void setCurrY(int currY) {
		this.currY = currY;
	}
	public int getCarIndex() {
		return carIndex;
	}
	public void setCarIndex(int carIndex) {
		this.carIndex = carIndex;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getStopped() {
		return stopped;
	}
	public void setStopped(int stopped) {
		this.stopped = stopped;
	}
	public int getDistFromCurr() {
		return distFromCurr;
	}
	public void setDistFromCurr(int distFromCurr) {
		this.distFromCurr = distFromCurr;
	}
	public int getCurrDirection() {
		return currDirection;
	}
	public void setCurrDirection(int currDirection) {
		this.currDirection = currDirection;
	}
	public int getSlowed() {
		return slowed;
	}
	public void setSlowed(int slowed) {
		this.slowed = slowed;
	}
	public int getSlowMoved() {
		return slowMoved;
	}
	public void setSlowMoved(int slowMoved) {
		this.slowMoved = slowMoved;
	}
	public void reset() {
		this.currX = startX;
		this.currY = startY;
		this.speed = 30;
		this.stopped = 0;
		this.distFromCurr = 0;
		this.currDirection = 0;
		this.isActive = 1;
		this.slowed = 0;
		this.slowMoved = 0;
		this.intersectAt = -1;
		this.intersectPlace = 0;
	}
}

