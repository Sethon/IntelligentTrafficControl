package com.project.map;

import java.awt.Point;
import java.util.ArrayList;

import com.project.graph.Node;
import com.project.graph.Road;
import com.project.graph.SingleRoad;

public class Map {
	
	public Map(){
		
	}
	
	public void manhattan(ArrayList<Node> nodeList, ArrayList<Road> edgeList){
		
		/********************************** Create nodes *********************************/
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
		
		
		
		
		/********************************** Create edges *********************************/
		
		nodeList.get(0).connect(nodeList.get(1), 30);
		nodeList.get(0).connect(nodeList.get(4), 30);
		
		nodeList.get(1).connect(nodeList.get(0), 30);
		nodeList.get(1).connect(nodeList.get(2), 30);
		nodeList.get(1).connect(nodeList.get(5), 30);
		
		nodeList.get(2).connect(nodeList.get(1), 30);
		nodeList.get(2).connect(nodeList.get(6), 30);		
		nodeList.get(2).connect(nodeList.get(3), 30);
		
		nodeList.get(3).connect(nodeList.get(2), 30);
		nodeList.get(3).connect(nodeList.get(8), 30);
		
		nodeList.get(4).connect(nodeList.get(0), 30);
		nodeList.get(4).connect(nodeList.get(5), 30);
		nodeList.get(4).connect(nodeList.get(9), 30);
		
		nodeList.get(5).connect(nodeList.get(1), 30);
		nodeList.get(5).connect(nodeList.get(4), 30);
		nodeList.get(5).connect(nodeList.get(6), 30);
		nodeList.get(5).connect(nodeList.get(10), 30);
		
		nodeList.get(6).connect(nodeList.get(5), 30);
		nodeList.get(6).connect(nodeList.get(7), 30);
		
		nodeList.get(7).connect(nodeList.get(6), 30);
		nodeList.get(7).connect(nodeList.get(8), 30);
		nodeList.get(7).connect(nodeList.get(11), 30);
		
		nodeList.get(8).connect(nodeList.get(3), 30);
		nodeList.get(8).connect(nodeList.get(7), 30);
		nodeList.get(8).connect(nodeList.get(12), 30);
		
		nodeList.get(9).connect(nodeList.get(4), 30);
		nodeList.get(9).connect(nodeList.get(10), 30);
		nodeList.get(9).connect(nodeList.get(13), 30);
		
		nodeList.get(10).connect(nodeList.get(5), 30);
		nodeList.get(10).connect(nodeList.get(14), 30);
		
		nodeList.get(11).connect(nodeList.get(7), 30);
		nodeList.get(11).connect(nodeList.get(12), 30);
		nodeList.get(11).connect(nodeList.get(16), 30);
		
		nodeList.get(12).connect(nodeList.get(8), 30);
		nodeList.get(12).connect(nodeList.get(11), 30);
		nodeList.get(12).connect(nodeList.get(17), 30);
		
		nodeList.get(13).connect(nodeList.get(9), 30);
		nodeList.get(13).connect(nodeList.get(14), 30);
		
		nodeList.get(14).connect(nodeList.get(10), 30);
		nodeList.get(14).connect(nodeList.get(13), 30);
		nodeList.get(14).connect(nodeList.get(15), 30);
		
		nodeList.get(15).connect(nodeList.get(14), 30);
		nodeList.get(15).connect(nodeList.get(16), 30);
		
		nodeList.get(16).connect(nodeList.get(11), 30);
		nodeList.get(16).connect(nodeList.get(15), 30);
		nodeList.get(16).connect(nodeList.get(17), 30);
		
		nodeList.get(17).connect(nodeList.get(12), 30);
		nodeList.get(17).connect(nodeList.get(16), 30);

		
		
	}
	
}
