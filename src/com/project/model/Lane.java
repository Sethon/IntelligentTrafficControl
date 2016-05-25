package com.project.model;

public class Lane {
	public final Road road;
	
	private Car[] cars;
	private Car[] acceptedCars;
	private int[] movements;
	
	public Lane(Road road){
		cars = new Car[road.getLength()];
		movements = new int[cars.length];
		acceptedCars = new Car[cars.length];
		this.road = road;
	}
	
	public boolean hasCarAt(int segment){
		return cars[segment] != null;
	}	
	
	public void calcMovements(){		
		//calculate each car's movement
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				int gap = getGrap(i, cars[i].trajectory.getNextLane());
				movements[i] = cars[i].getVelocity(gap);
			}
		}
		
		acceptedCars = new Car[cars.length];
	}
	
	public int getGap(int from){
		for (int i=from + 1; i<cars.length; i++){
			if(cars[i] != null){
				return (i - from) - 1;
			}
		}
		return cars.length - from - 1;
	}
	
	public int getGrap(int from, Lane nextLane){
		int gap = getGap(from);
		if(gap == cars.length - from - 1 && nextLane != null){
			gap += nextLane.getGap(-1);
		}
		return gap;
	}
	
	public void moveCars() {
		//actually move the cars
		Car[] newCars = new Car[cars.length];
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				int newPosition = i + movements[i];
				if(newPosition >= cars.length){
					//this car leaves the road
					cars[i].trajectory.updateLane();
					Lane next = cars[i].trajectory.currentLane;
					next.acceptCar(cars[i], newPosition - cars.length);
				}else{
					newCars[newPosition] = cars[i];
				}
			}
		}
		for(int i=0; i<cars.length; i++){
			if(acceptedCars[i] != null){
				newCars[i] = acceptedCars[i];
			}
		}
		cars = newCars;
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
}
