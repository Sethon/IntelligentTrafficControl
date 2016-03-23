package com.project.visuals.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import com.project.base.Controller;
import com.project.board.TrafficMenu;
import com.project.board.TrafficPanel;
import com.project.cellular.CellTypes;
import com.project.cellular.Grid;
import com.project.cellular.Map;
import com.project.cellular.RandomCarAdder;

public class SimulationFrame extends JSplitPane{

	private static final long serialVersionUID = 4036879803456305768L;
	
	private TrafficPanel trafficPanel;
	private TrafficMenu trafficMenu;
	private Controller controller;
	
	public SimulationFrame(Controller controller){
		this.controller = controller;
		this.initMap();
		this.trafficPanel = new TrafficPanel(controller);
		this.trafficMenu = new TrafficMenu(controller);

		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.setBorder(null);
		this.setResizeWeight(1);
		this.setDividerSize(0);
		this.add(trafficPanel, JSplitPane.LEFT);
		this.add(trafficMenu, JSplitPane.RIGHT);
	}
	
	public void initMap(){
		if(this.controller.getCurrentMap() == null){
			Grid grid = new Grid(21, 21);
			
			for(int i=0; i<21; i++){
				//border
				grid.addCell(0, i, CellTypes.UP_ROAD);
				grid.addCell(i, 0, CellTypes.RIGHT_ROAD);
				grid.addCell(20, i, CellTypes.DOWN_ROAD);
				grid.addCell(i, 20, CellTypes.LEFT_ROAD);
				
				//cross
				grid.addCell(i, 10, CellTypes.RIGHT_ROAD);
				grid.addCell(10, i, CellTypes.DOWN_ROAD);
			}
			grid.addCell(0, 0, CellTypes.RIGHT_ROAD);
			grid.addCell(10, 0, CellTypes.RIGHT_ROAD);
			grid.addCell(20, 0, CellTypes.DOWN_ROAD);
			grid.addCell(20, 10, CellTypes.DOWN_ROAD);
			grid.addCell(10, 10, CellTypes.LEFT_ROAD);
			grid.addCell(10, 20, CellTypes.LEFT_ROAD);
			grid.addCell(0, 20, CellTypes.UP_ROAD);
			grid.addCell(0, 10, CellTypes.UP_ROAD);
			
			Map map = new Map(grid);
			map.initCars(new RandomCarAdder(3));
			
			controller.setCurrentMap(map);
		}
	}
	
	public JPanel getTrafficPanel(){
		return this.trafficPanel;
	}
	
	
	
	public JPanel getP2(){
		return this.trafficMenu;
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
	}
	
}
