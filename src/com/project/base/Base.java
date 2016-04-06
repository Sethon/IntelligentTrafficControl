package com.project.base;

import java.awt.EventQueue;


public class Base {
/**
 * Where it all starts, calls the run method
 * 
 * @param args
 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
				controller.init();
			}
		});
	}
	
}