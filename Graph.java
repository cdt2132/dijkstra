//Caroline Trimble
//cdt2132
//Homework 4
//Graph Class 


import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> g;

	public Graph() {
		g = new ArrayList<Vertex>();
	}

	// Constructs new graph as an ArrayList of Vertexes

	public void add(Vertex v) {
		g.add(v);
	}

	// Method to add a vertex to the graph

	public ArrayList<Vertex> get() {
		return g;
	}

	// Method to get the vertex

	public int getSize() {
		return g.size();
	}

	// Method to get the size of the array the represents the graph

	public Vertex getVertex(String s) {
		for (int i = 0; i < g.size(); i++) {
			if (s.equals(g.get(i).getName()))
				return g.get(i);
		}
		return null;
	}

	// Gets the vertex with the specified string name
	// If a vertex with that name does not exist, returns null

	public void printGraph() {
		for (int i = 0; i < g.size(); i++)
			System.out.println(g.get(i).toString());
	}

	// Method to print the graph; prints a toString representation of
	// each vertex

	public void dijkstra(Vertex r) {
		// Veretx r represents the "source" we want for Dijkstra's
		PairingHeap<Vertex> p = new PairingHeap<Vertex>();
		// Constructs new Weiss PairingHeap of type Vertex
		for (int i = 0; i < g.size(); i++) {
			// For ever vertex in the graph
			if (g.get(i) == r)
				g.get(i).setDistance(0.0);
			// If the vertex is equal to the selected root, it sets
			// its distance to zero
			else
				g.get(i).setDistance(Double.MAX_VALUE);
			// Otherwise the distance is set to the double function MAX_VALUE
			// to represent infinity
			Object o = p.insert(g.get(i));
			// Inserts the Vertex into the priority heap
			g.get(i).setPosition(o);
			// Keeps track of the position within the vertex
			// Since the PairingHeap does not have a 'find' method
			// we must keep track of the 'position' in the heap of each vertex
		}
		while (!p.isEmpty()) {
			// While the queue is not empty
			Vertex v = p.deleteMin();
			// Set vertex v equal to the minimum of the priority queue
			ArrayList<Edge> n = v.getEdges();
			// Creates an ArrayList of edges of v's adjacency list
			for (int i = 0; i < n.size(); i++) {
				// for every edge in the adjacency list
				if (n.get(i).getVertex().getDistance() > v.getDistance()
						+ n.get(i).getWeight()) {
					// If the current distance is greater than v's
					// distance + the weight of the edge between them
					n.get(i)
							.getVertex()
							.setDistance(v.getDistance() + n.get(i).getWeight());
					// We set the distance to v's distance
					// + the weight of the edge between them
					n.get(i).getVertex().setParent(v);
					// We then set this adjacent vertex's parent to 'v'
					p.decreaseKey((PairingHeap.Position<Vertex>) n.get(i)
							.getVertex().getPosition(), n.get(i).getVertex());
					// Use the decrease key method to decrease the key to the
					// new distnace so the priority queue will re-configure
					// based on new keys (the distance in this case)
				}
			}
		}

	}

}
