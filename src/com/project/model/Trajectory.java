package com.project.model;

public abstract class Trajectory {
	public Lane currentLane;
	public Lane nextLane;
	
	public abstract Lane getNextLane();
	
	public void updateLane(){
		currentLane = nextLane;
		nextLane = getNextLane();
	}
}
