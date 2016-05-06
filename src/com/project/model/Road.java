package com.project.model;

import java.awt.Point;

public class Road {
	public final Intersection from;
	public final Intersection to;
	public final Lane leftLane;
	public final Lane rightLane;
	
	public Road(Intersection from, Intersection to, int fromDirection, int toDirection) {
		this.from = from;
		this.to = to;
		
		int l = this.getLength();
		this.leftLane = new Lane(l);
		this.rightLane = new Lane(l);
		
		this.updateConnectingIntersections(fromDirection, toDirection);
	}

	
	private void updateConnectingIntersections(int fromDirection, int toDirection){
		from.setOutgoingRoad(this, fromDirection);
		to.setIncomingRoad(this, toDirection);
	}
	
	private int getLength() {
		Point pFrom = from.getPosition();
		Point pTo = to.getPosition();
		return (int)(Point.distance(pFrom.getX(), pFrom.getY(), pTo.getX(), pTo.getY()) / 1.5);
	}
	
}
