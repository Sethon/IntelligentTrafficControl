package com.project.map;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import com.project.model.Car;
import com.project.model.CarLine;
import com.project.model.CurvedRoad;
import com.project.model.Globals;
import com.project.model.Intersection;
import com.project.model.RandomTrajectory;
import com.project.model.Road;

public class NagelMap {
	public final ArrayList<Road> roads = new ArrayList<Road>();
	public final ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	private int tickCount = 0;
	
	public void addRoad(Road road){
		roads.add(road);
	}
	
	public void addIntersection(Intersection inter){
		intersections.add(inter);
	}
		
	public void generate(){
		//We want to generate a random map here later, but for now, I'm just setting up a test map.
		double offset = 35;
		double size = 600;
		double c = size / 2;
		/*
				Intersection center = new Intersection(new Point2D.Double(offset + c, offset + c));
				Intersection top = new Intersection(new Point2D.Double(offset + c, offset));
				Intersection left = new Intersection(new Point2D.Double(offset, offset + c));
				Intersection bottom = new Intersection(new Point2D.Double(offset + c, offset + size));
				Intersection right = new Intersection(new Point2D.Double(offset + size, offset + c));

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

				addRoad(road1);
				addRoad(road2);
				addRoad(road3);
				addRoad(road4);
				addRoad(road5);
				addRoad(road6);
				addRoad(road7);
				addRoad(road8);

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
		 */

		int letter_w =  160;

		//H
		Intersection ih1 = new Intersection(new Point2D.Double(offset, offset));
		Intersection ih2 = new Intersection(new Point2D.Double(offset + letter_w, offset));
		Intersection ih3 = new Intersection(new Point2D.Double(offset, offset + size/4));
		Intersection ih4 = new Intersection(new Point2D.Double(offset + letter_w, offset + size/4));
		Intersection ih5 = new Intersection(new Point2D.Double(offset, offset + size/2));
		Intersection ih6 = new Intersection(new Point2D.Double(offset + letter_w, offset + size/2));

		//horizontal line
		Road rh34 = new Road(ih3, ih4, Intersection.EAST, Intersection.WEST);
		Road rh43 = new Road(ih4, ih3, Intersection.WEST, Intersection.EAST);
		//left line
		Road rh13 = new Road(ih1, ih3, Intersection.SOUTH, Intersection.NORTH);
		Road rh31 = new Road(ih3, ih1, Intersection.NORTH, Intersection.SOUTH);
		Road rh35 = new Road(ih3, ih5, Intersection.SOUTH, Intersection.NORTH);
		Road rh53 = new Road(ih5, ih3, Intersection.NORTH, Intersection.SOUTH);
		//right line
		Road rh24 = new Road(ih2, ih4, Intersection.SOUTH, Intersection.NORTH);
		Road rh42 = new Road(ih4, ih2, Intersection.NORTH, Intersection.SOUTH);
		Road rh46 = new Road(ih4, ih6, Intersection.SOUTH, Intersection.NORTH);
		Road rh64 = new Road(ih6, ih4, Intersection.NORTH, Intersection.SOUTH);

		addIntersection(ih1);
		addIntersection(ih2);
		addIntersection(ih3);
		addIntersection(ih4);
		addIntersection(ih5);
		addIntersection(ih6);

		addRoad(rh34);
		addRoad(rh43);
		addRoad(rh13);
		addRoad(rh31);
		addRoad(rh35);
		addRoad(rh53);
		addRoad(rh24);
		addRoad(rh42);
		addRoad(rh46);
		addRoad(rh64);

		//A
		Intersection ia1 = new Intersection(new Point2D.Double(offset + letter_w*1.5, offset));
		Intersection ia2 = new Intersection(new Point2D.Double(offset + letter_w*1.25, offset + size/4));
		Intersection ia3 = new Intersection(new Point2D.Double(offset + letter_w*1.75, offset + size/4));
		Intersection ia4 = ih6;
		Intersection ia5 = new Intersection(new Point2D.Double(offset + letter_w*2, offset + size / 2));

		Road ra12 = new Road(ia1, ia2, Intersection.WEST, Intersection.NORTH);
		Road ra21 = new Road(ia2, ia1, Intersection.NORTH, Intersection.WEST);
		Road ra13 = new Road(ia1, ia3, Intersection.EAST, Intersection.NORTH);
		Road ra31 = new Road(ia3, ia1, Intersection.NORTH, Intersection.EAST);
		Road ra23 = new Road(ia2, ia3, Intersection.EAST, Intersection.WEST);
		Road ra32 = new Road(ia3, ia2, Intersection.WEST, Intersection.EAST);
		Road ra24 = new Road(ia2, ia4, Intersection.SOUTH, Intersection.EAST);
		Road ra42 = new Road(ia4, ia2, Intersection.EAST, Intersection.SOUTH);
		Road ra35 = new Road(ia3, ia5, Intersection.SOUTH, Intersection.WEST);
		Road ra53 = new Road(ia5, ia3, Intersection.WEST, Intersection.SOUTH);

		addIntersection(ia1);
		addIntersection(ia2);
		addIntersection(ia3);
		addIntersection(ia4);
		addIntersection(ia5);
		addRoad(ra12);
		addRoad(ra21);
		addRoad(ra13);
		addRoad(ra31);
		addRoad(ra23);
		addRoad(ra32);
		addRoad(ra24);
		addRoad(ra42);
		addRoad(ra35);
		addRoad(ra53);

		//P1
		Intersection ip11 = new Intersection(new Point2D.Double(offset + letter_w*2, offset));
		Intersection ip12 = new Intersection(new Point2D.Double(offset + letter_w*2, offset + size/4));
		Intersection ip13 = ia5;

		Road rp112 = new Road(ip11, ip12, Intersection.SOUTH, Intersection.NORTH);
		Road rp121 = new Road(ip12, ip11, Intersection.NORTH, Intersection.SOUTH);
		Road rp123 = new Road(ip12, ip13, Intersection.SOUTH, Intersection.NORTH);
		Road rp132 = new Road(ip13, ip12, Intersection.NORTH, Intersection.SOUTH);
		Road rp112b = new CurvedRoad(ip11, ip12, Intersection.EAST, Intersection.EAST, new Point2D.Double(offset + letter_w*3, offset + size/8));
		Road rp121b = new CurvedRoad(ip12, ip11, Intersection.EAST, Intersection.EAST, new Point2D.Double(offset + letter_w*3 + Globals.LANE_WIDTH*2, offset + size / 8));

		addIntersection(ip11);
		addIntersection(ip12);
		addIntersection(ip13);

		addRoad(rp112);
		addRoad(rp121);
		addRoad(rp123);
		addRoad(rp132);
		addRoad(rp112b);
		addRoad(rp121b);

		//P2
		Intersection ip21 = new Intersection(new Point2D.Double(offset + letter_w*3, offset));
		Intersection ip22 = new Intersection(new Point2D.Double(offset + letter_w*3, offset + size/4));
		Intersection ip23 = new Intersection(new Point2D.Double(offset + letter_w*3, offset + size/2));

		Road rp212 = new Road(ip21, ip22, Intersection.SOUTH, Intersection.NORTH);
		Road rp221 = new Road(ip22, ip21, Intersection.NORTH, Intersection.SOUTH);
		Road rp223 = new Road(ip22, ip23, Intersection.SOUTH, Intersection.NORTH);
		Road rp232 = new Road(ip23, ip22, Intersection.NORTH, Intersection.SOUTH);
		Road rp212b = new CurvedRoad(ip21, ip22, Intersection.EAST, Intersection.EAST, new Point2D.Double(offset + letter_w*4, offset + size/8));
		Road rp221b = new CurvedRoad(ip22, ip21, Intersection.EAST, Intersection.EAST, new Point2D.Double(offset + letter_w*4 + Globals.LANE_WIDTH*2, offset + size / 8));
		Road rp2p1 = new Road(ip23, ip13, Intersection.WEST, Intersection.EAST);
		Road rp1p2 = new Road(ip13, ip23, Intersection.EAST, Intersection.WEST);

		addIntersection(ip21);
		addIntersection(ip22);
		addIntersection(ip23);

		addRoad(rp212);
		addRoad(rp221);
		addRoad(rp223);
		addRoad(rp232);
		addRoad(rp212b);
		addRoad(rp221b);
		addRoad(rp2p1);
		addRoad(rp1p2);

		//Y

		Intersection iy1 = new Intersection(new Point2D.Double(offset + letter_w*4, offset));
		Intersection iy2 = new Intersection(new Point2D.Double(offset +letter_w*5, offset));
		Intersection iy3 = new Intersection(new Point2D.Double(offset + letter_w*4.5, offset + size/4));
		Intersection iy4 = new Intersection(new Point2D.Double(offset + letter_w*4.5, offset + size/2));

		Road ry13 = new Road(iy1, iy3, Intersection.SOUTH, Intersection.WEST);
		Road ry31 = new Road(iy3, iy1, Intersection.WEST, Intersection.SOUTH);
		Road ry23 = new Road(iy2, iy3, Intersection.SOUTH, Intersection.EAST);
		Road ry32 = new Road(iy3, iy2, Intersection.EAST, Intersection.SOUTH);
		Road ry34 = new Road(iy3, iy4, Intersection.SOUTH, Intersection.NORTH);
		Road ry43 = new Road(iy4, iy3, Intersection.NORTH, Intersection.SOUTH);
		Road ryp2 = new Road(iy4, ip23, Intersection.WEST, Intersection.EAST);
		Road rp2y = new Road(ip23, iy4, Intersection.EAST, Intersection.WEST);

		addIntersection(iy1);
		addIntersection(iy2);
		addIntersection(iy3);
		addIntersection(iy4);

		addRoad(ry13);
		addRoad(ry31);
		addRoad(ry23);
		addRoad(ry32);
		addRoad(ry34);
		addRoad(ry43);
		addRoad(ryp2);
		addRoad(rp2y);

		//B
		Intersection ib1 = ih5;
		Intersection ib2 = new Intersection(new Point2D.Double(offset, offset + size*0.75));
		Intersection ib3 = new Intersection(new Point2D.Double(offset, offset + size));

		Road rb12 = new Road(ib1, ib2, Intersection.SOUTH, Intersection.NORTH);
		Road rb21 = new Road(ib2, ib1, Intersection.NORTH, Intersection.SOUTH);
		Road rb12b = new CurvedRoad(ib1, ib2, Intersection.EAST, Intersection.EAST, new Point2D.Double(offset+letter_w, offset + size*0.625));
		Road rb23 = new Road(ib2, ib3, Intersection.SOUTH, Intersection.NORTH);
		Road rb32 = new Road(ib3, ib2, Intersection.NORTH, Intersection.SOUTH);
		Road rb23b = new CurvedRoad(ib2, ib3, Intersection.EAST, Intersection.EAST, new Point2D.Double(offset+letter_w, offset + size*0.875));

		addIntersection(ib1);
		addIntersection(ib2);
		addIntersection(ib3);

		addRoad(rb12);
		addRoad(rb21);
		addRoad(rb12b);
		addRoad(rb23);
		addRoad(rb32);
		addRoad(rb23b);

		//D
		Intersection id1 = ia4;
		Intersection id2 = new Intersection(new Point2D.Double(offset + letter_w, offset + size));

		Road rd12 = new Road(id1, id2, Intersection.SOUTH, Intersection.NORTH);
		Road rd21 = new CurvedRoad(id2, id1, Intersection.EAST, Intersection.SOUTH, new Point2D.Double(offset + letter_w*2 + Globals.LANE_WIDTH*2, size*0.75));

		addIntersection(id1);
		addIntersection(id2);
		addRoad(rd12);
		addRoad(rd21);

		//A2
		Intersection ia21 = new Intersection(new Point2D.Double(offset + letter_w*2.5, offset*2 + size/2));
		Intersection ia22 = new Intersection(new Point2D.Double(offset + letter_w*2.25, offset + size*0.75));
		Intersection ia23 = new Intersection(new Point2D.Double(offset + letter_w*2.75, offset + size*0.75));
		Intersection ia24 = new Intersection(new Point2D.Double(offset + letter_w*2, offset + size));
		Intersection ia25 = new Intersection(new Point2D.Double(offset + letter_w*3, offset + size));

		Road ra212 = new Road(ia21, ia22, Intersection.WEST, Intersection.NORTH);
		Road ra221 = new Road(ia22, ia21, Intersection.NORTH, Intersection.WEST);
		Road ra213 = new Road(ia21, ia23, Intersection.EAST, Intersection.NORTH);
		Road ra231 = new Road(ia23, ia21, Intersection.NORTH, Intersection.EAST);
		Road ra223 = new Road(ia22, ia23, Intersection.EAST, Intersection.WEST);
		Road ra232 = new Road(ia23, ia22, Intersection.WEST, Intersection.EAST);
		Road ra224 = new Road(ia22, ia24, Intersection.SOUTH, Intersection.NORTH);
		Road ra242 = new Road(ia24, ia22, Intersection.NORTH, Intersection.SOUTH);
		Road ra235 = new Road(ia23, ia25, Intersection.SOUTH, Intersection.NORTH);
		Road ra253 = new Road(ia25, ia23, Intersection.NORTH, Intersection.SOUTH);
		Road rda2 = new Road(id2, ia24, Intersection.EAST, Intersection.WEST);
		Road ra2d = new Road(ia24, id2, Intersection.WEST, Intersection.EAST);

		addIntersection(ia21);
		addIntersection(ia22);
		addIntersection(ia23);
		addIntersection(ia24);
		addIntersection(ia25);
		addRoad(ra212);
		addRoad(ra221);
		addRoad(ra213);
		addRoad(ra231);
		addRoad(ra223);
		addRoad(ra232);
		addRoad(ra224);
		addRoad(ra242);
		addRoad(ra235);
		addRoad(ra253);
		addRoad(rda2);
		addRoad(ra2d);

		//Y2
		Intersection iy21 = new Intersection(new Point2D.Double(offset + letter_w*3, offset*2 + size/2));
		Intersection iy22 = new Intersection(new Point2D.Double(offset + letter_w*4, offset*2 + size/2));
		Intersection iy23 = new Intersection(new Point2D.Double(offset + letter_w*3.5, offset*2 + size*0.75));
		Intersection iy24 = new Intersection(new Point2D.Double(offset + letter_w*3.5, offset + size));

		Road ry213 = new Road(iy21, iy23, Intersection.SOUTH, Intersection.WEST);
		Road ry231 = new Road(iy23, iy21, Intersection.WEST, Intersection.SOUTH);
		Road ry223 = new Road(iy22, iy23, Intersection.SOUTH, Intersection.EAST);
		Road ry232 = new Road(iy23, iy22, Intersection.EAST, Intersection.SOUTH);
		Road ry234 = new Road(iy23, iy24, Intersection.SOUTH, Intersection.NORTH);
		Road ry243 = new Road(iy24, iy23, Intersection.NORTH, Intersection.SOUTH);
		Road ra2y2 = new Road(ia25, iy24, Intersection.EAST, Intersection.WEST);
		Road ry2a2 = new Road(iy24, ia25, Intersection.WEST, Intersection.EAST);

		addIntersection(iy21);
		addIntersection(iy22);
		addIntersection(iy23);
		addIntersection(iy24);

		addRoad(ry213);
		addRoad(ry231);
		addRoad(ry223);
		addRoad(ry232);
		addRoad(ry234);
		addRoad(ry243);	
		addRoad(ry2a2);
		addRoad(ra2y2);

		Random rand = new Random();
		for(int i=0;i<100;i++){
			Road r = roads.get(rand.nextInt(roads.size()));
			int pos = rand.nextInt(r.getLength());
			(rand.nextBoolean() ? r.leftLane : r.rightLane).addCar(makeCar(), pos);
		}
	}
	
	private Car makeCar(){
		return new Car(new RandomTrajectory());
	}
	
	public void tick(){
		// The reason these are 2 separate methods, is because all calculations need to be done on the current state.
		// Only after the calculations, do we want to update the state.
		calcUpdate();
		update();
		handleTransitions();
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
	}
	
	private void handleTransitions(){
		for(Road road: roads){
			road.handleTransitions();
		}
		for(Intersection inter: intersections){
			inter.handleTransitions();
		}
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
}
