package com.project.model;

import java.awt.geom.Point2D;

import com.project.geom.Line;
import com.project.geom.Utils;

public class Intersection {
	// An intersection has 4 sides, each one has an integer associated with it.
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private Point2D.Double position;
	
	private Road[] inRoads = new Road[4];
	private Road[] outRoads = new Road[4];
	
	// These are imaginary roads used to track cars while they are on the crossroads.
	// This array starts out as all null, but it is filled in as needed by the getInsideRoad method.
	// There are 32 possible internal roads: for each incoming road, there are 4 possible outgoing roads they may choose,
	// each of which has 2 lanes. Using separate roads allows us to implement lane switching very easily.
	// The first 4 options are for when you're coming in from the north, the second 4 are for  east, and so on.
	private Road[] insideRoads = new Road[32];
	
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
		Line cl = getConnectionLine(direction);
		double p;
		if(incoming){
			p = 0.75;
		}else{
			p = 0.25;
		}
		return cl.getPointAlong(p);
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
	
	public Point2D.Double getConnectionPoint(Lane lane){
		//Returns where the given lane 
		Road road = lane.road;
		int laneModifier = (lane == road.leftLane) ? 1 : -1;
		int incomingModifier = 0;
		int direction;
		for(direction=0; direction<4; direction++){
			if(inRoads[direction] == road){
				incomingModifier = 1;
				laneModifier = -laneModifier;
				break;
			}
			if(outRoads[direction] == road){
				incomingModifier = -1;
				break;
			}
		}
		if(direction == 4){ // The road is not connected to this intersection
			return null;
		}
		double p = 0.5 + (incomingModifier*0.25) + (laneModifier*0.125);
		return getConnectionLine(direction).getPointAlong(p);
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
	
	private Line getConnectionLine(int direction){
		double x = position.x;
		double y = position.y;
		double s = Globals.LANE_WIDTH*4;
		direction %= 4; //make sure direction is one of the 4 directions
		
		switch(direction){
		case NORTH:
			return new Line(Utils.translate(position, s, 0), position);
		case EAST:
			return new Line(Utils.translate(position, s, s), Utils.translate(position, s, 0));
		case SOUTH:
			return new Line(Utils.translate(position, 0, s), Utils.translate(position, s, s));
		case WEST:
			return new Line(position, Utils.translate(position, 0, s));
		}
		return null;
	}
	
	public Road getInsideRoad(int inDirection, int outDirection){
		Point2D.Double from = getConnectionPoint(inDirection, true);
		Point2D.Double to = getConnectionPoint(outDirection, false);
		
		return new CurvedRoad(
				new Intersection(from),
				new Intersection(to), outDirection, outDirection, to);
	}
}
