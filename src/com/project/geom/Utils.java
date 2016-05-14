package com.project.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Utils {
	public static Point2D.Double translate(Point2D pt, double x, double y){
		return new Point2D.Double(pt.x +x, pt.y + y);
	}
	
	public static double getAngle(Point2D from, Point2D to) {
	    return Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
	}
	
	public static Line2D.Double getOffsetLine(Line2D.Double line, double offset){
		double angle = getAngle(line.getP1(), line.getP2());
		double xinc = Math.cos(angle) * offset;
		double yinc = Math.sin(angle) * offset;
		return new Line2D.Double(
				translate(line.getP1(), xinc, yinc),
				translate(line.getP2(), xinc, yinc)
			);
	}
}
