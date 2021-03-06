package com.project.cellular;

import java.util.ArrayList;
import java.util.Random;

public class Map {
	private Grid grid;
	private ArrayList<MultiCellController> controllers = new ArrayList<MultiCellController>();
	// creates multicell controllers for traffic light
	public Map(Grid grid){
		this.grid = grid;
		initControllers();
	}
	//goes through  the all cells in the grid
	// we get references of the cells
	
	private void initControllers(){
		for(int x=0; x<grid.getWidth(); x++){
			for(int y=0; y<grid.getHeight(); y++){
				GridCell center = grid.getCellAt(x, y);
				GridCell left = grid.getCellAt(x-1, y);
				GridCell right = grid.getCellAt(x+1, y);
				GridCell up = grid.getCellAt(x, y-1);
				GridCell down = grid.getCellAt(x, y+1);
				boolean hasHorNeighbors = (left != null || right !=  null);
				boolean hasVerNeighbors = (up != null || down != null);
				//cross-road check
				
				if(!(hasHorNeighbors && hasVerNeighbors)){
					continue;
				}
				MultiCellController contr = new MultiCellController(center, left, right, up, down);
				if(contr.initialize()){
					controllers.add(contr);
				}
			}
		}
	}
	
	//add cars to the grid
	//works by adding cars by probability
	public void initCars(CarAdder adder){
		adder.addCars(grid);
	}
	
	public void tick(){
		grid.tick();
		for(MultiCellController contr: controllers){
			contr.tick();
		}
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	public String toString(){
		return grid.toString();
	}
	
	public void setGlobalTrafficLightSwitchInterval(int ticks){
		for(MultiCellController contr: controllers){
			contr.setTrafficLightSwitchInterval(ticks);
		}
	}
	
	public static void main(String[] args){
		Grid grid = new Grid(7, 7);
		
		for(int i=0; i<7; i++){
			//border
			grid.addCell(0, i, CellTypes.UP_ROAD);
			grid.addCell(i, 0, CellTypes.RIGHT_ROAD);
			grid.addCell(6, i, CellTypes.DOWN_ROAD);
			grid.addCell(i, 6, CellTypes.LEFT_ROAD);
			
			//cross
			grid.addCell(i, 3, CellTypes.RIGHT_ROAD);
			grid.addCell(3, i, CellTypes.DOWN_ROAD);
		}
		grid.addCell(0, 0, CellTypes.RIGHT_ROAD);
		grid.addCell(3, 0, CellTypes.RIGHT_ROAD);
		grid.addCell(6, 0, CellTypes.DOWN_ROAD);
		grid.addCell(6, 3, CellTypes.DOWN_ROAD);
		grid.addCell(6, 6, CellTypes.LEFT_ROAD);
		grid.addCell(3, 6, CellTypes.LEFT_ROAD);
		grid.addCell(0, 6, CellTypes.UP_ROAD);
		grid.addCell(0, 3, CellTypes.UP_ROAD);
		
		grid.getCellAt(1, 6).setState(true);
		
		Map map = new Map(grid);
		System.out.println(grid.toDirString());
		
		for(int i=0; i<20; i++){
			System.out.println(map);
			map.tick();
		}
	}
}
