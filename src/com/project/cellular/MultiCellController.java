package com.project.cellular;

public class MultiCellController {
	private GridCell center;
	private GridCell left;
	private GridCell right;
	private GridCell up;
	private GridCell down;
	
	public MultiCellController(GridCell center, GridCell left, GridCell right, GridCell up, GridCell down,
			Grid grid, int centerX, int centerY){
		this.center = center;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}
}
