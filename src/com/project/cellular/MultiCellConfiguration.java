package com.project.cellular;

public enum MultiCellConfiguration {
	//crossroads
	//written in the form CROSSROAD_GREENDIRECTION_REDDIRECTION_TURN
	//Where TURN can either be TURN or STRAIGHT depending on what cars should do.
	CROSSROAD_LEFT_UP_STRAIGHT (CellTypes.CROSSROAD,
			CellTypes.LEFT_ROAD, CellTypes.LEFT_ROAD,
			CellTypes.UP_TRAFFIC_STOPPER, CellTypes.UP_RED_LIGHT),
	CROSSROAD_LEFT_UP_TURN (CellTypes.TURN_LEFT_UP,
			CellTypes.LEFT_TRAFFIC_STOPPER, CellTypes.LEFT_ROAD,
			CellTypes.UP_ROAD, CellTypes.UP_RED_LIGHT),
	CROSSROAD_LEFT_DOWN_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.LEFT_ROAD, CellTypes.LEFT_ROAD,
			CellTypes.DOWN_RED_LIGHT, CellTypes.DOWN_TRAFFIC_STOPPER),
	CROSSROAD_LEFT_DOWN_TURN(CellTypes.TURN_LEFT_DOWN,
			CellTypes.LEFT_TRAFFIC_STOPPER, CellTypes.LEFT_ROAD,
			CellTypes.DOWN_RED_LIGHT, CellTypes.DOWN_ROAD),
	
	CROSSROAD_RIGHT_UP_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.RIGHT_ROAD, CellTypes.RIGHT_ROAD,
			CellTypes.UP_TRAFFIC_STOPPER, CellTypes.UP_RED_LIGHT),
	CROSSROAD_RIGHT_UP_TURN(CellTypes.TURN_RIGHT_UP,
			CellTypes.RIGHT_ROAD, CellTypes.RIGHT_TRAFFIC_STOPPER,
			CellTypes.UP_ROAD, CellTypes.UP_RED_LIGHT),
	CROSSROAD_RIGHT_DOWN_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.RIGHT_ROAD, CellTypes.RIGHT_ROAD,
			CellTypes.DOWN_RED_LIGHT, CellTypes.DOWN_TRAFFIC_STOPPER),
	CROSSROAD_RIGHT_DOWN_TURN(CellTypes.TURN_RIGHT_DOWN,
			CellTypes.RIGHT_ROAD, CellTypes.RIGHT_TRAFFIC_STOPPER,
			CellTypes.DOWN_RED_LIGHT, CellTypes.DOWN_ROAD),
	
	CROSSROAD_UP_LEFT_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.LEFT_TRAFFIC_STOPPER, CellTypes.LEFT_RED_LIGHT,
			CellTypes.UP_ROAD, CellTypes.UP_ROAD),
	CROSSROAD_UP_LEFT_TURN(CellTypes.TURN_LEFT_UP,
			CellTypes.LEFT_ROAD, CellTypes.LEFT_RED_LIGHT,
			CellTypes.UP_TRAFFIC_STOPPER, CellTypes.UP_ROAD),
	CROSSROAD_UP_RIGHT_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.RIGHT_RED_LIGHT, CellTypes.RIGHT_ROAD,
			CellTypes.UP_ROAD, CellTypes.UP_ROAD),
	CROSSROAD_UP_RIGHT_TURN(CellTypes.TURN_RIGHT_UP,
			CellTypes.RIGHT_RED_LIGHT, CellTypes.RIGHT_ROAD,
			CellTypes.UP_TRAFFIC_STOPPER, CellTypes.UP_ROAD),
	
	CROSSROAD_DOWN_LEFT_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.LEFT_TRAFFIC_STOPPER, CellTypes.LEFT_RED_LIGHT,
			CellTypes.DOWN_ROAD, CellTypes.DOWN_ROAD),
	CROSSROAD_DOWN_LEFT_TURN(CellTypes.TURN_LEFT_DOWN,
			CellTypes.LEFT_ROAD, CellTypes.LEFT_RED_LIGHT,
			CellTypes.DOWN_ROAD, CellTypes.DOWN_TRAFFIC_STOPPER),
	CROSSROAD_DOWN_RIGHT_STRAIGHT(CellTypes.CROSSROAD,
			CellTypes.RIGHT_RED_LIGHT, CellTypes.RIGHT_TRAFFIC_STOPPER,
			CellTypes.DOWN_ROAD, CellTypes.DOWN_ROAD),
	CROSSROAD_DOWN_RIGHT_TURN(CellTypes.TURN_RIGHT_DOWN,
			CellTypes.RIGHT_RED_LIGHT, CellTypes.RIGHT_ROAD,
			CellTypes.DOWN_ROAD, CellTypes.DOWN_TRAFFIC_STOPPER);
	
	public final CellTypes centerType;
	public final CellTypes leftType;
	public final CellTypes rightType;
	public final CellTypes upType;
	public final CellTypes downType;
	
	private MultiCellConfiguration(CellTypes centerType, CellTypes leftType, CellTypes rightType,
			CellTypes upType, CellTypes downType) {
		this.centerType = centerType;
		this.leftType = leftType;
		this.rightType = rightType;
		this.upType = upType;
		this.downType = downType;
	}
}
