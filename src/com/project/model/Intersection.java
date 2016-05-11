package com.project.model;

import java.awt.geom.Point2D;

public class Intersection {
	// An intersection has 4 sides, each one has an integer associated with it.
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private Point2D.Double position;
	
	private Road[] inRoads = new Road[4];
	private Road[] outRoads = new Road[4];
	
	private Point2D.Double[] connectionPoints = new Point2D.Double[]{
			// This array stores how roads connect to the intersection.
			// The first 2 points where the north facing incoming and outgoing roads connect respectively
			// The second 2 points do that for the east facing roads, and so on.
			// These co-ordinates specify how the middle of the road touches the intersection.
			// The points will be scaled by the road with, inside the getConnectionPoint method.
			new Point2D.Double(1, 0),
			new Point2D.Double(3, 0),
			
			new Point2D.Double(4, 1),
			new Point2D.Double(4, 3),
			
			new Point2D.Double(3, 4),
			new Point2D.Double(1, 4),
			
			new Point2D.Double(0, 3),
			new Point2D.Double(0, 1)
	};
	
	public Intersection(Point2D.Double position){
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
	
	public void setPosition(Point2D.Double newPosition){
		this.position = newPosition;
	}
	
	public Point2D.Double getPosition(){
		return position;
	}
	
	public Point2D.Double getConnectionPoint(int direction, boolean incoming){
		// This method returns the point where the middle of a road touches the intersection.
		// direction is one of the constants defined at the top of this class, incoming is
		// whether it is the incoming or outgoing road.
		int i = direction * 2 + (incoming ? 0 : 1);
		Point2D res = connectionPoints[i];
		return new Point2D.Double(
				position.getX() + res.getX() * Globals.LANE_WIDTH,
				position.getY() + res.getY() * Globals.LANE_WIDTH);
	}
	
	//Get the point where the center of the given road touches the intersection.
	public Point2D.Double getConnectionPoint(Road road){
		for(int direction = 0; direction < 4; direction++){
			if(inRoads[direction] == road){
				return getConnectionPoint(direction, true);
			}
			if(outRoads[direction] == road){
				return getConnectionPoint(direction, false);
			}
		}
		return null;
	}
	
	// Given an incoming road, returns theoutgoing road corresponding to how many roads you count going clockwise.
	public Road getRelativeOutgoingFRoad(Road currentRoad, int turnNumber){
		for(int i=0; i<4; i++){
			if(inRoads[i] == currentRoad){
				return this.outRoads[(i + turnNumber) % 4];
			}
		}
		return null;
	}
}
