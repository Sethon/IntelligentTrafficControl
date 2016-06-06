package com.project.geom;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;

public class Ellipse extends Ellipse2D.Double implements RoadShape {
	
	public Ellipse(double x, double y, double w, double h){
		super(x, y, w, h);
	}
	
	public double getLength(){
		double a = Math.max(getWidth(), getHeight());
		double b = Math.min(getWidth(), getHeight());
		// I used approximation 2 from this page, as it seemed to strike a nice balance
		// between complexity and accuracy: https://www.mathsisfun.com/geometry/ellipse-perimeter.html
		return Math.PI*(3*(a + b) - Math.sqrt((3*a + b)*(a + 3*b)));
	}
	
	public Point2D.Double getCenter(){
		return new Point2D.Double(getCenterX(), getCenterY());
	}
	
	public Point2D.Double getPointAlong(double progress){
		double angle = Math.PI*2*progress;
		double xInc = Math.cos(angle)*getWidth()/2;
		double yInc = Math.sin(angle)*getHeight()/2;
		return Utils.translate(getCenter(), xInc, yInc);
	}

	public Line2D.Double getSegment(double minSegmentLength, int segment) {
		int nSegments = getNumSegments(minSegmentLength);
		int nPoints = nSegments;
		double segmentProgress = 1.0/nSegments;
		
		Point2D from = getPointAlong(segmentProgress * segment);
		Point2D to = getPointAlong(segmentProgress * (segment + 1));
		return new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY());
	}

}
