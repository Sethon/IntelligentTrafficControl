package com.project.model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import com.project.geom.Utils;

public class IntersectionInsideRoad extends CurvedRoad {

	public IntersectionInsideRoad(Point2D.Double from, Point2D.Double to, int fromDirection, int toDirection,
			Double curveControl) {
		super(new Intersection(from), new Intersection(to), fromDirection, toDirection, curveControl);
	}

	public ArrayList<Line2D.Double> getCarLines(){
		ArrayList<Line2D.Double> lines = new ArrayList<Line2D.Double>(getLength());
		
		for(int i=0; i<this.getLength(); i++){
			if(leftLane.hasCarAt(i)){
				lines.add(shape.getSegment(Globals.CELL_LENGTH, i));
			}
		}
		return lines;
	}
}
