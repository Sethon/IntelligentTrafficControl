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
		
		//draw all the roads
		for(Road road: map.roads){
			g2.setStroke(roadStroke);
			g2.setColor(roadColor);
			g2.draw((Shape)road.getShape());
			g2.setStroke(dividerStroke);
			g2.setColor(DividerColor);
			g2.draw((Shape)road.getShape());
		}
		
		//draw all the intersections
		g2.setColor(roadColor);
		for(Intersection inter: map.intersections){
			g2.fillRect((int)inter.getPosition().x, (int)inter.getPosition().y, (int)Globals.LANE_WIDTH*4, (int)Globals.LANE_WIDTH*4);
		}
		
		for(Road road: map.roads){
			g2.setStroke(carStroke);
			g2.setColor(carColor);
			for(Line2D.Double carLine: road.getCarLines()){
				g2.draw(carLine);
				//g2.draw(new Line2D.Double(0, 0, carLine.x1, carLine.y1));
				//g2.draw(new Line2D.Double(15, 0, carLine.x2, carLine.y2));
			}
		}
	}
}
