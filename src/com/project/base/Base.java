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
				//Controller controller = new Controller();
				//controller.init();
				
				NagelMap m = new NagelMap();
				m.generate();
				for(int i=0;i<1000;i++){
					m.tick();
				}
				m.getStats().saveCSVFile();
			}
		});
	}
	
}