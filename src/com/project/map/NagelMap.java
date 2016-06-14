package com.project.map;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import com.project.model.Car;
import com.project.model.CarLine;
import com.project.model.CarSourceSink;
import com.project.model.CurvedRoad;
import com.project.model.EllipseRoad;
import com.project.model.Globals;
import com.project.model.Intersection;
import com.project.model.RandomTrajectory;
import com.project.model.Lane;
import com.project.model.Road;
import com.project.model.RoadConnection;
import com.project.stats.Record;
import com.project.stats.RecordSet;

public class NagelMap {
	public final ArrayList<Road> roads = new ArrayList<Road>();
	public final ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	public final ArrayList<Car> cars = new ArrayList<Car>();
	private int tickCount = 0;
	private RecordSet stats = new RecordSet();
	
	public void addRoad(Road road){
		if(roads.contains(road)){
			return;
		}
		roads.add(road);
	}
	
	public void addIntersection(Intersection inter){
		if(intersections.contains(inter)){
			return;
		}
		intersections.add(inter);
	}
	
	public void resetStats(){
		stats = new RecordSet();
	}
	
	public RecordSet getStats(){
		return stats;
	}
			
	public void generate(){
		//We want to generate a random map here later, but for now, I'm just setting up a test map.
		double offset = 35;
		double size = 600;
		double c = size / 2;

		Intersection center = new Intersection(new Point2D.Double(offset + c, offset + c));
		Intersection top = new Intersection(new Point2D.Double(offset + c, offset));
		Intersection left = new Intersection(new Point2D.Double(offset, offset + c));
		Intersection bottom = new Intersection(new Point2D.Double(offset + c, offset + size));
		Intersection right = new Intersection(new Point2D.Double(offset + size, offset + c));
		Intersection sourceSink = new CarSourceSink(new Point2D.Double(offset + size + c, offset + c), this);
		
		Road road1 = new Road(top, center, Intersection.SOUTH, Intersection.NORTH);
		Road road2 = new Road(center, top, Intersection.NORTH, Intersection.SOUTH);
		Road road3 = new Road(center, bottom, Intersection.SOUTH, Intersection.NORTH);
		Road road4 = new Road(bottom, center, Intersection.NORTH, Intersection.SOUTH);
		Road road5 = new Road(left, center, Intersection.EAST, Intersection.WEST);
		Road road6 = new Road(center, left, Intersection.WEST, Intersection.EAST);
		Road road7 = new Road(center, right, Intersection.EAST, Intersection.WEST);
		Road road8 = new Road(right, center, Intersection.WEST, Intersection.EAST);
		
		//This offset is the offset for the control point of the bezier curves of the inner roads of the ring.
		//If we don't apply this offset, the two roads that make up each part of the ring will overlap.
		//We want to push the control point inwards by 2 lane widths. The amount we need to push inwards
		// equal horizontally and vertically. We want to move  2 lane widths, at an angle of 45° (pi/4 radians) to both axis.
		double ctrlOffset = Math.cos(Math.PI/4)*Globals.LANE_WIDTH*2;

		Road ring1 = new CurvedRoad(top, right, Intersection.EAST, Intersection.NORTH, new Point2D.Double(offset + size - ctrlOffset, offset + ctrlOffset));
		Road ring2 = new CurvedRoad(right, top, Intersection.NORTH, Intersection.EAST, new Point2D.Double(offset + size, offset));
		Road ring3 = new CurvedRoad(right, bottom, Intersection.SOUTH, Intersection.EAST, new Point2D.Double(offset + size - ctrlOffset, offset + size - ctrlOffset));
		Road ring4 = new CurvedRoad(bottom, right, Intersection.EAST, Intersection.SOUTH, new Point2D.Double(offset + size , offset + size));
		Road ring5 = new CurvedRoad(bottom, left, Intersection.WEST, Intersection.SOUTH, new Point2D.Double(offset + ctrlOffset, offset + size - ctrlOffset));
		Road ring6 = new CurvedRoad(left, bottom, Intersection.SOUTH, Intersection.WEST, new Point2D.Double(offset, offset + size));
		Road ring7 = new CurvedRoad(left, top, Intersection.NORTH, Intersection.WEST, new Point2D.Double(offset + ctrlOffset, offset + ctrlOffset));
		Road ring8 = new CurvedRoad(top, left, Intersection.WEST, Intersection.NORTH, new Point2D.Double(offset, offset));

		Intersection mergePoint = new RoadConnection(ring2, ring2.getLength()/6); //quite an arbitrary connection point :)
		
		Road road9 =  new Road(sourceSink, mergePoint, Intersection.WEST, Intersection.EAST);
		Road road10 = new Road(right, sourceSink, Intersection.EAST, Intersection.WEST);
		
		addRoad(road1);
		addRoad(road2);
		addRoad(road3);
		addRoad(road4);
		addRoad(road5);
		addRoad(road6);
		addRoad(road7);
		addRoad(road8);
		addRoad(road9);
		addRoad(road10);

		addRoad(ring1);
		addRoad(ring2);
		addRoad(ring3);
		addRoad(ring4);
		addRoad(ring5);
		addRoad(ring6);
		addRoad(ring7);
		addRoad(ring8);

		addIntersection(center);
		addIntersection(top);
		addIntersection(right);
		addIntersection(bottom);
		addIntersection(left);
		addIntersection(sourceSink);
		
		//addRandomCars(100);
	}
	
	public void generateCircle(double x, double y, double w, double h, int nCars){
		addRoad(new EllipseRoad(x, y, w, h));
		addRandomCars(nCars);
	}
	
	public void addRandomCars(int n){
		Random rand = new Random();
		for(int i=0;i<n;i++){
			Road r = roads.get(rand.nextInt(roads.size()));
			int pos = rand.nextInt(r.getLength());
			Lane lane = (rand.nextBoolean()) ? r.leftLane : r.rightLane;
			addCar(makeCar(), lane, pos);
		}
	}
	
	public Car makeCar(){
		return new Car(new RandomTrajectory());
	}
	
	public void addCar(Car car, Lane lane, int pos){
		if(cars.contains(car)){
			return;
		}
		lane.addCar(car, pos);
		cars.add(car);
	}
	
	public void tick(){
		// The reason these are 2 separate methods, is because all calculations need to be done on the current state.
		// Only after the calculations, do we want to update the state.
		calcUpdate();
		update();
		handleTransitions();
		collectStats();
		tickCount += 1;
	}
	
	private void calcUpdate(){
		for(Road road: roads){
			road.calcUpdate();
		}
		for(Intersection inter: intersections){
			inter.calcUpdate();
		}
	}
	
	private void update(){
		for(Road road: roads){
			road.update();
		}
		for(Intersection inter: intersections){
			inter.update();
		}
		cars.removeIf(new Predicate<Car>(){
			public boolean test(Car car) {
				if(car.trajectory.currentLane == null){
					System.out.println("This car should be deleted!");
				}
				return car.trajectory.currentLane == null;
			}
			
		});
	}
	
	private void handleTransitions(){
		for(Road road: roads){
			road.handleTransitions();
		}
		for(Intersection inter: intersections){
			inter.handleTransitions();
		}
	}
	
	private void collectStats(){
		for(Car car:cars){
			Record rec = getBaseRecord();
			car.collectStats(rec);
			stats.addRecord(rec);
		}
	}
	
	private Record getBaseRecord(){
		Record rec = new Record();
		rec.setValue("tick", tickCount);
		rec.setValue("nCars", cars.size());;
		rec.setValue("nCells", getTotalCells());
		return rec;
	}
	
	public ArrayList<CarLine> getCarLines(){
		ArrayList<CarLine> carLines = new ArrayList<CarLine>();
		for(Road road: roads){
			carLines.addAll(road.getCarLines());
		}
		
		for(Intersection inter: intersections){
			carLines.addAll(inter.getCarLines());
		}
		
		return carLines;
	}
	
	public int getTotalCells(){
		int cells = 0;
		for(Road road: roads){
			cells += road.getLength() * 2; // 2 lanes
		}
		
		for(Intersection inter: intersections){
			for(Road road: inter.getInsideRoads()){
				cells += road.getLength(); //We only use the left lane here of these.
			}
		}
		return cells;
	}
	
}
