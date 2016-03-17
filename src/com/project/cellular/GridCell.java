package com.project.cellular;

public class GridCell {
	private int horizontalPattern;
	private int verticalPattern;
	private boolean state;
	
	public GridCell(int horizontalPattern, int verticalPattern, boolean state){
		this.horizontalPattern = horizontalPattern;
		this.verticalPattern = verticalPattern;
		this.state = state;
	}
	
	public GridCell(CellTypes type, boolean state) {
		this(type.horizontalPattern, type.verticalPattern, state);
	}
	
	public boolean getState(){
		return state;
	}
	
	public void setState(boolean state){
		this.state = state;
	}
	
	public void setHorizontalPattern(int pattern){
		this.horizontalPattern = pattern;
	}
	
	public void setVerticalPattern(int pattern) {
		this.verticalPattern = pattern;
	}
	
	public void setCellType(CellTypes type){
		this.horizontalPattern = type.horizontalPattern;
		this.verticalPattern = type.verticalPattern;
	}
	
	/*
	 * Updates the state of a cell based on the state of its horizontal or vertical neighbors
	 */
	public void updateState(boolean leftState, boolean rightState, boolean horizontal){
		int conditionNumber = getConditionNumber(leftState, this.state, rightState);
		int pattern = horizontal ? this.horizontalPattern : this.verticalPattern;
		int conditionNumberMask = (int)Math.pow(2, conditionNumber);
		this.state = ((pattern & conditionNumberMask) == conditionNumberMask);
	}
	
	/* The next state of a cell depends on itself and its 2 neighbours.
	 * This means there are 8 possible conditions. Each of these is assigned a number.
	 * The next state of a cell depends on the bit corresponding to the corresponding condition number.
	 */
	private static int getConditionNumber(boolean left, boolean self, boolean right) {
		int leftBit = left ? 1 : 0;
		int selfBit = self ? 1 : 0;
		int rightBit = self ? 1 : 0;
		return leftBit * 4 + selfBit * 2 + rightBit;
	}
}
