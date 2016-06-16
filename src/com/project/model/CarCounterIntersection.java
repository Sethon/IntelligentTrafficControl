package com.project.model;

import java.awt.geom.Point2D.Double;

public class CarCounterIntersection extends Intersection {

	public CarCounterIntersection(Double position) {
		super(position);
	}
	
	protected void changeLights(){
		int[] greenDirections = new int[]{0, 0, 1, 1};
		boolean[] greenLanes = new boolean[]{true, false, true, false};
		int maxWaiting = 0;
		int maxIndex = 0;
		for(int i=0; i<4; i++){
			Lane[] lanes = getLanesForLights(greenLanes[i], greenDirections[i]);
			int waiting = 0;
			if(lanes[0] != null) waiting += lanes[0].getWaitingCars();
			if(lanes[1] != null) waiting += lanes[1].getWaitingCars();
			if(waiting > maxWaiting){
				maxWaiting = waiting;
				maxIndex = i;
			}
		}
		this.greenDirection = greenDirections[maxIndex];
		this.greenLane = greenLanes[maxIndex];
	}

}
