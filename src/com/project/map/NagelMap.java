package com.project.map;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.project.model.CurvedRoad;
import com.project.model.Globals;
import com.project.model.Intersection;
import com.project.model.Road;

public class NagelMap {
	public final ArrayList<Road> roads = new ArrayList<Road>();
	public final ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	
	public void addRoad(Road road){
		roads.add(road);
	}
	
	public void addIntersection(Intersection inter){
		intersections.add(inter);
	}
		
	public void generate(){
		//We want to generate a random map here later, but for now, I'm just setting up a test map.
		double offset = 20;
		double size = 200;
		double c = size / 2;
		
		Intersection center = new Intersection(new Point2D.Double(offset + c, offset + c));
		Intersection top = new Intersection(new Point2D.Double(offset + c, offset));
		Intersection left = new Intersection(new Point2D.Double(offset, offset + c));
		Intersection bottom = new Intersection(new Point2D.Double(offset + c, offset + size));
		Intersection right = new Intersection(new Point2D.Double(offset + size, offset + c));
		
		Road road1 = new Road(top, center, Intersection.SOUTH, Intersection.NORTH);
		Road road2 = new Road(center, top, Intersection.NORTH, Intersection.SOUTH);
		Road road3 = new Road(center, bottom, Intersection.SOUTH, Intersection.NORTH);
		Road road4 = new Road(bottom, center, Intersection.NORTH, Intersection.SOUTH);
		Road road5 = new Road(left, center, Intersection.EAST, Intersection.WEST);
		Road road6 = new Road(center, left, Intersection.WEST, Intersection.EAST);
		Road road7 = new Road(center, right, Intersection.EAST, Intersection.WEST);
		Road road8 = new Road(right, center, Intersection.WEST, Intersection.EAST);
		
		//This offset is the offset for the control point of the bezier curves of the inner roads of the ring.
		//If we don't apply this offset, the two roads that make up each part of the ring will overlap.
		//We want to push the control point inwards by 2 lane widths. The amount we need to push inwards
		// equal horizontally and vertically. We want to move  2 lane widths, at an angle of 45° (pi/4 radians) to both axis.
		double ctrlOffset = Math.cos(Math.PI/4)*Globals.LANE_WIDTH*2;
		
		Road ring1 = new CurvedRoad(top, right, Intersection.EAST, Intersection.NORTH, new Point2D.Double(offset + size - ctrlOffset, offset + ctrlOffset));
		Road ring2 = new CurvedRoad(right, top, Intersection.NORTH, Intersection.EAST, new Point2D.Double(offset + size, offset));
		Road ring3 = new CurvedRoad(right, bottom, Intersection.SOUTH, Intersection.EAST, new Point2D.Double(offset + size - ctrlOffset, offset + size - ctrlOffset));
		Road ring4 = new CurvedRoad(bottom, right, Intersection.EAST, Intersection.SOUTH, new Point2D.Double(offset + size , offset + size));
		Road ring5 = new CurvedRoad(bottom, left, Intersection.WEST, Intersection.SOUTH, new Point2D.Double(offset + ctrlOffset, offset + size - ctrlOffset));
		Road ring6 = new CurvedRoad(left, bottom, Intersection.SOUTH, Intersection.WEST, new Point2D.Double(offset, offset + size));
		Road ring7 = new CurvedRoad(left, top, Intersection.NORTH, Intersection.WEST, new Point2D.Double(offset + ctrlOffset, offset + ctrlOffset));
		Road ring8 = new CurvedRoad(top, left, Intersection.WEST, Intersection.NORTH, new Point2D.Double(offset, offset));
		
		addRoad(road1);
		addRoad(road2);
		addRoad(road3);
		addRoad(road4);
		addRoad(road5);
		addRoad(road6);
		addRoad(road7);
		addRoad(road8);
		
		addRoad(ring1);
		addRoad(ring2);
		addRoad(ring3);
		addRoad(ring4);
		addRoad(ring5);
		addRoad(ring6);
		addRoad(ring7);
		addRoad(ring8);
		
		addIntersection(center);
		addIntersection(top);
		addIntersection(right);
		addIntersection(bottom);
		addIntersection(left);
	}
}