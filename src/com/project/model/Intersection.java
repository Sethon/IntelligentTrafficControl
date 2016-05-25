package com.project.model;

import java.awt.geom.Point2D;
import java.util.Hashtable;

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
	
	//This dictionary stores the internal roads, mapping from a string defined by which lanes they connect, to the road.
	private Hashtable<String, Road> internalRoads = new Hashtable<String, Road>(64);
	
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
	
	public Point2D.Double getConnectionPoint(int direction, boolean incoming, boolean leftLane){
		// This method returns the point where the middle of a road touches the intersection.
		// direction is one of the constants defined at the top of this class, incoming is
		// whether it is the incoming or outgoing road.
		// leftLane is true if this is the connection point for the left lane
		Line cl = getConnectionLine(direction);
		double p;
		if(incoming){
			if(leftLane){
				p = 62.5;
			}else{
				p = 87.5;
			}
		}else{
			if(leftLane){
				p = 37.5;
			}else{
				p = 12.5;
			}
		}
		return cl.getPointAlong(p);
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
	
	public Road getInsideRoad(int inDirection, boolean inLane, int outDirection, boolean outLane){
		//Get the internal "road" that connects an incoming and outgoing lane.
		//inDirection and outDirection are the direction of the road to come in and go out respectively.
		//inLane and outLane are true if the incoming/outgoing lane is the left lane, false if it's the right lane.
		String key = String.format("%s%s%s%s", inLane, inDirection, outLane, outDirection);
		Road road = internalRoads.get(key);
		if(road == null){
			Point2D.Double from = getConnectionPoint(inDirection, true, inLane);
			Point2D.Double to = getConnectionPoint(outDirection, false, outLane);
			Point2D.Double center = Utils.translate(position, Globals.LANE_WIDTH*2, Globals.LANE_WIDTH*2);
			int fromDirection = (inDirection + 2) % 4; //the opposite direction
			int toDirection = (outDirection + 2) % 4;
			road = new IntersectionInsideRoad(from, to, fromDirection, toDirection, center);
			internalRoads.put(key, road);
		}
		return road;
	}
	
	public Road getInsideRoad(Lane from, Lane to){
		int inDirection = -1;
		boolean inLane = true;
		int outDirection = -1;
		boolean outLane = true;
		for(int i=0; i<4; i++){
			if(inRoads[i] == from.road){
				if(from == inRoads[i].leftLane){
					inDirection = i;
					inLane = true;
				}else if(from == inRoads[i].rightLane){
					inDirection = i;
					inLane = false;
				}
			}
			if(outRoads[i] == to.road){
				if(to == outRoads[i].leftLane){
					outDirection = i;
					outLane = true;
				}else if(to == outRoads[i].rightLane){
					outDirection = i;
					outLane = false;
				}
			}
			if(inDirection != -1 && outDirection != -1){
				return getInsideRoad(inDirection, inLane, outDirection, outLane);
			}
		}
		throw new NullPointerException("Requested a road that doesn't exist");
	}
}
