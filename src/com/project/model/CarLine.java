package com.project.model;

import java.awt.geom.Line2D;

public class CarLine extends Line2D.Double {
	public final int carID;
	
	public CarLine(Line2D.Double line, Car car){
		super(line.getP1(), line.getP2());
		carID = car.ID;
	}
}
