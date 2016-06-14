package com.project.model;

import java.awt.geom.Point2D.Double;
import java.util.Random;

import com.project.map.NagelMap;

public class CarSourceSink extends Intersection{
	// An intersection that puts new cars on the road with a certain probability,
	// and removes cars that drive on to it.
	
	private Random rand = new Random();
	//The probability of a car spawning at each tick, on any outgoing road is 1/carProb
	private int carProb = 5;
	
	private NagelMap map;
	
	public CarSourceSink(Double position, NagelMap nagelMap) {
		super(position);
		setHasTrafficLights(false);
		map = nagelMap;
	}
	
	public void setCarSpawnProbability(double prob){
		carProb = (int)(1/prob);
	}
	
	protected Road makeInsideRoad(Lane from, Lane to){
		//make sure inside roads have SinkLanes instead of normal lanes.
		Road r = super.makeInsideRoad(from, to);
		r.leftLane = new SinkLane(r);
		r.rightLane = new SinkLane(r);
		return r;
	}
	
	public void update(){
		System.out.println("Update!");
		super.update();
		//randomly spawn cars to outgoing roads
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
			map.addCar(map.makeCar(), l, 0);
		}
	}
}
