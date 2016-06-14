package com.project.model;

import java.util.Random;

public abstract class Trajectory {
	public Lane currentLane;
	
	private Lane targetLane = null;
	public boolean onIntersection = false;
	private boolean onIntersectionShouldFlip = true;
	private boolean isOvertaking;
	private int mergePosition = 0;
	
	// This trajectory just picks a road at random at each
	public Lane getNextLane() {
		onIntersectionShouldFlip = true;
		mergePosition = currentLane.road.to.getConnectPosition();
		if(onIntersection){
			// our next lane is the road we've previously selected
			return targetLane;
		}else{
			// we're going towards an intersection, pick a direction to leave it.
			if(targetLane == null){
				if(currentLane.road.to.isRealIntersection()){
					targetLane = getTargetLane(currentLane.road.to);
				}else{
					Lane retval = currentLane.road.to.getConnectedRoad().rightLane;
					onIntersectionShouldFlip = false;
					if (retval == null){
						System.out.println("the problem is in here!");
					}
					return retval;
				}
			}
			return currentLane.road.to.getInsideRoad(currentLane, targetLane).leftLane;
		}
	}
	
	public int getMergePosition(){
		return mergePosition;
	}
	
	//This method, given an intersection, should return on which lane it wants to leave the intersection.
	protected abstract Lane getTargetLane(Intersection inter);
	
	public void updateLane(){
		currentLane = getNextLane();
		onIntersection = onIntersectionShouldFlip ? !onIntersection : onIntersection;
		if(!onIntersection){
			targetLane = null;
		}
	}
}
