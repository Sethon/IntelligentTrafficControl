package com.project.map;

import java.awt.Point;
import java.util.ArrayList;

import com.project.graph.Node;
import com.project.graph.Road;
import com.project.graph.SingleRoad;

public class Map {
	
	public Map(){
		
	}
	
	public void manhattan(ArrayList<Node> doubleEdges){
		
		/********************************** Create nodes *********************************/
		doubleEdges.add(new Node(new Point(50,100), false));
		doubleEdges.add(new Node(new Point(300, 100), false));
		doubleEdges.add(new Node(new Point(550, 100), false));
//		nodeList.add(new Node(new Point(800, 100), false));
		doubleEdges.add(new Node(new Point(1050, 100), false));
		
		doubleEdges.add(new Node(new Point(50, 290), false));
		doubleEdges.add(new Node(new Point(300, 290), false));
		doubleEdges.add(new Node(new Point(550, 290), false));
		doubleEdges.add(new Node(new Point(800, 290), false));
		doubleEdges.add(new Node(new Point(1050, 290), false));
		
		doubleEdges.add(new Node(new Point(50, 490), false));
		doubleEdges.add(new Node(new Point(300, 490), false));
//		nodeList.add(new Node(new Point(550, 490), false));
		doubleEdges.add(new Node(new Point(800, 490), false));
		doubleEdges.add(new Node(new Point(1050, 490), false));
		
		doubleEdges.add(new Node(new Point(50, 690), false));
		doubleEdges.add(new Node(new Point(300, 690), false));
		doubleEdges.add(new Node(new Point(550, 690), false));
		doubleEdges.add(new Node(new Point(800, 690), false));
		doubleEdges.add(new Node(new Point(1050, 690), false));
		
		
		
		
		/********************************** Create edges *********************************/
		
		doubleEdges.get(0).connect(doubleEdges.get(1), 30);
		doubleEdges.get(0).connect(doubleEdges.get(4), 30);
		
		doubleEdges.get(1).connect(doubleEdges.get(0), 30);
		doubleEdges.get(1).connect(doubleEdges.get(2), 30);
		doubleEdges.get(1).connect(doubleEdges.get(5), 30);
		
		doubleEdges.get(2).connect(doubleEdges.get(1), 30);
		doubleEdges.get(2).connect(doubleEdges.get(3), 30);
		
		doubleEdges.get(2).connect(doubleEdges.get(6), 30);		

		
		doubleEdges.get(3).connect(doubleEdges.get(2), 30);
		doubleEdges.get(3).connect(doubleEdges.get(8), 30);
		
		doubleEdges.get(4).connect(doubleEdges.get(0), 30);
		doubleEdges.get(4).connect(doubleEdges.get(5), 30);
		doubleEdges.get(4).connect(doubleEdges.get(9), 30);
		
		doubleEdges.get(5).connect(doubleEdges.get(1), 30);
		doubleEdges.get(5).connect(doubleEdges.get(4), 30);
		doubleEdges.get(5).connect(doubleEdges.get(6), 30);
		doubleEdges.get(5).connect(doubleEdges.get(10), 30);
		
		doubleEdges.get(6).connect(doubleEdges.get(5), 30);
		doubleEdges.get(6).connect(doubleEdges.get(7), 30);
		
		doubleEdges.get(7).connect(doubleEdges.get(6), 30);
		doubleEdges.get(7).connect(doubleEdges.get(8), 30);
		doubleEdges.get(7).connect(doubleEdges.get(11), 30);
		
		doubleEdges.get(8).connect(doubleEdges.get(3), 30);
		doubleEdges.get(8).connect(doubleEdges.get(7), 30);
		doubleEdges.get(8).connect(doubleEdges.get(12), 30);
		
		doubleEdges.get(9).connect(doubleEdges.get(4), 30);
		doubleEdges.get(9).connect(doubleEdges.get(10), 30);
		doubleEdges.get(9).connect(doubleEdges.get(13), 30);
		
		doubleEdges.get(10).connect(doubleEdges.get(5), 30);
		doubleEdges.get(10).connect(doubleEdges.get(14), 30);
		
		doubleEdges.get(11).connect(doubleEdges.get(7), 30);
		doubleEdges.get(11).connect(doubleEdges.get(12), 30);
		doubleEdges.get(11).connect(doubleEdges.get(16), 30);
		
		doubleEdges.get(12).connect(doubleEdges.get(8), 30);
		doubleEdges.get(12).connect(doubleEdges.get(11), 30);
		doubleEdges.get(12).connect(doubleEdges.get(17), 30);
		
		doubleEdges.get(13).connect(doubleEdges.get(9), 30);
		doubleEdges.get(13).connect(doubleEdges.get(14), 30);
		
		doubleEdges.get(14).connect(doubleEdges.get(10), 30);
		doubleEdges.get(14).connect(doubleEdges.get(13), 30);
		doubleEdges.get(14).connect(doubleEdges.get(15), 30);
		
		doubleEdges.get(15).connect(doubleEdges.get(14), 30);
		doubleEdges.get(15).connect(doubleEdges.get(16), 30);
		
		doubleEdges.get(16).connect(doubleEdges.get(11), 30);
		doubleEdges.get(16).connect(doubleEdges.get(15), 30);
		doubleEdges.get(16).connect(doubleEdges.get(17), 30);
		
		doubleEdges.get(17).connect(doubleEdges.get(12), 30);
		doubleEdges.get(17).connect(doubleEdges.get(16), 30);

		
		
	}
	
}
