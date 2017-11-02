package com.graph.SmallWorld;

import java.util.ArrayList;
import org.graphstream.graph.Graph;
import com.graph.BreadthFirstSearch.BreadthFirstSearch;

public class SmallWorld {
	
	Graph graph;
	BreadthFirstSearch breadthFirstSearch;
	
	ArrayList<Integer> neighborList;
	
	public SmallWorld(Graph graph) {
		
		this.graph = graph;
		
		breadthFirstSearch = new BreadthFirstSearch(graph);
	}
	
		
	public float getAverageGeodeticsDistances() {
		
		float average = 0;
		
		int sumGeodetics = 0;

		int maxNodes = graph.getNodeCount();
		
		for(int root = 0; root < maxNodes; root++){
			for(int j = 0; j < maxNodes; j++){
				if(root <= j)
				{
					sumGeodetics += breadthFirstSearch.search(root, j);
				}
			}
		}

		try {
			
			average = (1/(0.5f * maxNodes * (maxNodes + 1))) * sumGeodetics;
			
		}catch(Exception e){
			
			e.printStackTrace();
		}

		return average;
	}
}
