package com.project.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.project.graph.Edge;
import com.project.graph.Node;

public class Road extends Edge{
	private ArrayList<Car> cars;
	
	public Road(Node from, Node to, double speedLimit) {
		super(from, to, speedLimit);
		// TODO Auto-generated constructor stub
		this.cars = new ArrayList<Car>();
	}



	public ArrayList<Car> getCars(){
		return this.cars;
	}
	
	
	
	public Node getFrom(){
		return this.from;
	}
	
	
	
	public Node getTo(){
		return this.to;
	}
	
	
	public void drawRoad(Graphics2D g2){
		g2.setStroke(new BasicStroke(40));		
//		double a[] = segment.getRight();
		Line2D aLine = new Line2D.Double(from.getPosition().getX()+20, from.getPosition().getY()+20, to.getPosition().getX()+20, to.getPosition().getY()+20);
		
		g2.draw(aLine);
		
		

//		double c[] = segment.getLeft();
//		if(from.getPosition().getX() == to.getPosition().getX()){
//			Line2D bLine = new Line2D.Double(from.getPosition().getX(), from.getPosition().getY() + roadWidth, to.getPosition().getX(), to.getPosition().getY() + roadWidth);
//			g2.draw(bLine);
//		}else{
//			Line2D bLine = new Line2D.Double(from.getPosition().getX() + roadWidth, from.getPosition().getY(), to.getPosition().getX() + roadWidth, to.getPosition().getY());
//			g2.draw(bLine);
//		}
	}
	
	
}
