package com.project.cellular;

import java.util.Random;

public class RandomCarAdder implements CarAdder {
	private int chance;
	private Random rand = new Random();
	
	public RandomCarAdder(int chance){
		this.chance = chance;
	}

	@Override
	public void addCars(Grid grid) {
		for(int x=0; x<grid.getWidth(); x++){
			for(int y=0; y<grid.getHeight(); y++){
				GridCell cell = grid.getCellAt(x, y);
				if(cell != null && !cell.getControlled()){
					cell.setState(rand.nextInt(chance) == 0);
				}
			}
		}
	}

}
