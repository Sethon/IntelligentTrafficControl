package com.project.model;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Road {
	protected final Stroke EDGE_OF_ROAD = new BasicStroke(1f);
	private Point2D start;
	private Point2D end;
	private ArrayList<Car> cars;
	

	public Road(Point2D start, Point2D end){
		this.start = start;
		this.end = end;
		this.cars = new ArrayList<Car>();
	}
	
	
	
	public ArrayList<Car> getCars(){
		return this.cars;
	}
	
	
	
	public Point2D getStart(){
		return this.start;
	}
	
	
	
	public Point2D getEnd(){
		return this.end;
	}
	
	
}
