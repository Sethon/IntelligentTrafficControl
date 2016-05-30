package com.project.board;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import com.project.base.Controller;
import com.project.geom.Utils;
import com.project.map.NagelMap;
import com.project.model.Road;
import com.project.model.CarLine;
import com.project.model.Globals;
import com.project.model.Intersection;
import com.project.model.Lane;;


public class NagelTrafficPanel extends JPanel{
	
	private static final long serialVersionUID = -8086343848521884074L;
	private Controller controller;
	private TimerTask mapRunner;
	
	private int panX = 0;
	private int panY = 0;
	private int prevMouseX;
	private int prevMouseY;
	private int mouseX = 0;
	private int mouseY = 0;
	private double scale = 1.0;
	
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
		t.scheduleAtFixedRate(mapRunner, 0, 200);
		MouseAdapter ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				prevMouseX = e.getX();
				prevMouseY = e.getY();
			}
			
			public void mouseMoved(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY();
			}
			
			public void mouseDragged(MouseEvent e) {
				panX += e.getX() - prevMouseX;
				panY += e.getY() - prevMouseY;
				prevMouseX = e.getX();
				prevMouseY = e.getY();
				repaint();
			}
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				scale = Math.max(0, Math.min(5, scale + 0.1 * e.getWheelRotation()));
				repaint();
			}
		};
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);
		this.addMouseWheelListener(ml);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawMap(controller.getNagelMap(), g2);
	}
	//draws map into the panel 
	private void drawMap(NagelMap map, Graphics2D g2){
		//apply transforms for panning and zooming
		applyTransforms(g2);
		
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
		Color roadColor = Color.DARK_GRAY;
		Color DividerColor = Color.WHITE;
		
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
		
		ArrayList<CarLine> carLines = new ArrayList<CarLine>();
		for(Road road: map.roads){
			carLines.addAll(road.getCarLines());
		}
		
		for(Intersection inter: map.intersections){
			carLines.addAll(inter.getCarLines());
		}
		
		for(CarLine carLine: carLines){
			drawCarLine(carLine, g2);
		}
		
		for(Intersection inter:map.intersections){
			for(Road r: inter.getIncomingRoads()){
				if(r == null) continue;
				drawLight(inter, r.leftLane, g2);
				drawLight(inter, r.rightLane, g2);
			}
		}
	}
	
	private void drawLight(Intersection inter, Lane lane, Graphics2D g2){
		if(inter.hasGreenLight(lane)){
			g2.setColor(Color.GREEN);
		}else{
			g2.setColor(Color.RED);
		}
		Point2D.Double p = inter.getConnectionPoint(lane);
		int size = (int)(Globals.LANE_WIDTH*0.5);
		g2.fillRect((int)p.x-size/2, (int)p.y-size/2, size, size);
	}
	
	private void drawCarLine(Line2D.Double line, Graphics2D g2){
		BasicStroke carStroke = new BasicStroke((float)Globals.LANE_WIDTH*0.5f);
		Color carColor = Color.YELLOW;
		g2.setStroke(carStroke);
		g2.setColor(carColor);
		g2.draw(Utils.scale(line, 0.1));
	}
	
	private void applyTransforms(Graphics2D g2){
		AffineTransform t = new AffineTransform();
		t.concatenate(AffineTransform.getTranslateInstance(panX, panY));
		t.concatenate(AffineTransform.getScaleInstance(scale, scale));
		g2.setTransform(t);
		
	}
}
