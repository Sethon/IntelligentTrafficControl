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
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import com.project.base.Controller;
import com.project.geom.Line;
import com.project.geom.Utils;
import com.project.map.NagelMap;
import com.project.model.Road;
import com.project.model.CarLine;
import com.project.model.Globals;
import com.project.model.Intersection;
import com.project.model.Lane;;


public class NagelTrafficPanel extends BaseSimulationPanel{
	
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
	
	// tickProgress is a number between 0 and 1, and keeps track of how far along we are in the current tick.
	// see line 72 for more info
	private double tickProgress = 0;
	
	//We have two lists of CarLines, one for the previous tick, and one for the current tick.
	//This is used for smoothly animating cars between ticks.
	private ArrayList<CarLine> lastCarLines;
	private ArrayList<CarLine> currentCarLines;
	
	public int fps = 30;
	
	//constructor: creates new timer task
	// run method: calles the timer, does: the update
	public NagelTrafficPanel(Controller controller){
		this.controller = controller;
		lastCarLines = controller.getNagelMap().getCarLines();
		currentCarLines = lastCarLines;
		
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
	
	public void activate(){
		// This TimerTask is run 'fps' times per second
		mapRunner = new TimerTask() {
			private int frame = 0;
			private final int tickFreq = 10;
			public void run(){
				// This is true every "tickFreq" frames
				if(frame == 0){
					controller.getNagelMap().tick();
					//update our CarLine arrays
					lastCarLines = currentCarLines;
					currentCarLines = controller.getNagelMap().getCarLines();
				}
				// every frame, we update tickProgress to reflect how many frames out of the total tickFrew we've had.
				// We animate cars based on this. For example, if this number is 0.75, then cars will be 75% of the way
				// between their previous position and their current position.
				tickProgress = frame/(double)tickFreq;
				repaint();		
				frame += 1;
				// this ensures that as soonas frame == tickFreq, it is reset to 0
				frame %= tickFreq;
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(mapRunner, 0, 1000/fps);
	}
	
	public void deactivate(){
		mapRunner.cancel();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawMap(controller.getNagelMap(), g2);
	}
	
	private ArrayList<CarLine> getCarLines(){
		// Returns the CarLines corresponding to all the cars on the map.
		// This is where most of the smooth animation magic happens.
		
		//These are two hashTables, mapping from car IDs to CarLines.
		Hashtable<Integer, CarLine> from = mapCarLines(lastCarLines);
		Hashtable<Integer, CarLine> to = mapCarLines(currentCarLines);
		ArrayList<CarLine> lines = new ArrayList<CarLine>();
		//loop over every carID of a car on the map
		for(int carID: from.keySet()){
			//Get tthe CarLine corresponding to their previous (fromLine) and current (toLine) positions.
			CarLine fromLine = from.get(new Integer(carID));
			CarLine toLine = to.get(new Integer(carID));
			if(fromLine == null || toLine == null){
				continue;
			}
			// A car's line has 2 points. line1 and line2 are the lines along each of these points has to move in order to go
			// from its previous to its current position.
			Line line1 = new Line(fromLine.getP1(), toLine.getP1());
			Line line2 = new Line(fromLine.getP2(), toLine.getP2());
			// Create a new CarLine, with its two points set to the point along the path it has to move.
			lines.add(new CarLine(new Line(line1.getPointAlong(tickProgress), line2.getPointAlong(tickProgress)), carID));
		}
		return lines;
	}
	
	private Hashtable<Integer, CarLine> mapCarLines(ArrayList<CarLine> lines){
		//This converts an ArrayList of CarLines to a Hashtable, making it easier to get CarLines from their car's ID.
		Hashtable<Integer, CarLine> table = new Hashtable<Integer, CarLine>(lines.size());
		for(CarLine line: lines){
			table.put(new Integer(line.carID), line);
		}
		return table;
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
		
		//draw all the cars
		ArrayList<CarLine> carLines = getCarLines();
		for(CarLine carLine: carLines){
			drawCarLine(carLine, g2);
		}
		
		//draw the traffic lights
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
