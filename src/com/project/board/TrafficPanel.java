package com.project.board;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import com.project.base.Controller;
import com.project.graph.Edge;
import com.project.graph.Node;
import com.project.graph.Road;
import com.project.graph.SingleRoad;
import com.project.cellular.CellTypes;
import com.project.cellular.Grid;
import com.project.cellular.GridCell;
import com.project.cellular.Map;


public class TrafficPanel extends JPanel{
	
	private static final long serialVersionUID = -8086343848521884074L;
	 static boolean temp;
	//private static final CellTypes LEFT_RED_LIGHT = null;
	private Controller controller;
	private ArrayList<Node> doubleEdges;
	private int frameCounter = 0;
	private TimerTask mapRunner;
	 
	
	/**
	 * Max road location 	x-axis: 1200
	 * 						y-axis: 750
	 * @param controller
	 */
	
	public TrafficPanel(Controller controller){
		this.controller = controller;
		mapRunner = new TimerTask() {
			public void run(){
				controller.getCurrentMap().tick();
				repaint();
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(mapRunner, 0, 100);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawMap(controller.getCurrentMap(), g2);
		frameCounter += 1;
		if(frameCounter % 10 == 0){
			frameCounter = 0;
			controller.getCurrentMap().tick();
			this.repaint();
			System.out.println("Tick.");
		}
	}
	
	private void drawMap(Map map, Graphics2D g2){
		Grid grid = map.getGrid();
		int xPadding = 10;
		int yPadding = 10;
		int cellSize;
		if(getWidth() < getHeight()){
			int availableSpace = getWidth() - xPadding*2;
			cellSize = (int)(availableSpace/grid.getWidth());
		}else{
			int availableSpace = getHeight() - yPadding*2;
			cellSize = (int)(availableSpace/grid.getHeight());
		}
		
		double carProportion = 0.8;
		int carPadding = (int)((1-carProportion)*0.5*cellSize);
		
		
		for(int x=0; x<grid.getWidth(); x++){
			for(int y=0; y<grid.getHeight(); y++){
				GridCell cell = grid.getCellAt(x, y);
				if(cell == null) continue;
				
				int drawX = xPadding + x*cellSize;
				int drawY = yPadding + y*cellSize;
				//GRID
				g2.setColor(new Color(50));
				g2.fillRect(drawX, drawY, cellSize, cellSize);
		
				if(cell.getState()){
					//CAR COLORS
					g2.setColor(new Color(200, 200, 200));
					//g2.fillRect(drawX + carPadding, drawY + carPadding, (int)(carProportion*cellSize), (int)(carProportion*cellSize));
					g2.fillRect(drawX, drawY, cellSize, cellSize);
				
				}
			
				if((cell.isOfType(CellTypes.DOWN_RED_LIGHT)==true || cell.isOfType(CellTypes.UP_RED_LIGHT)==true || cell.isOfType(CellTypes.RIGHT_RED_LIGHT)==true || cell.isOfType(CellTypes.LEFT_RED_LIGHT)==true ) && controller.shouldShowLight()){
					g2.setColor(new Color(200, 50, 50));
					//g2.fillRect(drawX + carPadding, drawY + carPadding, (int)(carProportion*cellSize), (int)(carProportion*cellSize));
					g2.fillRect(drawX, drawY, cellSize, cellSize);
				}
		
				if(cell.getState()){
					//CAR COLORS
					//g2.setColor(new Color(0, 0, 0));g2.fillRect((drawX + carPadding), (drawY + carPadding), (int)(carProportion*cellSize), (int)(carProportion*cellSize));
					g2.setColor(new Color(200, 200, 200));
					//g2.fillRect(drawX + carPadding, drawY + carPadding, (int)(carProportion*cellSize), (int)(carProportion*cellSize));
					g2.fillOval(drawX + carPadding, drawY + carPadding, (int)(carProportion*cellSize), (int)(carProportion*cellSize));
				
				}
				
			//	}
			}
		}
	}
}
