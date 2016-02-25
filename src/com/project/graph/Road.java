package com.project.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;

import com.project.model.Car;

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
