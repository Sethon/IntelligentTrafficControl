package com.project.visuals.frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

public class Frame extends JFrame implements ComponentListener {

	private static final long serialVersionUID = 7105409066216982655L;
	private Dimension normalSize;
	private Point normalLocation;

	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1500, 1500 / 16 * 9));
		this.addComponentListener(this);
		this.setTitle("Intelligent Traffic Control");
	}

	
	
	public void actionPerformed() {
		if (getExtendedState() == JFrame.MAXIMIZED_BOTH && isUndecorated()) {
			dispose();
			setUndecorated(false);
			setPreferredSize(normalSize);
			setExtendedState(JFrame.NORMAL);
			setLocation(normalLocation);
			pack();
			setVisible(true);
		} else {
			dispose();
			setUndecorated(true);
			setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
		}
	}

	
	
	public void componentHidden(ComponentEvent e) {

	}

	
	
	public void componentMoved(ComponentEvent e) {

	}

	
	
	public void componentResized(ComponentEvent e) {
		this.setPreferredSize(this.getSize());
	}

	
	
	public void componentShown(ComponentEvent e) {

	}
}
