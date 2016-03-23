package com.project.cellular;

public class GridCell {
	private int pattern;
	private boolean horizontal;
	private boolean state;
	private boolean isControlled = false;
	
	public GridCell(int pattern, boolean horizontal, boolean state){
		this.pattern = pattern;
		this.horizontal = horizontal;
		this.state = state;
	}
	
	public GridCell(CellTypes type, boolean state) {
		this(type.pattern, type.horizontal, state);
	}
	
	public boolean getState(){
		return state;
	}
	
	public void setState(boolean state){
		this.state = state;
	}
	
	public void setPattern(int pattern){
		this.pattern = pattern;
	}
	
	public void setHorizontal(boolean horizontal){
		this.horizontal = horizontal;
	}
	
	public void setCellType(CellTypes type){
		this.setPattern(type.pattern);
		this.setHorizontal(type.horizontal);
	}
	
	public int getPattern(){
		return pattern;
	}
	
	public boolean getHorizontal(){
		return horizontal;
	}
	
	/*
	 * Updates the state of a cell based on the state of its horizontal or vertical neighbors
	 */
	public void updateState(boolean leftState, boolean rightState){
		int conditionNumber = getConditionNumber(leftState, this.state, rightState);
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
	
	public boolean isOfType(CellTypes type) {
		return this.pattern == type.pattern && this.horizontal == type.horizontal;
	}
	
	public void setControlled(boolean controlled){
		this.isControlled = controlled;
	}
	
	public boolean getControlled(){
		return isControlled;
	}
}
