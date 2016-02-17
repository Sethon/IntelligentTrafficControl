package com.project.graph;

import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private Point position;
	private boolean hasTrafficLights;
	private ArrayList<Edge> outgoingEdges;
	
	public Node(Point position, boolean hasTrafficLights){
		this.position = position;
		this.hasTrafficLights = hasTrafficLights;
		this.outgoingEdges = new ArrayList<Edge>();
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(Point newPosition){
		position = newPosition;
	}
	
	public void setHasTrafficLights(boolean value){
		hasTrafficLights = value;
	}
	
	public boolean getHasTrafficLights(){
		return hasTrafficLights;
	}
	
	public void connect(Node to, double speedLimit){
		Edge e = new Edge(this, to, speedLimit);
		outgoingEdges.add(e);
	}
	
	public ArrayList<Edge> getOutgoingEdges(){
		// We make a copy, so our internal list cannot be modified.
		return new ArrayList<Edge>(outgoingEdges);
	}
}
