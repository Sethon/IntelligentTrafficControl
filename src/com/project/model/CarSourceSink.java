package com.project.model;

import java.awt.geom.Point2D.Double;
import java.util.Random;

public class CarSourceSink extends Intersection{
	// An intersection that puts new cars on the road with a certain probability,
	// and removes cars that drive on to it.
	
	private Random rand = new Random();
	//The probability of a car spawning at each tick, on any outgoing road is 1/carProb
	private int carProb = 0;
	
	public CarSourceSink(Double position) {
		super(position);
		setHasTrafficLights(false);
	}
	
	public void setCarSpawnProbability(double prob){
		carProb = (int)(1/prob);
	}
	
	protected Road makeInsideRoad(Lane from, Lane to){
		Road r = super.makeInsideRoad(from, to);
		r.leftLane = new SinkLane(r);
		r.rightLane = new SinkLane(r);
		System.out.println("Created a sink lane!");
		return r;
	}
	
	public void update(){
		super.update();
		for(Road r: getOutgoingRoads()){
			if(r != null){
				if(rand.nextInt(carProb) == 0){
					spawnCar(r);
				}
			}
		}
	}
	
	public void spawnCar(Road r){
		Lane l = rand.nextBoolean() ? r.leftLane : r.rightLane;
		if(!l.hasCarAt(0)){
			l.acceptCar(new Car(new RandomTrajectory()), 0);
		}
	}
}
