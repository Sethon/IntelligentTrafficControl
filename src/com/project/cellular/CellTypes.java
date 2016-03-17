package com.project.cellular;

public enum CellTypes {
	//straight roads
	LEFT_ROAD (226, 204),  
	RIGHT_ROAD (184, 204),
	UP_ROAD (204, 226),
	DOWN_ROAD (204, 184),
	
	CROSSROAD (184, 184),
	
	//red light cells stop cars from entering a crossroad;
	LEFT_RED_LIGHT (238, 204),
	RIGHT_RED_LIGHT (252, 204),
	UP_RED_LID (204, 238),
	DOWN_RED_LIGHT (204, 252),
	
	//traffic stoppers
	LEFT_TRAFFIC_STOPPER (192, 204),
	RIGHT_TRAFFIC_STOPPER (136, 204),
	UP_TRAFFIC_STOPPER (204, 192),
	DOWN_TRAFFIC_STOPPER (204, 136)
	
	//turns
	// writen in the form TURN_HORIZONTAL_VERTICAL
	TURN_LEFT_UP (226, 226),
	TURN_LEFT_DOWN (226, 184),
	TURN_RIGHT_UP (184, 226),
	TURN_RIGHT_DOWN (184, 184);
	
	public final int horizontalPattern;
	public final int verticalPattern;
	
	private CellTypes(int horizontalPattern, int verticalPattern){
		this.horizontalPattern = horizontalPattern;
		this.verticalPattern = verticalPattern;
	}
}
