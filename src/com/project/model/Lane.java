package com.project.model;

public class Lane {
	public final Road road;
	
	protected Car[] cars;
	protected Car[] acceptedCars;
	protected int[] movements;
	private boolean blocked;
	public boolean[] laneChangers;
	
	public Lane(Road road){
		cars = new Car[road.getLength()];
		movements = new int[cars.length];
		acceptedCars = new Car[cars.length];
		this.road = road;
	}
	
	public boolean hasCarAt(int segment){
		return cars[segment] != null;
	}
	
	public Car getCarAt(int segment){
		return cars[segment];
	}
	
	public void calcMovements(){		
		//calculate each car's movement
		laneChangers = new boolean[cars.length];
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				Lane nextLane = cars[i].trajectory.getNextLane();
				int gap = getGrap(i, nextLane, cars[i].trajectory.getMergePosition(), cars[i].getPreviousVelocity());
				movements[i] = cars[i].getVelocity(gap);
				laneChangers[i] = cars[i].getWantsLaneChange();
			}
		}
	}
	
	public boolean handleLaneChange(int i){
		cars[i].laneChangeTick();
		if(laneChangers[i] && cars[i].canChangeLane() && road.leftLane.canMoveIn(i, movements[i])){
			road.leftLane.acceptCar(cars[i], i+movements[i]);
			cars[i].laneChanged();
			cars[i].setOvertaking(true);
			cars[i] = null;
			return true;
		}
		if(cars[i].isOvertaking() && cars[i].canChangeLane() && road.rightLane.canMoveIn(i, movements[i])){
			road.rightLane.acceptCar(cars[i], i+movements[i]);
			cars[i].laneChanged();
			cars[i].setOvertaking(false);
			cars[i] = null;
			return true;
		}
		return false;
	}
	
	public boolean canMoveIn(int from, int speed){
		if(from + speed >= cars.length){
			return false;
		}
		//check for car in trajectory
		for(int j=0; j<=speed; j++){
			if(hasCarAt(from+j)){
				return false;
			}
		}
		
		//check for cars behind
		int carIndex = getCarBehind(from);
		int distance = from - carIndex;
		if(carIndex != -1 && movements[carIndex] >= distance){
			return false;
		}
		
		return true;
	}
	
	public int getCarBehind(int from){
		for(int i=from-1; i>=0; i--){
			if(hasCarAt(i)){
				return i;
			}
		}
		return -1;
	}
	
	public int getGap(int from){
		if(blocked && from == -1){
			return 0;
		}
		for (int i=from + 1; i<cars.length; i++){
			if(cars[i] != null){
				return (i - from) - 1;
			}
		}
		return cars.length - from - 1;
	}
	
	public int getGrap(int from, Lane nextLane, int mergePosition, int speed){
		int gap = getGap(from);
		if(gap == cars.length - from - 1){
			if(nextLane == null){
				gap += 100; //arbitrary number of free spaces.
			}else{
				if(nextLane.canMoveIn(mergePosition, speed)){
					gap += nextLane.getGap(mergePosition-1);
				}
			}
		}
		return gap;
	}
	
	public void setBlocked(boolean b){
		blocked = b;
	}
	
	public void moveCars() {
		//actually move the cars
		Car[] newCars = new Car[cars.length];
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				if(handleLaneChange(i)){
					continue;
				}
				int newPosition = i + movements[i];
				if(newPosition >= cars.length){
					//this car leaves the road
					cars[i].trajectory.updateLane();
					Lane next = cars[i].trajectory.currentLane;
					next.acceptCar(cars[i], newPosition - cars.length + cars[i].trajectory.getMergePosition());
				}else{
					newCars[newPosition] = cars[i];
				}
			}
		}
		cars = newCars;
	}
	
	public void saveAcceptedCars(){
		for(int i=0; i<cars.length; i++){
			if(acceptedCars[i] != null){
				cars[i] = acceptedCars[i];
			}
		}
		
		acceptedCars = new Car[cars.length];
	}
	
	public void acceptCar(Car car, int startPosition){
		//Store cars entering the road
		acceptedCars[startPosition] = car;
		car.trajectory.currentLane = this;
	}
	
	public void addCar(Car car, int position){
		cars[position] = car;
		car.trajectory.currentLane = this;
	}
	
	public int getWaitingCars(){
		for(int i=0;i<cars.length; i++){
			if(cars[cars.length - (i+1)] == null){
				return i;
			}
		}
		return cars.length;
	}
}
