package com.project.model;

public class Lane {
	public final Road road;
	
	private Car[] cars;
	
	
	public Lane(Road road){
		cars = new Car[road.getLength()];
		this.road = road;
	}
	
	public boolean hasCarAt(int segment){
		return cars[segment] != null;
	}
	
	public void moveCars() {
		int[] movements = new int[cars.length];
		
		//calculate each car's movement
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				//TODO Calculate every car's movement
			}
		}
		
		//actually move the cars
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				int newPosition = i + movements[i];
				if(newPosition >= cars.length){
					//this car leaves the road
					cars[i].trajectory.updateLane();
					Lane next = cars[i].trajectory.currentLane;
					next.acceptCar(cars[i], newPosition - cars.length);
				}
			}
		}
	}
	
	public void acceptCar(Car car, int startPosition){
		//Store cars entering the road
		cars[startPosition] = car;
	}
}
