package com.project.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import com.project.base.Controller;
import com.project.graph.Node;
import com.project.map.Map;
import com.project.model.Road;


public class TrafficPanel extends JPanel{
	
	private static final long serialVersionUID = -8086343848521884074L;
	private Controller controller;
	private ArrayList<Node> nodeList;
	private ArrayList<Road> edgeList;
	
	/**
	 * Max road location 	x-axis: 1200
	 * 						y-axis: 750
	 * @param controller
	 */
	
	public TrafficPanel(Controller controller){
		this.controller = controller;
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Road>();
		run();
	}
	
	private void run(){
		new Map().manhattan(nodeList, edgeList);
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Point2D center = new Point2D.Float(this.getWidth() / 2, this.getHeight() / 2);
		float radius = this.getWidth() / 2;
		float[] dist = { 
				0.6f, 0.8f 
		};
		Color[] colors = { 
				new Color(180, 180, 180), 
				new Color(160, 160, 160) 
		};
		RadialGradientPaint gp = new RadialGradientPaint(center, radius, dist, colors);
		g2.setPaint(gp);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(Node x: nodeList)
			x.drawNode(g2);
		
		for(Road x: edgeList)
			x.drawRoad(g2);
		
	}
	
}
