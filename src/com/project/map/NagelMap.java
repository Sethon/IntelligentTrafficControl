package com.project.map;

import java.util.ArrayList;

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
}
