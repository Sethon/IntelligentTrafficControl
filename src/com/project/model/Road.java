package com.project.model;


import com.project.geom.Line;
import com.project.geom.RoadShape;

public class Road {
	public final Intersection from;
	public final Intersection to;
	public final Lane leftLane;
	public final Lane rightLane;
	protected RoadShape shape;
	
	public Road(Intersection from, Intersection to, int fromDirection, int toDirection) {
		this.from = from;
		this.to = to;
		
		int l = this.getLength();
		this.leftLane = new Lane(l);
		this.rightLane = new Lane(l);
		
		this.shape = new Line(from.getPosition(), to.getPosition());
		this.updateConnectingIntersections(fromDirection, toDirection);
	}
	
	public RoadShape getShape(){
		return shape;
	}
	
	private void updateConnectingIntersections(int fromDirection, int toDirection){
		from.setOutgoingRoad(this, fromDirection);
		to.setIncomingRoad(this, toDirection);
	}
	
	private int getLength() {
		return this.shape.getNumSegments(15);
	}
		
}
