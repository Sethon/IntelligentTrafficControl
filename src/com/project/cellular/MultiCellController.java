package com.project.cellular;

import java.util.ArrayList;
import java.util.Random;

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
	
	public static final int trafficLightSwitchInterval = 10;
	public int trafficLightGreenIndex = 0;
	
	public MultiCellController(GridCell center, GridCell left, GridCell right, GridCell up, GridCell down){
		this.center = center;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.setConnections();
	}
	
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
	}
	
	// Initialize this intersection.
	// This is done for every cell on the grid.
	// This method returns true if a controller is necessary.
	public boolean initialize() {
		if(this.outgoing.size() == 1){
			this.directCenterTo(this.outgoing.get(0));
			if(this.incoming.size() == 1){
				return false;
			}
		}
		//Mark our cells as being controlled, cars shouldn't be spawned here.
		this.center.setControlled(true);
		this.left.setControlled(true);
		this.right.setControlled(true);
		this.up.setControlled(true);
		this.down.setControlled(true);
		return true;
	}
	
	public void directCenterTo(GridCell to){
		if(to == left){
			center.setCellType(CellTypes.LEFT_ROAD);
		}
		if(to == right){
			center.setCellType(CellTypes.RIGHT_ROAD);
		}
		if(to == up){
			center.setCellType(CellTypes.UP_ROAD);
		}
		if(to == down){
			center.setCellType(CellTypes.DOWN_ROAD);
		}
	}
	
	public void tick(){
		setOutgoingDirection();
		setGreenLight();
		tickCounter += 1;
	}
	
	private void setOutgoingDirection(){
		GridCell out = outgoing.get(random.nextInt(outgoing.size()));
		this.directCenterTo(out);
		for (GridCell cell: outgoing){
			if(cell == out){
				break;
			}
			if(cell == left){
				cell.setCellType(CellTypes.LEFT_TRAFFIC_STOPPER);
			}
			if(cell == right){
				cell.setCellType(CellTypes.RIGHT_TRAFFIC_STOPPER);
			}
			if(cell == up){
				cell.setCellType(CellTypes.UP_TRAFFIC_STOPPER);
			}
			if(cell == down){
				cell.setCellType(CellTypes.DOWN_TRAFFIC_STOPPER);
			}
		}
	}
	
	private void setGreenLight(){
		if(tickCounter >= trafficLightSwitchInterval){
			trafficLightGreenIndex = (trafficLightGreenIndex + 1) % incoming.size();
			tickCounter = 0;
		}
		GridCell green = incoming.get(trafficLightGreenIndex);
		for(GridCell cell: incoming){
			if(cell == green){
				break;
			}
			if(cell == left){
				cell.setCellType(CellTypes.RIGHT_RED_LIGHT);
			}
			if(cell == right){
				cell.setCellType(CellTypes.LEFT_RED_LIGHT);
			}
			if(cell == up){
				cell.setCellType(CellTypes.DOWN_RED_LIGHT);
			}
			if(cell == down){
				cell.setCellType(CellTypes.UP_RED_LIGHT);
			}
		}
	}
	
}
