package com.project.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line extends Line2D.Double implements RoadShape {
	public Line(Point2D from, Point2D to){
		super(from, to);
	}
	
	public double getLength(){
		return this.getP1().distance(this.getP2());
	}
	
	public Line2D.Double getSegment(double minSegmentLength, int i){
		int nSegments = getNumSegments(minSegmentLength);
		
		double xIncrement = (this.getX2() - this.getX1())/nSegments;
		double yIncrement = (this.getY2() - this.getY1())/nSegments;
		
		Point2D from = new Point2D.Double(this.getX1() + i * xIncrement, this.getY1() + i * yIncrement);
		Point2D to;
		if(i == nSegments-1){
			to = this.getP2();
		}else{
			to = new Point2D.Double(this.getX1() + (i+1)*xIncrement, this.getY1() + (i+1)*yIncrement);
		}
		
		return new Line2D.Double(from, to);
	}
	
	public Point2D.Double getPointAlong(double progress){
		double xIncrement = (this.getX2() - this.getX1());
		double yIncrement = (this.getY2() - this.getY1());
		return new Point2D.Double(getX1() + progress*xIncrement, getY1() + progress*yIncrement);
	}
}
