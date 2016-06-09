package com.project.model;

public class SinkLane extends Lane {

	public SinkLane(Road road) {
		super(road);
		// TODO Auto-generated constructor stub
	}
	
	public void acceptCar(Car car, int position){
		// do nothing, just throw away the car.
	}
}
