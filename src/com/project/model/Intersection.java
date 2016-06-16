package com.project.model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Collection;

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
	
	private boolean hasTrafficLights = true;
	// There always two opposite roads that have green. Which ones, is determined by this number.
	// "0" for one pair, "1" for the other.
	private int greenDirection = 0;
	// "true" for the left lane, "false" for the right lane.
	private boolean greenLane = true;
	// how many "ticks" should it take between switching traffic light directions?
	private int switchInterval = 10;
	private int tickCounter = 0;
		
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
	
	public Line getConnectionLine(int direction){
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
	
	public Road getInsideRoad(Lane from, Lane to){
		String key = String.format("%s-%s", from, to);
		Road road = internalRoads.get(key);
		if(road == null){
			road = makeInsideRoad(from, to);
			internalRoads.put(key, road);
		}
		return road;
	}
	
	protected Road makeInsideRoad(Lane from, Lane to){
		Point2D.Double center = Utils.translate(position, Globals.LANE_WIDTH*2, Globals.LANE_WIDTH*2);
		return new IntersectionInsideRoad(getConnectionPoint(from), getConnectionPoint(to), 0, 0, center);
	}
	
	public ArrayList<CarLine> getCarLines(){
		ArrayList<CarLine> lines = new ArrayList<CarLine>();
		for(Road road: internalRoads.values()){
			lines.addAll(road.getCarLines());
		}
		return lines;
	}
	
	public void calcUpdate(){
		for(Road road: internalRoads.values()){
			road.calcUpdate();
		}
	}
	
	private void updateGreenLight(){
		if(!hasTrafficLights){
			return;
		}
		tickCounter += 1;
		if(tickCounter==switchInterval){
			tickCounter = 0;
			if(greenLane){
				greenLane = false;
			}else{
				greenLane = true;
				greenDirection = greenDirection == 0 ? 1 : 0;
			}
		}
		updateBlockedLanes();
	}
	
	private void updateBlockedLanes(){
		for(int i=0;i<4;i++){
			if(inRoads[i] == null) continue;
			updateBlockedInternalLanes(inRoads[i].leftLane, !hasGreenLight(inRoads[i].leftLane));
			updateBlockedInternalLanes(inRoads[i].rightLane, !hasGreenLight(inRoads[i].rightLane));
		}
	}
	
	private void updateBlockedInternalLanes(Lane from, boolean blocked){
		for(int i=0;i<4;i++){
			if(outRoads[i] == null) continue;
			getInsideRoad(from, outRoads[i].leftLane).leftLane.setBlocked(blocked);
			getInsideRoad(from, outRoads[i].rightLane).leftLane.setBlocked(blocked);
		}
	}
	
	public void update(){
		updateGreenLight();
		for(Road road: internalRoads.values()){
			road.update();
		}
	}
	
	public void handleTransitions(){
		for(Road road: internalRoads.values()){
			road.handleTransitions();
		}
	}
	
	public Collection<Road> getInsideRoads(){
		return internalRoads.values();
	}
	
	public boolean hasGreenLight(Lane lane){
		if(!hasTrafficLights){
			return true;
		}
		Road r = null;
		if(inRoads[greenDirection] == lane.road){
			r = inRoads[greenDirection];
		} else if(inRoads[greenDirection + 2] == lane.road){
			r = inRoads[greenDirection + 2];
		}else{
			return false;
		}
		if(lane == r.leftLane){
			return greenLane;
		}
		if(lane == r.rightLane){
			return !greenLane;
		}
			
		return false;
	}
	
	public void setHasTrafficLights(boolean hasLights){
		hasTrafficLights = hasLights;
	}
	
	public boolean getHasTrafficLights(){
		return hasTrafficLights;
	}
	
	public boolean isRealIntersection(){
		return true;
	}
	
	public Road getConnectedRoad(){
		return null;
	}
	
	public int getConnectPosition(){
		return 0;
	}
}
