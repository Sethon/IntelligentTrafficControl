package com.project.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import com.project.graph.Road;

public class Car {
	private final int CAR_RADIUS = 4;
	private Color color;
	private Road currentRoad;
	private Point2D position;
	private double length;
	
	
	public Car(){
		this.color = getRandColor();
		this.length = 2;
	}
	
	
	
	private Color getRandColor(){
		return new Color((int) Math.ceil(Math.random() * 255), 
				(int) Math.ceil(Math.random() * 255), 
				(int) Math.ceil(Math.random() * 255));
	}
	

	
	public Road getCurrentRoad(){
		return this.currentRoad;
	}
	
	
	
	public void setCurrentRoad(Road currentRoad){
		this.currentRoad = currentRoad;
	}
	
	
	
	public Point2D getPosition(){
		return this.position;
	}
	
	
	
	public void setPosition(Point2D position){
		this.position = position;
	}
	
	
	
	public Color getColor(){
		return this.color;
	}
	
	
	
	public double getLength(){
		return this.length;
	}
	
	
	
	public void drawCar(Graphics2D g2){
		int x = (int) this.position.getX() - CAR_RADIUS;
		int y = (int) this.position.getY() + CAR_RADIUS;
		
		g2.setColor(this.getColor());
		g2.fillRect(x, y, CAR_RADIUS*2, CAR_RADIUS*2);
	}
}
