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
		if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight()){
			return null;
		}
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
					boolean left = (getCellAt(x-1, y) != null) ? cells[x-1][y].getState() : false;
					boolean right = (getCellAt(x+1, y) != null) ? this.cells[x+1][y].getState() : false;
					this.cells[x][y].updateState(left, right);
				}
			}
		}
	}
	
	public void updateVertical(){
		for (int x=0; x<this.getWidth(); x++){
			for(int y=0; y<this.getHeight(); y++){
				if(this.cells[x][y] != null && !this.cells[x][y].getHorizontal()){
					boolean above = (getCellAt(x, y-1) != null) ? cells[x][y-1].getState() : false;
					boolean below = (getCellAt(x, y+1) != null) ? this.cells[x][y+1].getState() : false;
					this.cells[x][y].updateState(above, below);
				}
			}
		}
	}
	
	public void commitUpdates(){
		for(int y=0; y<getHeight(); y++){
			for(int x=0; x<getWidth(); x++){
				if(this.getCellAt(x, y) != null){
					this.getCellAt(x, y).commitState();
				}
			}
		}
	}
	
	public void tick(){
		updateHorizontal();
		updateVertical();
		commitUpdates();
	}
	
	public String toString(){
		String result = "";
		for(int y=0; y<getHeight(); y++){
			for(int x=0; x<getWidth(); x++){
				if(getCellAt(x, y) == null){
					result += ".";
				}else{
					result += getCellAt(x, y).getState() ? 1:0;
				}
			}
			result += "\n";
		}
		return result;
	}
	
	public String toDirString(){
		String result = "";
		for(int y=0; y<getHeight(); y++){
			for(int x=0; x<getWidth(); x++){
				GridCell cell = getCellAt(x, y);
				if(cell == null){
					result += ".";
				}else{
					if(cell.isOfType(CellTypes.LEFT_ROAD)){
						result += "L";
					}
					if(cell.isOfType(CellTypes.RIGHT_ROAD)){
						result += "R";
					}
					if(cell.isOfType(CellTypes.UP_ROAD)){
						result += "U";
					}
					if(cell.isOfType(CellTypes.DOWN_ROAD)){
						result += "D";
					}
				}
			}
			result += "\n";
		}
		return result;
	}
}
