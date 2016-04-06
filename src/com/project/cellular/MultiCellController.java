package com.project.cellular;

import java.util.ArrayList;
import java.util.Random;
/**
 * controller for the cross-roads
 *
 */
public class MultiCellController {
	private GridCell center;
	private GridCell left;
	private GridCell right;
	private GridCell up;
	private GridCell down;
	private ArrayList<GridCell> incoming = new ArrayList<GridCell>(3);
	private ArrayList<GridCell> outgoing = new ArrayList<GridCell>(3);
	
	private int tickCounter = 0;
	
	private Random random = new Random();
	
	private int trafficLightSwitchInterval = 10;
	public int trafficLightGreenIndex = 0;
	
	//constructor: 
	public MultiCellController(GridCell center, GridCell left, GridCell right, GridCell up, GridCell down){
		this.center = center;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		if(center != null) this.setConnections();
	}
	
	public void setTrafficLightSwitchInterval(int ticks){
		trafficLightSwitchInterval = ticks;
	}
	//checks for all the directions if there is a road there,
	//
	private void setConnections(){
		if (left != null) {
			if(left.isOfType(CellTypes.RIGHT_ROAD)){
				incoming.add(left);
			} else {
				outgoing.add(left);
			}
		}
		if (right != null){
			if (right.isOfType(CellTypes.LEFT_ROAD)){
				incoming.add(right);
			} else {
				outgoing.add(right);
			}
		}
		if (up != null) {
			if (up.isOfType(CellTypes.DOWN_ROAD)) {
				incoming.add(up);
			} else {
				outgoing.add(up);
			}
		}
		if (down != null) {
			if (down.isOfType(CellTypes.UP_ROAD)) {
				incoming.add(down);
			} else {
				outgoing.add(down);
			}
		}
		
		if(incoming.size() == 0){
			System.out.println("No incoming streets!");
		}
		if(outgoing.size() == 0){
			System.out.println("No outgoing streets");
		}
	}
	
	// Initialize this intersection.
	// This is done for every cell on the grid.
	// This method returns true if a controller is necessary.
	public boolean initialize() {
		if(this.center == null){
			return false;
		}
		
		if(this.incoming.size() == 1){
			this.acceptFrom(this.incoming.get(0));
			if(this.outgoing.size() == 1){
				return false;
			}
		}
		
		//Mark our cells as being controlled, cars shouldn't be spawned here.
		
		this.center.setControlled(true);
		if (this.left != null) this.left.setControlled(true);
		if (this.right != null) this.right.setControlled(true);
		if (this.up != null) this.up.setControlled(true);
		if (this.down != null) this.down.setControlled(true);
		return true;
	}
	
	public void acceptFrom(GridCell from){
		if(from == left){
			center.setCellType(CellTypes.RIGHT_ROAD);
		}
		if(from == right){
			center.setCellType(CellTypes.LEFT_ROAD);
		}
		if(from == up){
			center.setCellType(CellTypes.DOWN_ROAD);
		}
		if(from == down){
			center.setCellType(CellTypes.UP_ROAD);
		}
	}
	//decides which direction has a green light incoming
	//the direction on the cross-road
	
	public void tick(){
		setOutgoingDirection();
		setGreenLight();
		tickCounter += 1;
	}
	//choose the random direction to move on the cross-road 
	private void setOutgoingDirection(){
		GridCell out = outgoing.get(random.nextInt(outgoing.size()));
		for (GridCell cell: outgoing){
			if(cell == left){
				cell.setCellType(cell == out ? CellTypes.LEFT_ROAD : CellTypes.LEFT_TRAFFIC_STOPPER);
			}
			if(cell == right){
				cell.setCellType(cell == out ? CellTypes.RIGHT_ROAD : CellTypes.RIGHT_TRAFFIC_STOPPER);
			}
			if(cell == up){
				cell.setCellType(cell == out ? CellTypes.UP_ROAD : CellTypes.UP_TRAFFIC_STOPPER);
			}
			if(cell == down){
				cell.setCellType(cell == out ? CellTypes.DOWN_ROAD : CellTypes.DOWN_TRAFFIC_STOPPER);
			}
		}
	}
	// choose which incoming road has green light
	//create new class for each strategy 
	private void setGreenLight(){
		if(tickCounter >= trafficLightSwitchInterval){
			trafficLightGreenIndex = (trafficLightGreenIndex + 1) % incoming.size();
			tickCounter = 0;
		}
		GridCell green = incoming.get(trafficLightGreenIndex);
		acceptFrom(green);
		for(GridCell cell: incoming){
			if(cell == left){
				cell.setCellType(cell == green ? CellTypes.RIGHT_ROAD : CellTypes.RIGHT_RED_LIGHT);
			}
			if(cell == right){
				cell.setCellType(cell == green ? CellTypes.LEFT_ROAD : CellTypes.LEFT_RED_LIGHT);
			}
			if(cell == up){
				cell.setCellType(cell == green ? CellTypes.DOWN_ROAD : CellTypes.DOWN_RED_LIGHT);
			}
			if(cell == down){
				cell.setCellType(cell == green ? CellTypes.UP_ROAD : CellTypes.UP_RED_LIGHT);
			}
		}
	}
	
}
