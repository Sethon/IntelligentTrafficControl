package com.project.cellular;

import java.util.ArrayList;

public class MultiCellController {
	private GridCell center;
	private GridCell left;
	private GridCell right;
	private GridCell up;
	private GridCell down;
	private ArrayList<GridCell> incoming = new ArrayList<GridCell>();
	private ArrayList<GridCell> outgoing = new ArrayList<GridCell>();
	
	public MultiCellController(GridCell center, GridCell left, GridCell right, GridCell up, GridCell down,
			Grid grid, int centerX, int centerY){
		this.center = center;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.setConnections();
	}
	
	private void setConnections(){
		if (left != null) {
			if(left.getHorizontalPattern() == CellTypes.RIGHT_ROAD.horizontalPattern){
				incoming.add(left);
			} else {
				outgoing.add(left);
			}
		}
		if (right != null){
			if (right.getHorizontalPattern() == CellTypes.LEFT_ROAD.horizontalPattern){
				incoming.add(right);
			} else {
				outgoing.add(right);
			}
		}
		if (up != null) {
			if (up.getVerticalPattern() == CellTypes.DOWN_ROAD.verticalPattern) {
				incoming.add(up);
			} else {
				outgoing.add(up);
			}
		}
		if (down != null) {
			if (down.getVerticalPattern() == CellTypes.UP_ROAD.verticalPattern) {
				incoming.add(down);
			} else {
				outgoing.add(down);
			}
		}
	}
	
}
