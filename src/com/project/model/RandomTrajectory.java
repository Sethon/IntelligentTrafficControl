package com.project.model;

import java.util.Random;

public class RandomTrajectory extends Trajectory{
	private Random random = new Random();
	
	protected Lane getTargetLane(Intersection intersection){
		//Pick a random lane from which we wat to leave the intersection
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
		//Pick one of the two lanes of a road at random.
		return random.nextBoolean() ? road.leftLane : road.rightLane;
	}
}