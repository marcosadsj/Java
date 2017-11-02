package com.graph.MajorSubGraphGenerator;

import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class MajorSubgraphGenerator {

	Graph graph;
	Graph majorSubgraph;
	
	LinkedList<Node> queue;
	String edgeID;
	String edgeIDReverse;

	
	public MajorSubgraphGenerator(Graph graph) {
		this.graph = graph;
		majorSubgraph = new SingleGraph("MajorSubgraph");

	}
	
	public void search() {
		
		for(Edge edge : graph.getEachEdge()) {
			
			edge.addAttribute("visited", "no");
		}
				
		for(Node node: graph.getEachNode()) {
			
			node.addAttribute("visited", "no");
		}
		
		for(Node node: graph.getEachNode()) {

			node.addAttribute("visited", "yes");
			
			Graph subgraphComponent = new SingleGraph("MajorSubgraph");
			
			queue = new LinkedList<Node>();
				
			generateSubgraph(node, queue, subgraphComponent);
		}
	}
		
	public void generateSubgraph(Node currentNode, LinkedList<Node> queue, Graph subgraphComponent) {
		
		queue.add(currentNode);
		
		try {
			
			subgraphComponent.addNode(currentNode.getId());
			System.out.println("passou");
			
		}catch(Exception e) {
			
		}

		Iterator<Node> neighborNode = currentNode.getNeighborNodeIterator();
		
		while(neighborNode.hasNext()) {
						
			Node nextNode = neighborNode.next();
			
			edgeID = currentNode.getId() + "-" + nextNode.getId();
			edgeIDReverse = nextNode.getId() + "-" + currentNode.getId();
								
			try {
				graph.getEdge(edgeID);
				System.out.println("foi1");

			}catch(Exception e)
			{
				edgeID = edgeIDReverse;
				System.out.println("naofoi1");
			}
			
			try {
				subgraphComponent.addNode(currentNode.getId());
				System.out.println("foi2");

			}catch(Exception e)
			{
				System.out.println("naofoi2");
			}
			
			try {
				System.out.println("foi3");

				subgraphComponent.addNode(nextNode.getId());
				
			}catch(Exception e)
			{
				System.out.println("naofoi3");
			}
						
			try {
			
				currentNode.addAttribute("visited","yes");
				
				System.out.println("passou1");
				
				if(graph.getEdge(edgeID).getAttribute("visited").equals("no"))
				{
					System.out.println("passou2");

					try {
						graph.getEdge(edgeID).addAttribute("visited","yes");

					}catch(Exception e)
					{
						System.out.println("deu ruim antes passou3");
					}
					
					System.out.println("passou3");

					try {
						subgraphComponent.addEdge(edgeID, currentNode, nextNode);

					}catch(Exception e)
					{
						System.out.println("deu ruim antes 3.1");

					}
					System.out.println("passou3.1");
				}
				
				if(nextNode.getAttribute("visited").equals("no"))
				{
					System.out.println("passou4");

					generateSubgraph(currentNode, queue, subgraphComponent);
				}	
				
			}catch(Exception e) {
				System.out.println("exception");

			}
		}		
		
		checkTheHighestSubgraph(subgraphComponent);
		
		queue.remove(currentNode);
	}
	
	
	private void checkTheHighestSubgraph(Graph subgraphComponent) {		
		System.out.println("checkout");

		if(subgraphComponent.getNodeCount() >= majorSubgraph.getNodeCount())
		{
			System.out.println("checkin");

			majorSubgraph = subgraphComponent;
		}
	}
	
	public Graph getMajorSubgraph() {
		return majorSubgraph;
	}
}
