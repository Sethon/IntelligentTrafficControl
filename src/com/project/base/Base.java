package com.project.base;

import java.awt.EventQueue;


public class Base {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
				controller.init();
			}
		});
	}
	
}