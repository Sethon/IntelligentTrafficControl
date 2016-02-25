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
		g2.setStroke(new BasicStroke(Edge.roadWidth));		
		
		Line2D aLine = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth/2, from.getPosition().getY()+Edge.roadWidth/2, to.getPosition().getX()+Edge.roadWidth/2, to.getPosition().getY()+Edge.roadWidth/2);
		g2.setColor(Color.DARK_GRAY);
		g2.draw(aLine);
		
		
		g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
	
		Line2D dashed = new Line2D.Double(from.getPosition().getX()+Edge.roadWidth/2, from.getPosition().getY()+Edge.roadWidth/2, to.getPosition().getX()+Edge.roadWidth/2, to.getPosition().getY()+Edge.roadWidth/2);
		g2.setColor(Color.WHITE);
		
		g2.draw(dashed);
	}
	
	
}
