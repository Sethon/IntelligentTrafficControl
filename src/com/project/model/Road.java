package com.project.model;


import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

import com.project.geom.Line;
import com.project.geom.RoadShape;
import com.project.geom.Utils;

public class Road {
	public final Intersection from;
	public final Intersection to;
	public Lane leftLane;
	public Lane rightLane;
	protected RoadShape shape;
	protected int speedLimit;
	
	protected Road(){
		from = null;
		to = null;
		shape = null;
		speedLimit = Globals.MAX_SPEED;
	}
	
	public Road(Intersection from, Intersection to, int fromDirection, int toDirection) {
		this.from = from;
		this.to = to;
		this.updateConnectingIntersections(fromDirection, toDirection);
		this.shape = new Line(from.getConnectionPoint(this), to.getConnectionPoint(this));
		initLanes();
		speedLimit = Globals.MAX_SPEED;
	}


	protected void initLanes(){
		this.leftLane = new Lane(this);
		this.rightLane = new Lane(this);
	}
	
	public RoadShape getShape(){
		return shape;
	}
	
	private void updateConnectingIntersections(int fromDirection, int toDirection){
		if(from != null && from.isRealIntersection()) from.setOutgoingRoad(this, fromDirection);
		if(to != null && to.isRealIntersection()) to.setIncomingRoad(this, toDirection);
	}
	
	public int getLength() {
		return this.shape.getNumSegments(Globals.CELL_LENGTH);
	}
	
	public ArrayList<CarLine> getCarLines() {
		ArrayList<CarLine> lines = new ArrayList<CarLine>(getLength()*2);
		for(int i=0; i<this.getLength(); i++){
			Line2D.Double line = shape.getSegment(Globals.CELL_LENGTH, i);
			if(leftLane.hasCarAt(i)){
				lines.add(new CarLine(Utils.getOffsetLine(line, -Globals.LANE_WIDTH*0.5), leftLane.getCarAt(i)));
			}
			if(rightLane.hasCarAt(i)){
				lines.add(new CarLine(Utils.getOffsetLine(line, Globals.LANE_WIDTH*0.5), rightLane.getCarAt(i)));
			}
		}
		return lines;
	}
	
	public void calcUpdate(){
		leftLane.calcMovements();
		rightLane.calcMovements();
	}
	
	public void update(){
		leftLane.moveCars();
		rightLane.moveCars();
	}
	
	public void handleTransitions(){
		leftLane.saveAcceptedCars();
		rightLane.saveAcceptedCars();
	}
	
	public int getSpeedLimit(){
		return speedLimit;
	}
	
	public void setSpeedLimit(int limit){
		speedLimit = limit;
	}
}
