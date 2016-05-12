package com.project.model;

import java.util.Random;

public abstract class Trajectory {
	public Lane currentLane;
	
	private Lane targetLane = null;
	private boolean onIntersection = false;
	
	// This trajectory just picks a road at random at each
	public Lane getNextLane() {
		if(onIntersection){
			// our next lane is the road we've previously selected
			onIntersection = false;
			return targetLane;
		}else{
			// we're going towards an intersection, pick a direction to leave it.
			onIntersection = true;
			targetLane = getTargetLane(currentLane.road.to);
			//TODO get the internal lane that connects the current road to the target
			return null;
		}
	}
	
	//This method, given an intersection, should return on which lane it wants to leave the intersection.
	protected abstract Lane getTargetLane(Intersection inter);
	
	public void updateLane(){
		currentLane = getNextLane();
	}
}
