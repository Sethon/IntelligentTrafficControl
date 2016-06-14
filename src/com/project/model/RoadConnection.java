package com.project.model;

import java.awt.geom.Point2D;

public class RoadConnection extends Intersection {
	private Road intoRoad;
	private int connectPosition;
	
	public RoadConnection(Road intoRoad, int connectPosition){
		super((Point2D.Double)intoRoad.getShape().getSegment(Globals.CELL_LENGTH, connectPosition).getP1());
		this.intoRoad = intoRoad;
		this.connectPosition = connectPosition;
	}
	public boolean isRealIntersection(){
		return false;
	}
	
	public int getConnectPosition(){
		return connectPosition;
	}
	
	public Road getConnectedRoad(){
		System.out.println("GetConnectedRoad called on RoadConnection");
		return intoRoad;
	}
	
	public Point2D.Double getConnectionPoint(Road r){
		return getPosition();
	}
}
