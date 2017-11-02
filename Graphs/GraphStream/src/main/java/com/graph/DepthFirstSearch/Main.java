package com.graph.DepthFirstSearch;

import javax.swing.JOptionPane;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import com.graph.MajorSubGraphGenerator.MajorSubgraphGenerator;
import com.graph.SmallWorld.SmallWorld;


public class Main {

	private static Graph graph;
	private static ReadFile readFile;

	
	public static void main(String[] args) {
		
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		graph = new SingleGraph("Facebook");
				
		readFile = new ReadFile();
                
		addNodes();
		
		addEdges();
			
		//DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);

		MajorSubgraphGenerator majorSubgraphGenerator= new MajorSubgraphGenerator(graph);
		
		majorSubgraphGenerator.search();
		
		majorSubgraphGenerator.getMajorSubgraph().display();
		
		//depthFirstSearch.search();
		
		//JOptionPane.showMessageDialog(null, depthFirstSearch.getMinimumSpanningTree().getNodeCount() + 
		//									"\n" + depthFirstSearch.getMinimumSpanningTree().getEdgeCount());

		//depthFirstSearch.getMinimumSpanningTree().display();
				
		graph.addAttribute("ui.quality");

		graph.display();
		
		//SmallWorld smallWorld = new SmallWorld(depthFirstSearch.getMinimumSpanningTree());
		
		//System.out.println(smallWorld.getAverageGeodeticsDistances());
						
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
