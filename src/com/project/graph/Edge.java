package com.project.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Edge {
	public final static int roadWidth = 40;
	protected Node from;
	protected Node to;
	private double speedLimit;
	
	public Edge(Node from, Node to, double speedLimit){
		this.from = from;
		this.to = to;
		this.speedLimit = speedLimit;
	}
	
	public Node getFrom(){
		return from;
	}
	
	public void setFrom(Node newFrom){
		from = newFrom;
	}
	
	public Node getTo(){
		return to;
	}
	
	public void setTo(Node newTo){
		to = newTo;
	}
	
	public double getSpeedLimit(){
		return speedLimit;
	}
	
	public void setSpeedLimit(double newSpeedLimit){
		speedLimit = newSpeedLimit;
	}
	
	public double getLength(){
		double xdist2 = Math.pow(this.from.getPosition().getX() - this.to.getPosition().getX(), 2);
		double ydist2 = Math.pow(this.from.getPosition().getY() - this.to.getPosition().getY(), 2);
		return Math.sqrt(xdist2 + ydist2);
	}
	
	// Returns a minimum bound on the time it takes to travel along this edge,
	// assuming constant travel at the speed limit.
	public double getMinimumTravelTime(){
		return getLength()/getSpeedLimit();
	}
	
	
	public void drawRoad(Graphics2D g2){
		
		g2.setStroke(new BasicStroke(Edge.roadWidth/2));	
		
		
		if(from.getPosition().getX() < to.getPosition().getX() && from.getPosition().getY() == to.getPosition().getY()){
			Line2D aLine = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth*1.25, from.getPosition().getY()+Edge.roadWidth/4, to.getPosition().getX()-Edge.roadWidth/4, to.getPosition().getY()+Edge.roadWidth/4);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(aLine);
			
			Line2D dashed = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY()+Edge.roadWidth/2, to.getPosition().getX(), to.getPosition().getY()+Edge.roadWidth/2);
			g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));			
			g2.setColor(Color.WHITE);
			g2.draw(dashed);			
			
			g2.setStroke(new BasicStroke(1));
			Line2D outA = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY(), to.getPosition().getX(), to.getPosition().getY());			
			g2.draw(outA);
		}
		
		
		
		if(from.getPosition().getX() > to.getPosition().getX() && from.getPosition().getY() == to.getPosition().getY()){
			g2.setStroke(new BasicStroke(Edge.roadWidth/2));	
			g2.setColor(Color.DARK_GRAY);
			Line2D aLine = new Line2D.Double(from.getPosition().getX()-Edge.roadWidth/4, from.getPosition().getY()+1*(Edge.roadWidth-10), to.getPosition().getX()+Edge.roadWidth*1.25, to.getPosition().getY()+1*(Edge.roadWidth-10));		
			g2.draw(aLine);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			Line2D outA = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth);	
			g2.draw(outA);
	
		}
		
		
		
		if(from.getPosition().getY() < to.getPosition().getY() && from.getPosition().getX() == to.getPosition().getX()){
			Line2D aLine = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth/4, from.getPosition().getY()+Edge.roadWidth*1.25, to.getPosition().getX()+Edge.roadWidth/4, to.getPosition().getY()-Edge.roadWidth/4);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(aLine);
			
			Line2D dashed = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth/2, from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()+Edge.roadWidth/2, to.getPosition().getY());
			g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));			
			g2.setColor(Color.WHITE);
			g2.draw(dashed);	
			
			g2.setStroke(new BasicStroke(1));
			Line2D outA = new Line2D.Double(from.getPosition().getX()-Edge.roadWidth*(3/4), from.getPosition().getY()+Edge.roadWidth, to.getPosition().getX()-Edge.roadWidth*(3/4), to.getPosition().getY()-Edge.roadWidth*(3/4));	
			g2.draw(outA);
		}
		
		if(from.getPosition().getY() > to.getPosition().getY()){
			g2.setStroke(new BasicStroke(Edge.roadWidth/2));	
			g2.setColor(Color.DARK_GRAY);
			Line2D aLine = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth-(Edge.roadWidth/4), from.getPosition().getY()-Edge.roadWidth/4, to.getPosition().getX()+Edge.roadWidth-(Edge.roadWidth/4), to.getPosition().getY()+Edge.roadWidth*1.25);
			g2.draw(aLine);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			Line2D outA = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth, from.getPosition().getY()-Edge.roadWidth*(3/4), to.getPosition().getX()+Edge.roadWidth, to.getPosition().getY()+Edge.roadWidth);	
			g2.draw(outA);
		}
	}	
}
