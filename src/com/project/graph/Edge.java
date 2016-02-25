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
		g2.setStroke(new BasicStroke(2));			
//		double a[] = segment.getRight();
		Line2D aLine = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY(), to.getPosition().getX(), to.getPosition().getY());
		g2.draw(aLine);

//		double c[] = segment.getLeft();
		if(from.getPosition().getX() == to.getPosition().getX()){
			Line2D bLine = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY() + roadWidth, to.getPosition().getX(), to.getPosition().getY() + roadWidth);
			g2.draw(bLine);
		}else{
			Line2D bLine = new Line2D.Double(from.getPosition().getX() + roadWidth, from.getPosition().getY(), to.getPosition().getX() + roadWidth, to.getPosition().getY());
			g2.draw(bLine);
		}
	}
}
