package com.project.model;

import com.project.geom.Ellipse;

public class EllipseRoad extends Road{
	public EllipseRoad(double x, double y, double w, double h){
		super();
		shape = new Ellipse(x, y, w, h);
		leftLane = new CircularLane(this);
		rightLane = new CircularLane(this);
	}
}
