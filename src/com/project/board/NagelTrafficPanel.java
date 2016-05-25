package com.project.board;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import com.project.base.Controller;
import com.project.map.NagelMap;
import com.project.model.Road;
import com.project.model.Globals;
import com.project.model.Intersection;;


public class NagelTrafficPanel extends JPanel{
	
	private static final long serialVersionUID = -8086343848521884074L;
	private Controller controller;
	private TimerTask mapRunner;
	 
	
	/**
	 * Max road location 	x-axis: 1200
	 * 						y-axis: 750
	 * @param controller
	 */
	/**
	 * 
	 * @param controller
	 */
	//constructor: creates new timer task
	// run method: calles the timer, does: the update
	public NagelTrafficPanel(Controller controller){
		this.controller = controller;
		mapRunner = new TimerTask() {
			public void run(){
				System.out.println("start tick");
				controller.getNagelMap().tick();
				System.out.println("repaint");
				repaint();
				System.out.println("end tick");
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(mapRunner, 0, 1000);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawMap(controller.getNagelMap(), g2);
	}
	//draws map into the panel 
	private void drawMap(NagelMap map, Graphics2D g2){
		//Strokes define how lines are drawn.
		BasicStroke roadStroke = new BasicStroke(
				(float)Globals.LANE_WIDTH*2,
				BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND,
				1.0f
		);
		BasicStroke dividerStroke = new BasicStroke(
				0.5f,
				BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND,
				1.0f,
				new float[]{5f, 5f},
				0
		);
		BasicStroke carStroke = new BasicStroke((float)Globals.LANE_WIDTH*0.5f);
		Color roadColor = Color.DARK_GRAY;
		Color DividerColor = Color.WHITE;
		Color carColor = Color.BLUE;
		
		if(map.testCar.trajectory.onIntersection){
			g2.setColor(Color.RED);
		}else{
			g2.setColor(Color.WHITE);
		}
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//draw all the roads
		for(Road road: map.roads){
			g2.setStroke(roadStroke);
			g2.setColor(roadColor);
			if(road == map.testCar.trajectory.currentLane.road){
				g2.setColor(Color.RED);
			}
			if(road == map.testCar.trajectory.getNextLane().road){
				g2.setColor(Color.PINK);
			}
			g2.draw((Shape)road.getShape());
			g2.setStroke(dividerStroke);
			g2.setColor(DividerColor);
			g2.draw((Shape)road.getShape());
		}
		
		//draw all the intersections
		g2.setColor(roadColor);
		for(Intersection inter: map.intersections){
			g2.fillRect((int)inter.getPosition().x, (int)inter.getPosition().y, (int)Globals.LANE_WIDTH*4, (int)Globals.LANE_WIDTH*4);
			
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(1));
			for(Road r:inter.getIncomingRoads()){
				if(r==null)continue;
				Point2D.Double p1 = inter.getConnectionPoint(r.leftLane);
				Point2D.Double p2 = inter.getConnectionPoint(r.rightLane);
				g2.fillRect((int)p1.x, (int)p1.y, 1, 1);
				g2.fillRect((int)p2.x, (int)p2.y, 1, 1);
			}
			g2.setColor(Color.ORANGE);
			for(Road r:inter.getOutgoingRoads()){
				if(r==null) continue;
				Point2D.Double p1 = inter.getConnectionPoint(r.leftLane);
				Point2D.Double p2 = inter.getConnectionPoint(r.rightLane);
				g2.fillRect((int)p1.x, (int)p1.y, 1, 1);
				g2.fillRect((int)p2.x, (int)p2.y, 1, 1);
			}
			g2.setColor(roadColor);
		}
		
		for(Road road: map.roads){
			g2.setStroke(carStroke);
			g2.setColor(carColor);
			for(Line2D.Double carLine: road.getCarLines()){
				g2.draw(carLine);
			}
		}
		
		for(Intersection inter: map.intersections){
			g2.setStroke(carStroke);
			g2.setColor(carColor);
			for(Line2D.Double carLine: inter.getCarLines()){
				g2.draw(carLine);
			}
		}
		
		
		Intersection testInter = map.intersections.get(0);
		g2.setStroke(new BasicStroke(1));
		for(Road fr: testInter.getIncomingRoads()){
			for(Road tr: testInter.getOutgoingRoads()){
				testInter.getInsideRoad(fr.leftLane, tr.leftLane);
			}
		}
		
		g2.setColor(Color.WHITE);
		for(Road r:testInter.getInsideRoads()){
			g2.draw((Shape)r.getShape());
		}
		//g2.setColor(Color.YELLOW);
		//g2.draw((Shape)map.testCar.trajectory.getNextLane().road.getShape());
		//g2.setColor(Color.MAGENTA);
		//g2.draw((Shape)map.testCar.trajectory.currentLane.road.getShape());
		for(Road r: map.roads){
			for(int i=0;i<r.getLength();i++){
				if(r.leftLane.hasCarAt(i) || r.rightLane.hasCarAt(i)){
					System.out.println("Car position "+i+" of "+r.getLength());
				}
			}
		}
	}
}
