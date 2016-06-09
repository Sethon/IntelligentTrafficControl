package com.project.model;

public class SinkLane extends Lane {

	public SinkLane(Road road) {
		super(road);
		// TODO Auto-generated constructor stub
	}
	
	public void moveCars(){
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				cars[i].trajectory.currentLane = null;
				cars[i] = null;
			}
		}
	}
}
