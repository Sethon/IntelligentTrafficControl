package com.project.model;

import java.util.Random;

public abstract class Trajectory {
	public Lane currentLane;
	
	private Lane targetLane = null;
	public boolean onIntersection = false;
	
	// This trajectory just picks a road at random at each
	public Lane getNextLane() {
		if(onIntersection){
			// our next lane is the road we've previously selected
			return targetLane;
		}else{
			// we're going towards an intersection, pick a direction to leave it.
			if(targetLane == null){
				targetLane = getTargetLane(currentLane.road.to);
			}
			return currentLane.road.to.getInsideRoad(currentLane, targetLane).leftLane;
		}
	}
	
	//This method, given an intersection, should return on which lane it wants to leave the intersection.
	protected abstract Lane getTargetLane(Intersection inter);
	
	public void updateLane(){
		System.out.println("Onintersection "+onIntersection);
		currentLane = getNextLane();
		onIntersection = !onIntersection;
		if(!onIntersection){
			targetLane = null;
		}
	}
}
