package com.graph.DepthFirstSearch;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Main {

	private static Graph graph;
	private static ReadFile readFile;
	
	public static void main(String[] args) {
		
		graph = new SingleGraph("Facebook");
				
		readFile = new ReadFile();
		
        System.setProperty("org.graphstream.ui.render", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        graph.addAttribute("ui.quality");
                
		addNodes();
		
		addEdges();
			
		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
		
		depthFirstSearch.search();
		
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
	
}
