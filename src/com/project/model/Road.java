package com.project.model;


import java.awt.geom.Line2D;
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
	
	public Road(Intersection from, Intersection to, int fromDirection, int toDirection) {
		this.from = from;
		this.to = to;
		this.updateConnectingIntersections(fromDirection, toDirection);
		this.shape = new Line(from.getConnectionPoint(this), to.getConnectionPoint(this));
		initLanes();
	}
	
	protected void initLanes(){
		this.leftLane = new Lane(this);
		this.rightLane = new Lane(this);
	}
	
	public RoadShape getShape(){
		return shape;
	}
	
	private void updateConnectingIntersections(int fromDirection, int toDirection){
		from.setOutgoingRoad(this, fromDirection);
		to.setIncomingRoad(this, toDirection);
	}
	
	public int getLength() {
		return this.shape.getNumSegments(Globals.CELL_LENGTH);
	}
	
	public ArrayList<Line2D.Double> getCarLines() {
		ArrayList<Line2D.Double> lines = new ArrayList<Line2D.Double>(getLength()*2);
		for(int i=0; i<this.getLength(); i++){
			Line2D.Double line = shape.getSegment(Globals.CELL_LENGTH, i);
			if(leftLane.hasCarAt(i)){
				lines.add(Utils.getOffsetLine(line, -Globals.LANE_WIDTH*0.5));
			}
			if(rightLane.hasCarAt(i)){
				lines.add(Utils.getOffsetLine(line, Globals.LANE_WIDTH*0.5));
			}
		}
		return lines;
	}
		
}
