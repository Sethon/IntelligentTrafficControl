package com.project.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

public class Curve extends QuadCurve2D.Double implements RoadShape {
	public Curve(Point2D.Double from, Point2D.Double to, Point2D.Double control){
		super(from.getX(), from.getY(), control.getX(), control.getY(), to.getX(), to.getY());
	}
	
	public double getLength(){
		Point2D.Double a = new Point2D.Double(x1 - (2 * x2) + ctrlx, y1 - (2 * y2) + ctrly);
	    Point2D.Double b = new Point2D.Double((2 * x2) - (2 * x1), (2 * y2) - (2 * y1));
	    double A = 4 * (a.x * a.x + a.y * a.y);
	    double B = 4 * (a.x * b.x + a.y * b.y);
	    double C = b.x * b.x + b.y * b.y;
	    double Sabc = 2 * Math.sqrt(A + B + C);
	    double A_2 = Math.sqrt(A);
	    double A_32 = 2 * A * A_2;
	    double C_2 = 2 * Math.sqrt(C);
	    double BA = B / A_2;
	    return (A_32 * Sabc + A_2 * B * (Sabc - C_2) + (4 * C * A - B * B) * Math.log((2 * A_2 + BA + Sabc) / (BA + C_2))) / (4 * A_32);
	}
	
	public Point2D.Double getPointAlong(double progress){
		Line ac = new Line(this.getP1(), this.getCtrlPt());
		Line cb = new Line(this.getCtrlPt(), this.getP2());
		Point2D.Double p = ac.getPointAlong(progress);
		Point2D.Double q = cb.getPointAlong(progress);
		Line pq = new Line(p, q);
		return pq.getPointAlong(progress);
	}

	public Line2D.Double getSegment(double minSegmentLength, int i) {
		int nSegments = getNumSegments(minSegmentLength);
		int nPoints = nSegments + 1;
		double pointProgress = 1.0/nPoints;
		
		Point2D from = getPointAlong(pointProgress * i);
		Point2D to;
		if(i == nSegments - 1){
			to = this.getP2();
		}else{
			to = getPointAlong(pointProgress * (i+1));
		}
		
		return new Line2D.Double(from, to);
	}
}
