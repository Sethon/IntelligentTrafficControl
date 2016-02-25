package com.project.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class SingleRoad extends Edge{

	public SingleRoad(Node from, Node to, double speedLimit) {
		super(from, to, speedLimit);
		// TODO Auto-generated constructor stub
	}
	
	public void drawRoad(Graphics2D g2){	
		g2.setStroke(new BasicStroke(Edge.roadWidth));		
		Line2D outA;
		Line2D outB;	
		if(from.getPosition().getX() < to.getPosition().getX() && from.getPosition().getY() == to.getPosition().getY()){
			Line2D aLine = new Line2D.Double(from.getPosition().getX()+1.5*Edge.roadWidth, from.getPosition().getY()+Edge.roadWidth/2, to.getPosition().getX()-Edge.roadWidth/2, to.getPosition().getY()+Edge.roadWidth/2);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(aLine);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			outA = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY(), to.getPosition().getX()+Edge.roadWidth*(3/4), to.getPosition().getY());
			outB = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth*(3/4), to.getPosition().getY()+Edge.roadWidth);
			g2.draw(outA);
			g2.draw(outB);
		}
		
		if(from.getPosition().getX() > to.getPosition().getX() && from.getPosition().getY() == to.getPosition().getY()){
			Line2D aLine = new Line2D.Double(from.getPosition().getX()-Edge.roadWidth/2, from.getPosition().getY()+Edge.roadWidth/2, to.getPosition().getX()+1.5*Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth/2);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(aLine);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			outA = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth*(3/4), from.getPosition().getY(), to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY());
			outB = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth*(3/4), from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth);
			g2.draw(outA);
			g2.draw(outB);
		}
		
		if(from.getPosition().getY() < to.getPosition().getY() && from.getPosition().getX() == to.getPosition().getX()){
			Line2D aLine = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth/2, from.getPosition().getY()+1.5*Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth/2, to.getPosition().getY()-Edge.roadWidth/2);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(aLine);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			outA = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX(), to.getPosition().getY());
			outB = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY());
			g2.draw(outA);
			g2.draw(outB);
		}
		
		if(from.getPosition().getY() > to.getPosition().getY() && from.getPosition().getX() == to.getPosition().getX()){
			Line2D aLine = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth/2, from.getPosition().getY()-Edge.roadWidth/2, to.getPosition().getX()+Edge.roadWidth/2, to.getPosition().getY()+1.5*Edge.roadWidth);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(aLine);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			outA = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY(), to.getPosition().getX(), to.getPosition().getY()+Edge.roadWidth);
			outB = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY(), to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth);
			g2.draw(outA);
			g2.draw(outB);
		}
		
//		Line2D outA;
//		Line2D outB;		
//		g2.setColor(Color.WHITE);
//		g2.setStroke(new BasicStroke(1));
//		if(from.getPosition().getX() == to.getPosition().getX()){
//			outA = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX(), to.getPosition().getY()+Edge.roadWidth);
//			outB = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth);
//			g2.draw(outA);
//			g2.draw(outB);
//		}
//		if(from.getPosition().getY() == to.getPosition().getY()){
//			outA = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY(), to.getPosition().getX(), to.getPosition().getY());
//			outB = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth);			
//			g2.draw(outA);
//			g2.draw(outB);
//		}
		
		
		
	}

}
