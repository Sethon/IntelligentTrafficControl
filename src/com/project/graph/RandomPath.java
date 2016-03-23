package com.project.graph;

import java.util.ArrayList;
import java.util.Random;

public class RandomPath {
	static ArrayList<Node> pathN= new ArrayList<Node>();
	
	 public static ArrayList<Node>  random(Node a,Node b){
		 pathN.add(a);
		 int size=a.getOutgoingEdges().size();
		 int rano = new Random().nextInt(size);
		 a.getOutgoingEdges().get(rano);
		 
		 if(a.getOutgoingEdges().get(rano).to!=b){
			 random(a.getOutgoingEdges().get(rano).to,b);
		 }
		 
		 else pathN.add(b);
		
		 
		return pathN;
		 
		
		
	
	 }
	 /**
	  * 
	  * 
	  * Test for random 
	  
	 public static void main(String[] args){
			//	Node node=new Node();
				
				Node a = new Node("a",null);
				Node b = new Node("b",null);
				Node c = new Node("c",null);
				Node d = new Node("d", null);
				Node e = new Node("e",null);
				Node f = new Node("f",null);
				
				ArrayList<Node> all = new ArrayList<Node>();
		
				
				all.add(a);
				all.add(b);
				all.add(c);
				all.add(d);
				all.add(e);
				all.add(f);
				ArrayList<Node> path = new ArrayList<Node>();
			//	a.getOutgoingEdges();
				
				a.connect(b,1,1);
				a.connect(c,1,1);
				b.connect(c,1,1);
				b.connect(d,1,1);
				c.connect(d, 1, 1);
				c.connect(e, 1, 1);
				d.connect(e, 1, 1);
				d.connect(f, 1, 1);
				e.connect(f, 1, 1);
				
				
				path = random(a, d);
			System.out.println("SIZE:"+path.size());
				for(int i=0;i<path.size();i++){
				System.out.println(path.get(i).getName());
				
				}
			
				
				
			}*/
}

