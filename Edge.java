//Caroline Trimble
//cdt2132
//Homework 4 
//Edge Class -- Used in AdjacencyList

public class Edge {
	private Vertex destination; 
	//Destination vertex
	private Double weight;
	//Weight of path 
	
	public Edge(Vertex d, double w){
		destination = d; 
		weight = w; 
	}
	//Constructs new edge with destination d and weight w
	//This is used in the adjacency list

	public Vertex getVertex(){
		return destination;
	}
	//Getter method for destination 
	
	public double getWeight(){
		return weight; 
	}
	//Getter method for weight 
	
	public String toString(){
		return destination.getName() + " " + weight; 
	}
	//toString method for edges
}
