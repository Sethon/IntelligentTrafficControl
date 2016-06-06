package com.project.model;

public class CircularLane extends Lane {
	
	public CircularLane(Road road){
		super(road);
	}
	
	public int getGap(int from){
		for (int i=1; i<=cars.length; i++){
			if(cars[(from + i) % cars.length] != null){
				return i - 1;
			}
		}
		return cars.length;
	}
	
	public void calcMovements(){		
		//calculate each car's movement
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				int gap = getGap(i);
				movements[i] = cars[i].getVelocity(gap);
			}
		}
	}
	
	public void moveCars() {
		//actually move the cars
		Car[] newCars = new Car[cars.length];
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				int newPosition = (i + movements[i]) % cars.length;
				newCars[newPosition] = cars[i];
			}
		}
		cars = newCars;
	}
}
