package com.project.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private Point position;
	//private boolean hasTrafficLights;
	private ArrayList<Edge> outgoingEdges;
	
	public Node(Point position){
		this.position = position;
	//	this.hasTrafficLights = hasTrafficLights;
		this.outgoingEdges = new ArrayList<Edge>();
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(Point newPosition){
		position = newPosition;
	}
	/*
	public void setHasTrafficLights(boolean value){
		hasTrafficLights = value;
	}
	
	public boolean getHasTrafficLights(){
		return hasTrafficLights;
	}
	*/
	public void connect(Node to, double speedLimit,double distance){
		Edge e = new Edge(this, to, speedLimit,distance);
		outgoingEdges.add(e);
	}
	
	public ArrayList<Edge> getOutgoingEdges(){
		// We make a copy, so our internal list cannot be modified.
		return new ArrayList<Edge>(outgoingEdges);
	}
	
	public void drawNode(Graphics2D g2){
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(this.position.x, this.position.y, Edge.roadWidth, Edge.roadWidth);
	
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(this.position.x, this.position.y, Edge.roadWidth, Edge.roadWidth);
		
	}
	
}
