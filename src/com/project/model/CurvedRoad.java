package com.project.model;


import java.awt.geom.Point2D;

import com.project.geom.Curve;

public class CurvedRoad extends Road {
	//A curved road is a road that has a bezier curve as its shape.
	public CurvedRoad(Intersection from, Intersection to, int fromDirection, int toDirection, Point2D.Double curveControl){
		super(from, to, fromDirection, toDirection);
		this.shape = new Curve(from.getConnectionPoint(this), to.getConnectionPoint(this), curveControl);
	}

}
