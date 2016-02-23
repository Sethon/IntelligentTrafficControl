package com.project.model;

import java.awt.Color;

public class Car {
	private Color color;
	private Road currentRoad;
	private double position;
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
	
	
	
	public double getPosition(){
		return this.position;
	}
	
	
	
	public void setPosition(double position){
		this.position = position;
	}
	
	
	
	public Color getColor(){
		this.color = color;
	}
	
	
	
	public double getLength(){
		return this.length;
	}
}
