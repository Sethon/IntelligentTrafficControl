package com.project.model;

import java.util.Random;

import com.project.stats.Record;

public class Car{
	
	int MinSpeed = 0;
	int MaxSpeed = 20;
	
	public final Trajectory trajectory;
	private int velocity;
	public final int ID;
	
	public Car(Trajectory traj){
		trajectory = traj;
		velocity = 0;
		ID = new Random().nextInt(65536);
	}
	
	public void accellerate(){
		velocity = Math.min(velocity + 1, MaxSpeed);	
	}
	
	public void slowDown(int gap){ // if the gap between 2 cars is smaller than the ActualV of the car behind 
													//then the car have to slowdown for not having a collision
		if(velocity > gap)
		{
			velocity = gap;
		}
	}
	
	public void randomSlowDown(){ // inserting a probablity of each car to slow down by one V's unit
		
		if( (velocity > 0 ) && ( Math.random() <= 0.3) ){
			velocity -= 1;
		}	
	}
	
	public int getVelocity(int gap){
		accellerate();
		slowDown(gap);
		randomSlowDown();
		return velocity;
	}

	public void collectStats(Record rec) {
		rec.setValue("carID", ID);
		rec.setValue("velocity", velocity);
	}
}