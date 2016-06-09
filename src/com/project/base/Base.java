package com.project.base;

import java.awt.EventQueue;

import com.project.map.NagelMap;


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
				m.generate();
				
				Controller controller = new Controller();
				controller.init();
				controller.setNagelMap(m);
				
				//Use this part for running the simulation without the GUI, for generating stats.
				// Comment out the lines above to stop the gui from running
				/*
				for(int i=0;i<1000;i++){
					m.tick();
				}
				m.getStats().saveCSVFile();
				*/
			}
		});
	}
	
}