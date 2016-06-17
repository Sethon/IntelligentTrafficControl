package com.project.base;

import java.awt.EventQueue;

import com.project.map.NagelMap;
import com.project.model.Globals;


public class Base {
/**
 * Where it all starts, calls the run method
 * 
 * @param args
 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				NagelMap m = new NagelMap();
				//m.generateCircle(10, 10, 400, 400, 100);
				
				//If true, use the car-counting traffic lights.
				//Otherwise, use the purely interval round-robin system.
				m.setSmartIntersections(true);
				
				//Pass in true here to add a CarSourceSink to the map
				m.generate(true);
				//Pass in a different number to change the number of initial cars on the map.
				m.addRandomCars(200);
				//The probability of a car spawning at each tick
				m.setCarSpawnProbability(0.2);
				
				//configuring global values
				Globals.MAX_SPEED = 20;
				Globals.STARTING_SPEED = 0; //The speed cars have when they are created.
				Globals.ALLOW_LANE_CHANGING = true;
				
				Controller controller = new Controller();
				controller.init();
				controller.setNagelMap(m);
				
				//Use this part for running the simulation without the GUI, for generating stats.
				// Comment out the lines above to stop the gui from running
				/*
				for(int i=0;i<1000;i++){
					m.tick();
				}
				m.getStats().makeSummary().saveCSVFile();
				*/
			}
		});
	}
	
}