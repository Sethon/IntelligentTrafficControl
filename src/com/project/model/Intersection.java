package com.project.model;

import java.awt.Point;

public class Intersection {
	// An intersection has 4 sides, each one has an integer associated with it.
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private Point position;
	
	private Road[] inRoads = new Road[4];
	private Road[] outRoads = new Road[4];
	
	public Intersection(Point position){
		this.position = position;
	}
	
	public void setIncomingRoad(Road road, int direction){
		inRoads[direction] = road;
	}
	
	public void setOutgoingRoad(Road road, int direction){
		outRoads[direction] = road;
	}
	
	public Road[] getIncomingRoads(){
		return inRoads.clone();
	}
	
	public Road[] getOutgoingRoads(){
		return outRoads.clone();
	}
	
	public void setPosition(Point newPosition){
		this.position = newPosition;
	}
	
	public Point getPosition(){
		return position
	}
	
	
}
