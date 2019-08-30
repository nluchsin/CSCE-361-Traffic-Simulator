package trafficManager;
import java.util.ArrayList;
import java.util.List; 
import map.Intersection;
import map.Map;
//NOTE: This is still incomplete and is just meant to give a general idea of what it will be like
public class Traffic {
	//The map of pacland translated to a 2D array, 0 represents a 'wall', 
	//1 represents a road
	//2,3,...,25 represent each individual traffic intersection
	/*
	public static int[][] cityMapFAKE = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,2,1,1,1,1,1,1,0,0,1,1,1,1,1,1,3,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,4,1,1,1,1,5,1,1,6,1,1,7,1,1,8,1,1,9,1,1,10,1,1,1,1,11,0},
			{0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},
			{0,1,1,1,1,1,12,0,0,1,1,1,1,0,0,1,1,1,1,0,0,13,1,1,1,1,1,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,1,1,1,1,1,14,1,1,1,1,1,1,0,0,1,1,1,1,1,1,15,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,1,1,0,0,16,1,1,17,1,1,18,1,1,19,1,1,20,1,1,21,0,0,1,1,1,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
			{0,1,1,22,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,23,1,1,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,24,1,1,25,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
	};*/
	public static int[][] cityMap = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,4,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,22,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0},
			{0,2,1,1,1,5,1,1,12,1,1,1,1,1,1,1,1,1,1,1,14,1,1,16,1,1,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0},
			{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0},
			{0,1,0,0,0,6,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,17,1,1,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0},
			{0,1,1,1,1,7,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,18,0,0,1,1,1,24,0},
			{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0},
			{0,1,1,1,1,8,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,19,0,0,1,1,1,25,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0},
			{0,1,0,0,0,9,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,20,1,1,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0},
			{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0},
			{0,3,1,1,1,10,1,1,13,1,1,1,1,1,1,1,1,1,1,1,15,1,1,21,1,1,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,23,0,0,1,0},
			{0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0},
			{0,1,1,1,1,11,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			
	};
		//measures the current time spent traveling in seconds
	public static int timer = 0;
	public static List<Car> activeCars = new ArrayList<Car>(); 
	public static void main(String[] args) throws InterruptedException {
		int[][] cars = {
				{1,1,1,29},
				{1,29,1,1},
				{17,29,1,3},
				{5,29,11,8}		
		};
		//let 0 = 4 stop, 1 east west, 2 north south, 3 traffic sigs (east west), 4 traffic sigs (north south)
		int[] intersects = {1,1,1,1,2,2,2,2,0,0,0,0,3,3,3,3,1,1,1,1,4,4,4,4};
		runSimul(intersects,cars);
	}
	public static void runSimul(int[] intersections, int[][] cars) throws InterruptedException {
		/*checks if all the vehicles have reached their destination, 0 means false
		1 means true*/
		int allArrived = 0;
		//instantiate all the cars
		for(int i = 0;i<cars.length;i++) {
			activeCars.add(new Car(cars[i][0],cars[i][1],cars[i][2],cars[i][3],i));
		}
		while(allArrived == 0) {
			//changes traffic signals
			//checks if the traffic lights just switched their lights
			int justSwitched = 0;
			if(timer % 120 == 0) {
				System.out.println("Traffic switch");
				justSwitched = 1;
				for(int i=0;i<intersections.length;i++) {
					if(intersections[i] == 3) {
						intersections[i] = 4;
					}else if(intersections[i] == 4) {
						intersections[i] = 3;
					}
				}
			}
			//does a round of movement
			allArrived = 1;
			for(Car currCar : activeCars) {
				if(currCar.getCurrX() == currCar.getEndX() && currCar.getCurrY() == currCar.getEndY()) {
					currCar.setIsActive(0);
				} 
				if(currCar.getIsActive() == 1) {
					if(currCar.getStopped() == 1) {
						System.out.println("Car " + currCar.getCarIndex() + " is stopped.");
						//lets the car go if it is stuck at a traffic light and it just switched
						if(currCar.getIntersectAt() >= 0) {
							//handles 4-way intersections
							//TODO: update for east west and north south intersections
							if(intersections[currCar.getIntersectAt()] < 0) {
								//moves car when it is at "front" of intersection
								if(currCar.getIntersectPlace()<=0) {
									currCar.setStopped(0);
								}
								System.out.println("Place is: " + currCar.getIntersectPlace());
								//brings cars closer to the "front" of the intersection
								currCar.setIntersectPlace(currCar.getIntersectPlace()-1);
							}
							
							//handles lights intersections
							else if(justSwitched == 1) {
								
								if(intersections[currCar.getIntersectAt()] == 3 ||intersections[currCar.getIntersectAt()] == 4) {
									currCar.setStopped(0);
								}
							}
						}
						currCar.setSlowed(1);
					} 
					if(currCar.getStopped() == 0) {
						//confirms that the car is not currently at an intersection
						currCar.setIntersectAt(-1);
						
						move(currCar, intersections);
						
					}
					//System.out.println("End is at X:" + currCar.getEndX() + " Y:" + currCar.getEndY());
					System.out.println("Car " + currCar.getCarIndex() + " Is currently at X:" + currCar.getCurrX() + " Y:" + currCar.getCurrY() + " Direction:" + currCar.getCurrDirection());
					//at least one car has not reached its destination
					allArrived = 0;
				}
			}
			if(allArrived == 0) {
				timer += 15;
				//System.out.println("Current Time Passed in seconds: " + timer);
				//System.out.println("A round is done");
				
			}
			
			Thread.sleep(10);
			
		}
	}
	//NOTE: It takes a care 1 minute to travel 1/2 of a mile, so it takes 15 seconds for it to travel one 'dot'
	public static int move(Car movingCar, int[] intersections) {
		int changedIntersect = -1;
		int xSwitch=0;
		int ySwitch=0;
		
		if(movingCar.getCurrDirection() == 1) {
			xSwitch = 0;
			ySwitch = -1;
		}
		else if(movingCar.getCurrDirection() == 2) {
			xSwitch = 1;
			ySwitch = 0;
		}
		else if(movingCar.getCurrDirection() == 3) {
			xSwitch = 0;
			ySwitch = 1;
		}
		else if(movingCar.getCurrDirection() == 4) {
			xSwitch = -1;
			ySwitch = 0;
		}
		
		//if the car has no direction, this should only occur at the very start of a car's movement
		if(movingCar.getCurrDirection() != 0) {
			
			//the car is already moving
			//This will handle if a car would run into a wall
			if(cityMap[movingCar.getCurrX()+ xSwitch][movingCar.getCurrY()+ ySwitch] == 0) {
				
				//north
				if(movingCar.getCurrDirection() == 1) {
					if(cityMap[movingCar.getCurrX()+ 1][movingCar.getCurrY()] != 0) {
						//go east
						movingCar.setCurrDirection(2);
					}
					else if(cityMap[movingCar.getCurrX()-1][movingCar.getCurrY() ] != 0) {
						//go west
						movingCar.setCurrDirection(4);
					}
					else {
						//go south
						movingCar.setCurrDirection(3);
					}
					makeMove(movingCar,movingCar.getCurrDirection(), intersections);
				}
				//east
				else if(movingCar.getCurrDirection() == 2) {
					if(cityMap[movingCar.getCurrX()][movingCar.getCurrY()+1] != 0) {
						//go south
						movingCar.setCurrDirection(3);
					}
					else if(cityMap[movingCar.getCurrX()][movingCar.getCurrY()-1 ] != 0) {
						//go north
						movingCar.setCurrDirection(1);
					}
					else {
						//go west
						movingCar.setCurrDirection(4);
					}
					changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
				}
				//south
				else if(movingCar.getCurrDirection() == 3) {
					if(cityMap[movingCar.getCurrX()+1][movingCar.getCurrY()] != 0) {
						//go east
						movingCar.setCurrDirection(2);
					}
					else if(cityMap[movingCar.getCurrX()-1][movingCar.getCurrY()] != 0) {
						//go west
						movingCar.setCurrDirection(4);
					}
					else {
						//go north
						
						movingCar.setCurrDirection(1);
					}
					changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
				}
				//west
				else if(movingCar.getCurrDirection() == 4) {
					if(cityMap[movingCar.getCurrX()][movingCar.getCurrY()+1] != 0) {
						//go south
						movingCar.setCurrDirection(3);
					}
					else if(cityMap[movingCar.getCurrX()][movingCar.getCurrY()-1 ] != 0) {
						//go north
						movingCar.setCurrDirection(1);
					}
					else {
						//go east
						movingCar.setCurrDirection(2);
					}
					changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
				}
			} else {
				//no wall in the way, keep going
				changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
			}
		}
		//the car has no current direction and has not started moving yet
		else if(movingCar.getStopped() == 0){
			if(movingCar.currY == movingCar.endY && cityMap[movingCar.getCurrX() + 1][movingCar.getCurrY()] != 0 && movingCar.currX < movingCar.endX) {
				//car can move east
				movingCar.setCurrDirection(2);
				changedIntersect =makeMove(movingCar, movingCar.getCurrDirection(), intersections);
			}
			else if (movingCar.currY == movingCar.endY && cityMap[movingCar.getCurrX() - 1][movingCar.getCurrY()] != 0 && movingCar.currX > movingCar.endX) {
				//car can move west
				movingCar.setCurrDirection(4);
				changedIntersect =makeMove(movingCar, movingCar.getCurrDirection(), intersections);
			}
			else if (movingCar.currX == movingCar.endX && cityMap[movingCar.getCurrX()][movingCar.getCurrY() + 1] != 0 && movingCar.currY < movingCar.endY) {
				//car can move south
				movingCar.setCurrDirection(3);
				changedIntersect =makeMove(movingCar, movingCar.getCurrDirection(), intersections);
			}
			else if (movingCar.currX == movingCar.endX && cityMap[movingCar.getCurrX()][movingCar.getCurrY() - 1] != 0 && movingCar.currY > movingCar.endY) {
				//car can move south
				movingCar.setCurrDirection(1);
				changedIntersect =makeMove(movingCar, movingCar.getCurrDirection(), intersections);
			}
			else if(movingCar.currY <= movingCar.endY && cityMap[movingCar.getCurrX()][movingCar.getCurrY() + 1] != 0) {
				//it can go down, now moving south
				movingCar.setCurrDirection(3);
				changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
			}
			//then checks north
			else if(movingCar.currY >= movingCar.endY && cityMap[movingCar.getCurrX()][movingCar.getCurrY() -1] != 0) {
				//it can go north
				movingCar.setCurrDirection(1);
				changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
			}
			//then east
			else if(movingCar.currX <= movingCar.endX && cityMap[movingCar.getCurrX()+1][movingCar.getCurrY()] != 0) {
				//it can go east
				movingCar.setCurrDirection(2);
				changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
			}
			//then west
			else if(movingCar.currX >= movingCar.endX && cityMap[movingCar.getCurrX()-1][movingCar.getCurrY()] != 0) {
				//it can go west
				movingCar.setCurrDirection(4);
				changedIntersect =makeMove(movingCar,movingCar.getCurrDirection(), intersections);
			}
		}
		
		return changedIntersect;
	}
	//0 represents stopped 1 north,2east,3south,4west
	static int makeMove(Car currCar, int moveDir, int[] intersections) {
		//adds the speed of the car to the distance traveled in the current 1/8 mile dot
		int changedIntersect = -1;
		if(currCar.getSlowed() == 1) {
			currCar.setSpeed(15);
			currCar.setSlowMoved(currCar.getSlowMoved() + 15);
		}
		currCar.setDistFromCurr(currCar.getDistFromCurr() + currCar.getSpeed());
		//car has moved enough to no longer be slowed
		if(currCar.getSlowMoved() >= 30) {
			currCar.setSlowMoved(0);
			currCar.setSpeed(30);
			currCar.setSlowed(0);
		}
		int xSwitch=0;
		int ySwitch=0;
		if(currCar.getCurrDirection() == 1) {
			xSwitch = 0;
			ySwitch = -2;
		}
		else if(currCar.getCurrDirection() == 2) {
			xSwitch = 2;
			ySwitch = 0;
		}
		else if(currCar.getCurrDirection() == 3) {
			xSwitch = 0;
			ySwitch = 2;
		}
		else if(currCar.getCurrDirection() == 4) {
			xSwitch = -2;
			ySwitch = 0;
		}
		if(currCar.getDistFromCurr() >= 30) {
			currCar.setDistFromCurr(currCar.getDistFromCurr() - 30);
			
			if(cityMap[currCar.getCurrX()+xSwitch][currCar.getCurrY()+ySwitch] > 1) {
				
				//System.out.println("Inter Type is: " + intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2]);
				//manages stopping of cars at intersections
				if(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 0) {
					currCar.setStopped(1);
					currCar.setIntersectAt(cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2);
					currCar.setIntersectPlace(checkQueue(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.getCurrY()+ySwitch]-2]));
				}
				//east and west stop
				else if(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 1 || intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 3 || intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 5) {
					if(moveDir == 2 || moveDir == 4) {
						currCar.setStopped(1);
						currCar.setIntersectAt(cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2);
						currCar.setIntersectPlace(checkQueue(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.getCurrY()+ySwitch]-2]));
						//checks to see if the intersection should change
						if(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 5) {
							changedIntersect = cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2;
						}
					}
					
				}
				else if(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 2 || intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 4 || intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 6) {
					if(moveDir == 1 || moveDir == 3) {
						currCar.setStopped(1);
						currCar.setIntersectAt(cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2);
						currCar.setIntersectPlace(checkQueue(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.getCurrY()+ySwitch]-2]));
						if(intersections[cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2] == 6) {
							changedIntersect= cityMap[currCar.getCurrX()+xSwitch][currCar.currY+ySwitch]-2;
						}
					}
					
				}
				
				
			}
			if(cityMap[currCar.getCurrX()+xSwitch/2][currCar.getCurrY()+ySwitch/2] > 1) {
				currCar.setCurrDirection(interPathfind(cityMap[currCar.getCurrX()+xSwitch/2][currCar.getCurrY()+ySwitch/2]-2,currCar));
			}
			currCar.setCurrY(currCar.getCurrY() + ySwitch/2);
			currCar.setCurrX(currCar.getCurrX() + xSwitch/2);
		}
			
		return changedIntersect;
		
	}
	//This function will return the direction the car should travel next based on the intersection it entered
	//1 is north,..., 4is west
	static int interPathfind(int interNum, Car currCar) {
		if(interNum < 0 || interNum > 21) {
			System.out.println("ERROR, INVALID INTERSECTION");
			return 1;
		}
		//Different sets of intersections will require different rules for pathfinding
		//The internums refer to the different intersections counting up from 0 reading up to down and left to right 
		if(interNum == 0 || interNum == 1) {
			if(distDiffY(currCar) < -3) {
				//go south
				return 3;
			} else {
				if(distDiffX(currCar)<0) {
					//go east
					return 2;
				}
				else if(distDiffX(currCar)>0){
					//go west
					return 4;
				} else {
					//The end is right below you and down
					return 2;
				}
			}
		} else if(interNum == 2) {
			//this considers the chunk to the east of the intersection
			
			if(distDiffX(currCar) >=-4) {
				if(distDiffY(currCar) > 0) {
					//The car's current position has a larger Y value than the end, so lower the Y value
					return 1;
				} else if (distDiffY(currCar) < 0) {
					return 3;
				} else {
					return 2;
				}
			} else {
				return 2;
			}
			
		}else if(interNum == 3) {
			if(distDiffY(currCar) >= -3) {
				if(distDiffX(currCar) > 0) {
					return 4;
				} else if(distDiffX(currCar) < 0) {
					return 2;
				} else if(distDiffY(currCar) < 0){
					return 1;
				} else {
					return 3;
				}
			} else {
				return 3;
				
			}
			
		} else if(interNum == 4) {
			if(distDiffY(currCar) < -3) {
				if(distDiffX(currCar) >= 0) {
					return 4;
				} else {
					return 2;
				}
			} else {
				if(distDiffY(currCar) > 0) {
					//above us
					if(distDiffX(currCar) >= 0) {
						//to the left or straight abouve us
						return 4;
					} else {
						return 2;
					}
				} else if (distDiffY(currCar) == 0) {
					if(distDiffX(currCar) > 0) {
						//to the left of us
						return 4;
					} else {
						return 2;
					}

				} else {
					//not on our row, between 1-3 below us
					if(distDiffX(currCar) <= 0 && distDiffX(currCar) >= -3) {
						//in the 'dead end' area
						return 3;
					} else if(distDiffX(currCar) > 0) {
						return 4;
					} else {
						return 2;
					}
				}
			}
			//NOTE: because of the symettry of the map some of the intersection 
			//pathfinding logic is based on the logic of the mirrored other side of the map
		}else if(interNum == 5) {
			if(distDiffX(currCar) >= 0) {
				if(distDiffY(currCar) > 0) {
					return 1;
				}
				return 4;
			}
			return 2;
		}else if(interNum == 6) {
			if(distDiffX(currCar) <= 0) {
				if(distDiffY(currCar) > 0) {
					return 1;
				}
				return 2;
			}
			return 4;
		}else if(interNum == 7) {
			if(distDiffY(currCar) < -3) {
				if(distDiffX(currCar) >= 0) {
					return 4;
				} 
				return 2;
			} else {
				if(distDiffY(currCar) > 0) {
					//above us
					if(distDiffX(currCar) >= 0) {
						//to the left or straight abouve us
						return 4;
					} else {
						return 2;
					}
				} else if (distDiffY(currCar) == 0) {
					if(distDiffX(currCar) > 0) {
						//to the left of us
						return 4;
					} else {
						return 2;
					}

				} else {
					//not on our row, between 1-3 below us
					if(distDiffX(currCar) >= 0 && distDiffX(currCar) <= 3) {
						//in the 'dead end' area
						return 3;
					} else if(distDiffX(currCar) > 0) {
						return 4;
					} else {
						return 2;
					}
				}
			}
		}
			else if(interNum == 8) {
				if(distDiffY(currCar) >= -3) {
					if(distDiffX(currCar) > 0) {
						return 4;
					} else if(distDiffX(currCar) < 0) {
						return 2;
					} else if(distDiffY(currCar) < 0){
						return 1;
					} else {
						return 3;
					}
				} else {
					return 3;
				}
			}
			else if(interNum == 9) {
				//this considers the chunk to the east of the intersection
				
				if(distDiffX(currCar) <= 4) {
					if(distDiffY(currCar) > 0) {
						//The car's current position has a larger Y value than the end, so lower the Y value
						return 1;
					} else if (distDiffY(currCar) < 0) {
						return 3;
					} else {
						return 4;
					}
				} else {
					return 2;
				}
			}else if(interNum == 10) {
				if(distDiffY(currCar) >= 0) {
					if(distDiffX(currCar) > 0) {
						return 4;
					}
					return 1;
				}
				return 3;
			}else if(interNum == 11) {
				if(distDiffY(currCar) >= 0) {
					if(distDiffX(currCar) < 5 && distDiffY(currCar) < 3) {
						return 2;
					}
					return 1;
				}
				return 3;
			}else if(interNum == 12) {
				//checks if the end is before the second main 'Connect' path
				if(distDiffX(currCar) >= -14) {
					if(distDiffY(currCar)>0) {
						return 1;
					}
					if(distDiffX(currCar) > 0) {
						return 4;
					}
					if(distDiffX(currCar) == 0) {
						return 3;
					}
					if(distDiffX(currCar) >= -6) {
						return 2;
					}
					return 3;
				
				}
				return 2;
			}else if(interNum == 13) {
				//checks if the end is before the second main 'Connect' path
				if(distDiffX(currCar) <= 14) {
					if(distDiffY(currCar)>0) {
						return 1;
					}
					if(distDiffX(currCar) < 0) {
						return 2;
					}
					if(distDiffX(currCar) == 0) {
						return 3;
					}
					if(distDiffX(currCar) <= 6) {
						return 4;
					}
					return 3;
				
				}
				return 4;
			}else if(interNum == 14) {
				if(distDiffX(currCar) >= 0) {
					if(distDiffY(currCar) > 0) {
						return 1;
					}
					return 3;
				}
				return 2;
			}else if(interNum == 15) {
				if(distDiffX(currCar) > 0) {
					return 4;
				} 
				if(distDiffY(currCar) < 0) {
					return 3;
				}
				return 2;
			}else if(interNum == 16) {
				if(distDiffX(currCar) >= 0) {
					if(distDiffY(currCar) > 0) {
						return 1;
					}
					return 4;
				}
				return 2;
			}else if(interNum == 17) {
				if(distDiffX(currCar) <= 0) {
					if(distDiffY(currCar) > 0) {
						return 1;
					}
					return 2;
				}
				return 4;
			}else if(interNum == 18) {
				if(distDiffX(currCar) < 0) {
					return 2;
				} 
				if(distDiffY(currCar) < 0) {
					return 3;
				}
				return 4;
			}else if(interNum == 19) {
				if(distDiffX(currCar) <= 0) {
					if(distDiffY(currCar) > 0) {
						return 1;
					}
					return 3;
				}
				return 4;
			}else if(interNum == 20) {
				if(distDiffX(currCar) >= 0) {
					if(distDiffY(currCar) > 0) {
						return 1;
					}
					return 4;
				}
				if(distDiffY(currCar) < 0) {
					return 4;
				}
				return 2;
			}else if(interNum == 21) {
				if(distDiffX(currCar) <= 0) {
					if(distDiffY(currCar) > 0) {
						return 1;
					}
					return 2;
				}
				if(distDiffY(currCar) < 0) {
					return 2;
				}
				return 4;
			}else if(interNum == 22) {
				if(distDiffY(currCar) > 0) {
					return 1;
				}
				if(distDiffX(currCar) > 0) {
					return 4;
				}
				return 2;
			}else if(interNum == 23) {
				if(distDiffY(currCar) > 0) {
					return 1;
				}
				if(distDiffX(currCar) > 0) {
					return 4;
				}
				return 2;
			}
		//TODO: Improve some of the intersection movement logic
		
		System.out.println("ERROR, INVALID INTERSECTION");
		return 1;
	}
	static int distDiffX(Car distCar) {
		//if it is less than 0 then the car is further east than the end X coordinate
		//greater than 0 then it is further west
		
		return distCar.getCurrX() - distCar.getEndX();
	}
	static int distDiffY(Car distCar) {
		//if it is less than 0 then the car is further north than the end X coordinate
		//greater than 0 then it is further south
		
		return distCar.getCurrY() - distCar.getEndY();
	}
	static int checkQueue(int interNum) {
		int total = 1;
		for(Car waitCars:activeCars) {
			if(waitCars.getIntersectAt() == interNum) {
				total++;
			}
		}
		return total;
	}
	
	public boolean checkCityMap(int startX, int startY, int endX, int endY) {
		if (cityMap[startX][startY] == 0 || cityMap[endX][endY] == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
