package com.graph.DepthFirstSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class ReadFile {

	private File fileNode;
	private File fileEdge;
	
	private FileReader fileReaderNode;
	private FileReader fileReaderEdge;

	private BufferedReader bufferReaderNode;
	private BufferedReader bufferReaderEdge;
	
	private boolean stateInitializedNode = false;
	private boolean stateInitializedEdge = false;
	
	String readedLineNode;
	String readedLineEdge;
	
	String nodePath = "/com/graph/files/VerticesFacebook.txt";
	String edgePath = "/com/graph/files/ArestasFacebook.txt";
	
	public ReadFile(){
		
		
		try {
			fileNode = new File(getClass().getResource(nodePath).toURI());
			fileEdge = new File(getClass().getResource(edgePath).toURI());

		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			fileReaderNode = new FileReader(fileNode);
			fileReaderEdge = new FileReader(fileEdge);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readLineNodes() {

		readedLineNode = null;

		if(stateInitializedNode == false) {
			bufferReaderNode = new BufferedReader(fileReaderNode);
			stateInitializedNode = true;
		}
		
		try {
			readedLineNode = bufferReaderNode.readLine();
			System.out.println(readedLineNode);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readedLineNode;
	}
	
	public String[] readLineEdges() {
				
		readedLineEdge = null;

		if(stateInitializedEdge == false) {
			bufferReaderEdge = new BufferedReader(fileReaderEdge);
			stateInitializedEdge = true;
		}
		
		try {
			readedLineEdge = bufferReaderEdge.readLine();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return readedLineEdge != null? readedLineEdge.split(";"): null;
	}
	
	public void closeFileNode() {
		try {
			bufferReaderNode.close();
			fileReaderNode.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeFileEdge() {
		try {
			bufferReaderEdge.close();
			fileReaderEdge.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
