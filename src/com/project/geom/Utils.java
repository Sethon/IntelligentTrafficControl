package com.project.geom;

import java.awt.geom.Point2D;

public class Utils {
	public static Point2D.Double translate(Point2D.Double pt, double x, double y){
		return new Point2D.Double(pt.x +x, pt.y + y);
	}
}
