package com.graph.BreadthFirstSearch;

import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class BreadthFirstSearch {
			
	Graph graph;
	
	LinkedList<Node> queueF;
	
	public BreadthFirstSearch(Graph graph)
	{
		this.graph = graph;
		
		queueF = new LinkedList<Node>();
		
		for(Node node : graph.getEachNode())
		{
			node.addAttribute("visitated", "no");
		}
	}
	
	public Graph search(int root){
		
		Node rootNode = graph.getNode(root);
				
		int cont = 0;
		
		rootNode.addAttribute("visitated","yes");
		
		rootNode.addAttribute("distance", cont);
		
		queueF.add(rootNode);
		
		while(!queueF.isEmpty())
		{
			cont++;
			
			Node neighborNode = queueF.get(0);
					
			Iterator<Node> iteratorNeighbor = neighborNode.getNeighborNodeIterator();
		
			while(iteratorNeighbor.hasNext())
			{
				Node currentNode = iteratorNeighbor.next();
				
				currentNode.addAttribute("distance", Integer.parseInt(neighborNode.getAttribute("distance").toString()) + 1);

				if(currentNode.getAttribute("visitated").toString().equals("no"))
				{
					graph.getEdge(neighborNode.getId() + "-" + currentNode.getId()).addAttribute("visitated","yes");
		
					currentNode.addAttribute("visitated","yes");
		
					queueF.add(currentNode);
		
				}else if(queueF.contains(currentNode)){
					graph.getEdge(neighborNode.getId() + "-" + currentNode.getId()).addAttribute("visitated","yes");
				}
			}
			queueF.remove(neighborNode);
		}
		return graph;
	}
}
