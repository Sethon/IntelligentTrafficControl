package com.project.cellular;

import java.util.ArrayList;
import java.util.Random;

public class Map {
	private Grid grid;
	private ArrayList<MultiCellController> controllers = new ArrayList<MultiCellController>();
	
	public Map(Grid grid, int carProbability){
		this.grid = grid;
		initControllers();
		initCars(carProbability);
	}
	
	private void initControllers(){
		for(int x=0; x<grid.getWidth(); x++){
			for(int y=0; y<grid.getHeight(); y++){
				GridCell center = grid.getCellAt(x, y);
				GridCell left = grid.getCellAt(x-1, y);
				GridCell right = grid.getCellAt(x+1, y);
				GridCell up = grid.getCellAt(x, y-1);
				GridCell down = grid.getCellAt(x, y+1);
				MultiCellController contr = new MultiCellController(center, left, right, up, down);
				if(contr.initialize()){
					controllers.add(contr);
				}
			}
		}
	}
	
	private void initCars(int carProbability){
		Random r = new Random();
		for(int x=0; x<grid.getWidth(); x++){
			for(int y=0; y<grid.getHeight(); y++){
				GridCell cell = grid.getCellAt(x, y);
				if(cell != null && !cell.getControlled()){
					cell.setState(r.nextInt(carProbability) == 0);
				}
			}
		}
	}
	
	private void tick(){
		grid.tick();
		for(MultiCellController contr: controllers){
			contr.tick();
		}
	}
	
	public String toString(){
		return grid.toString();
	}
	
}
