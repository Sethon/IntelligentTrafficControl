package com.project.cellular;

public enum CellType {
	//straight roads
	LEFT_ROAD (226, 204),  
	RIGHT_ROAD (184, 204),
	UP_ROAD (204, 226),
	DOWN_ROAD (204, 184);
	
	public final int horizontalPattern;
	public final int verticalPattern;
	
	private CellType(int horizontalPattern, int verticalPattern){
		this.horizontalPattern = horizontalPattern;
		this.verticalPattern = verticalPattern;
	}
}
