package com.graph.DepthFirstSearch;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class DepthFirstSearch {

	private static Graph graph;
	private static ReadFile readFile;
	
	public static void main(String[] args) {
		
		graph = new SingleGraph("Facebook");
				
		readFile = new ReadFile();
		
        System.setProperty("org.graphstream.ui.render", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		addNodes();
		
		addEdges();
			
		graph.display(true);
        
	}
	
	public static void addNodes() {
		
		String nodeName;
				
		while((nodeName = readFile.readLineNodes()) != null) {
			System.out.println(nodeName);
			graph.addNode(nodeName); 
		}
		
		readFile.closeFileNode();
	}
	
	public static void addEdges() {
		
		String edge[] = null;
		
		while((edge = readFile.readLineEdges()) != null) {
			System.out.println(edge[0]+"-"+edge[1]);
			graph.addEdge(edge[0]+"-"+edge[1], edge[0], edge[1]);
		}
		
		readFile.closeFileEdge();
	}
	
	public static void smallWorld()
	{
		
	}
	
	public static void deepSearch()
	{
		
	}
}
