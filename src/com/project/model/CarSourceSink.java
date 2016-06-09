package com.project.model;

import java.awt.geom.Point2D.Double;
import java.util.Random;

public class CarSourceSink extends Intersection{
	// An intersection that puts new cars on the road with a certain probability,
	// and removes cars that drive on to it.
	
	private Random rand = new Random();
	//The probability of a car spawning at each tick, on any outgoing road is 1/carProb
	private int carProb = 2;
	
	public void setCarSpawnProbability(double prob){
		carProb = (int)(1/prob);
	}
	
	public CarSourceSink(Double position) {
		super(position);
	}
	
	protected Road makeInsideRoad(Lane from, Lane to){
		Road r = super.makeInsideRoad(from, to);
		r.leftLane = new SinkLane(r);
		r.rightLane = new SinkLane(r);
		return r;
	}
	
	public void update(){
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
		l.addCar(new Car(new RandomTrajectory()), 0);
	}
}
