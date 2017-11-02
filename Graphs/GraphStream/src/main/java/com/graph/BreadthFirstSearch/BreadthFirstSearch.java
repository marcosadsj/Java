package com.graph.BreadthFirstSearch;

import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class BreadthFirstSearch {
			
	Graph graph;
	
	LinkedList<Node> queueF;
	
	int neighborMatrix[][];
	
	public BreadthFirstSearch(Graph graph)
	{
		this.graph = graph;
		
		queueF = new LinkedList<Node>();
		
		for(Node node : graph.getEachNode())
		{
			node.addAttribute("visitated", "no");
		}
	}
	
	public int search(int root, int desiredLeaf){
		
		Node rootNode = graph.getNode(root);
				
		int distance = 0;
				
		rootNode.addAttribute("visitated","yes");
		
		rootNode.addAttribute("distance", 0);
		
		queueF.add(rootNode);
		
		while(!queueF.isEmpty())
		{			
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
		
					if(!currentNode.equals((graph.getNode(desiredLeaf))))
					{
						queueF.add(currentNode);
						
						System.out.println("diferentnode");

					}else{
						System.out.println("passou0");

						distance = Integer.parseInt(currentNode.getAttribute("distance").toString());
						
						System.out.println("passou1");
					}
		
				}else if(queueF.contains(currentNode)){
					graph.getEdge(neighborNode.getId() + "-" + currentNode.getId()).addAttribute("visitated","yes");
				}
			}
			queueF.remove(neighborNode);
		}
		return distance;
	}
}
