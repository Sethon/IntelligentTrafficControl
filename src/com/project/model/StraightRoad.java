package com.project.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class StraightRoad extends Road{

	public StraightRoad(Point2D start, Point2D end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void drawRoad(Graphics2D g2){
		g2.setStroke(EDGE_OF_ROAD);
		g2.setColor(Color.darkGray);

		//draw the line, mapping coordinates to be "normal" i.e. like math graphs
		g2.drawLine(x1, y1, x2, y2);
	}

}
