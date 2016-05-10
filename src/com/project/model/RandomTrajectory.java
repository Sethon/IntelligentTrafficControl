package com.project.model;

import java.util.Random;

public class RandomTrajectory extends Trajectory{
	private Lane targetLane = null;
	private boolean onIntersection = false;
	private Random random = new Random();
	
	public Lane getNextLane() {
		if(onIntersection){
			onIntersection = false;
			return targetLane;
		}else{
			onIntersection = true;
			targetLane = getTargetLane(nextLane.road.to);
			// get the internal lane that connects the current road to the target
			return null;
		}
	}
	
	private Lane getTargetLane(Intersection intersection){
		Road cur = currentLane.road;
		Road option1;
		Road option2;
		if(currentLane == currentLane.road.leftLane){
			option1 = intersection.getRelativeOutgoingFRoad(cur, 0); // making a 180° turn
			option2 = intersection.getRelativeOutgoingFRoad(cur, 3); // turning left
		}else{
			option1 = intersection.getRelativeOutgoingFRoad(cur, 1); // turning right
			option2 = intersection.getRelativeOutgoingFRoad(cur, 2); // going straight
		}
		return getRandomLane(random.nextBoolean() ? option1 : option2);
	}
	
	private Lane getRandomLane(Road road){
		return random.nextBoolean() ? road.leftLane : road.rightLane;
	}
}
