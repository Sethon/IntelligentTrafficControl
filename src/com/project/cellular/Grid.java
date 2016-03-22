package com.project.cellular;

public class Grid {
	private GridCell[][] cells;
	
	public Grid(GridCell[][] cells) {
		this.cells = cells;
	}
	
	public Grid(int width, int height) {
		this(new GridCell[width][height]);
	}
	
	public GridCell getCellAt(int x, int y){
		return this.cells[x][y];
	}
	
	public int getWidth() {
		return this.cells.length;
	}
	
	public int getHeight() {
		return this.cells[0].length;
	}
	
	public void addCell(int x, int y, CellTypes type) {
		this.cells[x][y] = new GridCell(type, false);
	}
	
	public void updateHorizontal(){
		for(int y=0; y<this.getHeight(); y++){
			for(int x=0; x<this.getWidth(); x++){
				if(this.cells[x][y] != null && this.cells[x][y].getHorizontal()) {
					boolean left = (x > 0 && cells[x-1][y] != null) ? cells[x-1][y].getState() : false;
					boolean right = (x < this.getWidth() -1 && cells[x+1][y] != null) ? this.cells[x+1][y].getState() : false;
					this.cells[x][y].updateState(left, right);
				}
			}
		}
	}
	
	public void updateVertical(){
		for (int x=0; x<this.getWidth(); x++){
			for(int y=0; y<this.getHeight(); y++){
				if(this.cells[x][y] != null && !this.cells[x][y].getHorizontal()){
					boolean above = (y > 0 && cells[x][y-1] != null) ? cells[x][y-1].getState() : false;
					boolean below = (y < this.getHeight() -1 && cells[x][y+1] != null) ? this.cells[x][y+1].getState() : false;
					this.cells[x][y].updateState(above, below);
				}
			}
		}
	}
	
	public void tick(){
		updateHorizontal();
		updateVertical();
	}
}
