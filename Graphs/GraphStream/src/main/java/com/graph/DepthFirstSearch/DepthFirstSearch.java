package com.graph.DepthFirstSearch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class DepthFirstSearch {
	
	private Graph graph;
	private Queue<Node> queue;
	
	private Graph graphOfHighestComponent;
	
	public DepthFirstSearch(Graph graph) {
		
		this.graph = graph;
		graphOfHighestComponent = new SingleGraph("HighestComponent");
	}
	
	public void search() {

		for(Node node : graph.getEachNode()) {
			node.addAttribute("ui.color", "white");
			node.addAttribute("ui.style", "fill-color: white;");
		}
		
		for(Node node : graph.getEachNode()) {
						
			Graph subgraphComponent = new SingleGraph("MinimunSpanningTree");

			queue = null;
			
			if(node.getAttribute("ui.color") == "white") {
				
				queue = new LinkedList<Node>();
				
				depthFirstSearch(node, queue, subgraphComponent);
			}
		}
	}
	
	private void depthFirstSearch(Node currentNode, Queue<Node> queue, Graph subgraphComponent) {
		
		queue.add(currentNode);
		
		try {
			
			subgraphComponent.addNode(currentNode.getId());
			
		}catch(Exception e) {
			
		}
		
		currentNode.addAttribute("ui.color", "grey");
		currentNode.addAttribute("ui.style", "fill-color: grey;");

		Iterator<Node> neighborNode = currentNode.getNeighborNodeIterator();
		
		while(neighborNode.hasNext()) {
						
			Node nextNode = neighborNode.next();
			
			try {
			
				subgraphComponent.addNode(nextNode.getId());
				
				subgraphComponent.addEdge(currentNode.getId() + "-" + nextNode.getId(), currentNode.getId(), nextNode.getId());

			}catch(Exception e) {
				
			}
			
			if(nextNode.getAttribute("ui.color") != "black" 
					&& nextNode.getAttribute("ui.color") != "grey") {

				nextNode.addAttribute("ui.color", "grey");
				nextNode.addAttribute("ui.style", "fill-color: grey;");

				depthFirstSearch(nextNode, queue, subgraphComponent);
			}		
		}
			
		checkTheHighestSubGraph(subgraphComponent);

		queue.remove(currentNode);
		
		currentNode.addAttribute("ui.color", "black");
		currentNode.addAttribute("ui.style", "fill-color: black;");
	}
	
	private void checkTheHighestSubGraph(Graph subGraph) {
		if(subGraph.getNodeCount() > graphOfHighestComponent.getNodeCount()) {
			graphOfHighestComponent = subGraph;
		}
	}
	
	public Graph getMinimumSpanningTree() {
	
		graphOfHighestComponent.addAttribute("ui.quality");

        return graphOfHighestComponent;
	}
	
	public Graph getHighestComponent() {
		return graphOfHighestComponent;
	}
}
