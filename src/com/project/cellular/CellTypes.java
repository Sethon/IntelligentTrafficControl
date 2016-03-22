package com.project.cellular;

public enum CellTypes {
	//straight roads
	LEFT_ROAD (226, true),  
	RIGHT_ROAD (184, true),
	UP_ROAD (226, false),
	DOWN_ROAD (184, false),
		
	//red light cells stop cars from entering a crossroad;
	LEFT_RED_LIGHT (238, true),
	RIGHT_RED_LIGHT (252, true),
	UP_RED_LIGHT (238, false),
	DOWN_RED_LIGHT (252, false),
	
	//traffic stoppers
	LEFT_TRAFFIC_STOPPER (192, true),
	RIGHT_TRAFFIC_STOPPER (136, true),
	UP_TRAFFIC_STOPPER (192, false),
	DOWN_TRAFFIC_STOPPER (136, false),
	
	public final int pattern;
	public final boolean horizontal;
	
	private CellTypes(int pattern, boolean horizontal){
		this.pattern = pattern;
		this.horizontal = horizontal;
	}
}
