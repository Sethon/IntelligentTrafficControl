package com.project.road;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class Road {
	protected final double laneWidthMeters;
	  protected final int numLanes;

	  public Road(int numLanes, double laneWidthMeters) {
	    this.numLanes = numLanes;
	    this.laneWidthMeters = laneWidthMeters;
	  }

	  public abstract Rectangle2D getBoundsMeters();
	
	  public abstract List<Shape> getLaneMarkers();
	  
	  public abstract Shape getRoadCenter();

	  public abstract double getRoadLengthMeters();


	  public double getLaneWidthMeters() {
	    return laneWidthMeters;
	  }
	  
	  
	  
	  public int getNumLanes() {
	    return numLanes;
	  }
	  
	
	  
	  public abstract boolean transformForCar(Graphics2D g2,
	      double centerPosition, int lane);
	  
	  
}

