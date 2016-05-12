package com.project.model;

public abstract class Trajectory {
	public Lane currentLane;
	
	public abstract Lane getNextLane();
	
	public void updateLane(){
		currentLane = getNextLane();
	}
}
