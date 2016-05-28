package com.project.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Utils {
	//Return a new point that is translated 'x' amount to the right, and 'y' amount downwards.
	public static Point2D.Double translate(Point2D pt, double x, double y){
		return new Point2D.Double(pt.getX() + x, pt.getY() + y);
	}
	
	// Returns the angle between two points, in radians
	public static double getAngle(Point2D from, Point2D to) {
	    return Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
	}
	
	// Given a line and an offset, returns a parallel line, 'offset' units away from the original line.
	// The line is translated along a line perpendicular to the original line.
	// In other words, if you were to connect the corresponding edges of the original, and the returned line,
	// you'd get a rectangle.
	// The actual direction of the movement depends on the orientation of the line.
	// If the line goes straight up, a positive offset moves the returned line to the right,
	// a negative offset moves it to the left.
	public static Line2D.Double getOffsetLine(Line2D.Double line, double offset){
		double angle = getAngle(line.getP1(), line.getP2()) + Math.PI * 0.5;
		double xinc = Math.cos(angle) * offset;
		double yinc = Math.sin(angle) * offset;
		Line2D.Double l = new Line2D.Double(
				translate(line.getP1(), xinc, yinc),
				translate(line.getP2(), xinc, yinc)
			);
		
		return l;
	}
	
	public static Line2D.Double scale(Line2D.Double line, double factor){
		//Scales the line by the given amount. 1.0 will keep it the same size, 0.9 will shrink it, 1.1 will grow it.
		Point2D.Double center = new Point2D.Double((line.x1 + line.x2)/2, (line.y1 + line.y2)/2);
		double xInc = ((line.x2 - line.x1)/2)*factor;
		double yInc = ((line.y2 - line.y1)/2)*factor;
		return new Line2D.Double(translate(center, -xInc, -yInc), translate(center, xInc, yInc));
	}
}
