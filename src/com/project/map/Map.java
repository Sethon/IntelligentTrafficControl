package com.project.map;

import java.awt.Point;
import java.util.ArrayList;

import com.project.graph.Node;
import com.project.model.Road;

public class Map {
	
	public Map(){
		
	}
	
	public void manhattan(ArrayList<Node> nodeList, ArrayList<Road> edgeList){
		nodeList.add(new Node(new Point(50,100), false));
		nodeList.add(new Node(new Point(300, 100), false));
		nodeList.add(new Node(new Point(550, 100), false));
//		nodeList.add(new Node(new Point(800, 100), false));
		nodeList.add(new Node(new Point(1050, 100), false));
		
		nodeList.add(new Node(new Point(50, 290), false));
		nodeList.add(new Node(new Point(300, 290), false));
		nodeList.add(new Node(new Point(550, 290), false));
		nodeList.add(new Node(new Point(800, 290), false));
		nodeList.add(new Node(new Point(1050, 290), false));
		
		nodeList.add(new Node(new Point(50, 490), false));
		nodeList.add(new Node(new Point(300, 490), false));
//		nodeList.add(new Node(new Point(550, 490), false));
		nodeList.add(new Node(new Point(800, 490), false));
		nodeList.add(new Node(new Point(1050, 490), false));
		
		nodeList.add(new Node(new Point(50, 690), false));
		nodeList.add(new Node(new Point(300, 690), false));
		nodeList.add(new Node(new Point(550, 690), false));
		nodeList.add(new Node(new Point(800, 690), false));
		nodeList.add(new Node(new Point(1050, 690), false));
		
		
		edgeList.add(new Road(nodeList.get(0), nodeList.get(1),30));
		edgeList.add(new Road(nodeList.get(1), nodeList.get(0),30));
		
		edgeList.add(new Road(nodeList.get(0), nodeList.get(4),30));
		edgeList.add(new Road(nodeList.get(4), nodeList.get(0),30));
		
		edgeList.add(new Road(nodeList.get(1), nodeList.get(2),30));
		edgeList.add(new Road(nodeList.get(2), nodeList.get(1),30));
		
		edgeList.add(new Road(nodeList.get(1), nodeList.get(5),30));
		edgeList.add(new Road(nodeList.get(5), nodeList.get(1),30));

		edgeList.add(new Road(nodeList.get(2), nodeList.get(6),30));
	
		edgeList.add(new Road(nodeList.get(2), nodeList.get(3),30));
		edgeList.add(new Road(nodeList.get(3), nodeList.get(2),30));
	
		edgeList.add(new Road(nodeList.get(3), nodeList.get(8),30));
		edgeList.add(new Road(nodeList.get(8), nodeList.get(3),30));
		
		edgeList.add(new Road(nodeList.get(4), nodeList.get(5),30));
		edgeList.add(new Road(nodeList.get(5), nodeList.get(4),30));
		
		edgeList.add(new Road(nodeList.get(5), nodeList.get(6),30));
		edgeList.add(new Road(nodeList.get(6), nodeList.get(5),30));
		
		edgeList.add(new Road(nodeList.get(6), nodeList.get(7),30));
		edgeList.add(new Road(nodeList.get(7), nodeList.get(6),30));
		
		edgeList.add(new Road(nodeList.get(7), nodeList.get(8),30));
		edgeList.add(new Road(nodeList.get(8), nodeList.get(7),30));
		
		edgeList.add(new Road(nodeList.get(4), nodeList.get(9),30));
		edgeList.add(new Road(nodeList.get(9), nodeList.get(4),30));
		
		edgeList.add(new Road(nodeList.get(5), nodeList.get(10),30));
		edgeList.add(new Road(nodeList.get(10), nodeList.get(5),30));

		edgeList.add(new Road(nodeList.get(7), nodeList.get(11),30));
		edgeList.add(new Road(nodeList.get(11), nodeList.get(7),30));
		
		edgeList.add(new Road(nodeList.get(8), nodeList.get(12),30));
		edgeList.add(new Road(nodeList.get(12), nodeList.get(8),30));

		edgeList.add(new Road(nodeList.get(9), nodeList.get(10),30));
		
		edgeList.add(new Road(nodeList.get(11), nodeList.get(12),30));
		edgeList.add(new Road(nodeList.get(12), nodeList.get(11),30));

		edgeList.add(new Road(nodeList.get(9), nodeList.get(13),30));
		edgeList.add(new Road(nodeList.get(13), nodeList.get(9),30));
		
		edgeList.add(new Road(nodeList.get(10), nodeList.get(14),30));
		edgeList.add(new Road(nodeList.get(14), nodeList.get(10),30));

		edgeList.add(new Road(nodeList.get(13), nodeList.get(14),30));
		edgeList.add(new Road(nodeList.get(14), nodeList.get(13),30));

		edgeList.add(new Road(nodeList.get(14), nodeList.get(15),30));
		edgeList.add(new Road(nodeList.get(15), nodeList.get(14),30));
		
		edgeList.add(new Road(nodeList.get(15), nodeList.get(16),30));
		edgeList.add(new Road(nodeList.get(16), nodeList.get(15),30));
		
		edgeList.add(new Road(nodeList.get(16), nodeList.get(17),30));
		edgeList.add(new Road(nodeList.get(17), nodeList.get(16),30));

		edgeList.add(new Road(nodeList.get(11), nodeList.get(16),30));
		edgeList.add(new Road(nodeList.get(16), nodeList.get(11),30));

		edgeList.add(new Road(nodeList.get(12), nodeList.get(17),30));
		edgeList.add(new Road(nodeList.get(17), nodeList.get(12),30));


	}
	
}
