package com.project.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;

import com.project.model.Car;

public class Road extends Edge{
	private ArrayList<Car> cars;
	
	public Road(Node from, Node to, int speedLimit,int distance) {
		super(from, to, speedLimit,distance);
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
	
	
	
		
	
}
