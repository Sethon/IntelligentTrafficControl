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
	
	public void applyConfiguration(MultiCellConfiguration config){
		if(center != null){
			center.setCellType(config.centerType);
		}
		if(left != null){
			left.setCellType(config.leftType);
		}
		if(right != null){
			right.setCellType(config.rightType);
		}
		if(up != null){
			up.setCellType(config.upType);
		}
		if(down != null){
			down.setCellType(config.downType);
		}
	}
}
