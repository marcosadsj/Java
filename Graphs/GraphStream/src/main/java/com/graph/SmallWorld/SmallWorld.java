package com.graph.SmallWorld;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.graph.BreadthFirstSearch.BreadthFirstSearch;

public class SmallWorld {
	
	Graph graph;
	BreadthFirstSearch breadthFirstSearch;
	
	ArrayList<Integer> neighborList;
	
	public SmallWorld(Graph graph) {
		
		this.graph = graph;
		
		breadthFirstSearch = new BreadthFirstSearch(graph);
	}
	
	private int calcule(Graph graphWithDistances) {
		
		int cont = 0;
		
		Iterator<Node> graphIterator = graphWithDistances.iterator();
	
		while(graphIterator.hasNext()){
			cont += Integer.parseInt(graphIterator.next().getAttribute("distance").toString());
		}
		
		return cont;
	}
	
	public float getAverageGeodeticsDistances() {
		
		float average = 0;
		
		int sumGeodetics = 0;

		int maxNodes = graph.getNodeCount();
		
		for(int root = 0; root < maxNodes; root++){
			
			sumGeodetics += calcule(breadthFirstSearch.search(root));
		}

		try {
			
			average = (1/(0.5f * maxNodes * (maxNodes + 1))) * sumGeodetics;
			
		}catch(Exception e){
			
			e.printStackTrace();
		}

		return average;
	}
}
