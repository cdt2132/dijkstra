//Caroline Trimble 
//cdt2132
//Homework 4
//Vertex Class


import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
	private String nodeName;
	//Name of vertex
	private ArrayList<Edge> adjacencyList;
	//Adjacency List
	private Vertex parent; 
	//Parent (for use in Dijkstra's)
	private double distance; 
	//Distance (for use in Dijkstra's)
	private Object heapPos; 
	//Position in heap (for use in Dijkstra's we need to 
	//keep track each vertice's position in the priority queue) 

	public Vertex(String n, double d) {
		nodeName = n;
		distance = d; 
		adjacencyList = new ArrayList<Edge>();
		heapPos = null; 
		parent = null; 
	}
	//Creates new vertex with certain name and distance
	
	public void setParent(Vertex v){
		parent = v; 
	}
	//Sets parent to specified vertex
	//This is for dijkstra's algorithm so we can "create a path"
	
	public String getName(){
		return nodeName; 
	}
	//Getter method for name 
	
	public Double getDistance(){
		return distance; 
	}
	//Getter method for distance 
	
	public void setDistance(Double d){
		distance = d; 
	}
	//Setter method for distance 

	public void addToList(Vertex v, Double d) {
		Edge e = new Edge(v,d); 
		adjacencyList.add(e); 
	}
	//Pass in a vertex and a distance then the method creates
	//An edge and add's it to the vertex's adjacency list 

	public ArrayList<Edge> getEdges() {
		return adjacencyList; 
	}
	//returns the adjacency list
	
	public String toString(){
		Vertex p = this.parent; 
		String out = this.nodeName; 
		while(p!= null){
			out += " " + p.getName(); 
			p = p.parent; 
		}
		
		return out; 
	}
	//To string method prints out the "path"
	
	public double path(){
		Double d = this.distance; 
		return d; 
	}
	//Calculates the total distance of the minimal path 
	
	public void setPosition(Object p){
		heapPos = p; 
	}
	//Setter method for heap position 
	
	public Object getPosition(){
		return heapPos;
	}
	//Getter method for heap position 
	
	public int compareTo(Vertex v){
		if(this.distance > v.distance) 
			return 1; 
		if(this.distance == v.distance)
			return 0; 
		else 
			return -1; 
	}

}
