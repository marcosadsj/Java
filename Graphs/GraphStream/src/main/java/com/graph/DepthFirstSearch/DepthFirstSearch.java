package com.graph.DepthFirstSearch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class DepthFirstSearch {
	
	private Graph graph;
	private Queue<Node> queue;
	
	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
	}
	
	public void search() {
		
		for(Node node : graph.getEachNode()) {
			node.addAttribute("ui.color", "white");
		}
		
		for(Node node : graph.getEachNode()) {
			queue = null;
			
			if(node.getAttribute("ui.color") == "white") {
				queue = new LinkedList<Node>();
				depthFirstSearch(node, queue);
			}
		}
	}
	
	private void depthFirstSearch(Node node, Queue<Node> queue) {
		queue.add(node);
		node.addAttribute("ui.color", "grey");
		
		Iterator<Node> neighborNode = node.getNeighborNodeIterator();
		
		while(neighborNode.hasNext()) {
			Node nextNode = neighborNode.next();
			
			if(nextNode.getAttribute("ui.color") != "red" 
					&& nextNode.getAttribute("ui.color") != "grey") {
				nextNode.addAttribute("ui.color", "grey");
				depthFirstSearch(nextNode, queue);
			}
		}
		
		queue.remove(node);
		node.addAttribute("ui.color", "red");
	}
}
