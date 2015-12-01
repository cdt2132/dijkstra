//Caroline Trimble 
//cdt2132
//Homework 4
//THIS IS THE TESTER CLASS FOR THE PROGRAM!!!


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;

public class CityDistance {
	public static String coordinates; 
	private static Graph g = new Graph();
	// Creates a new Graph
	private static JFrame frame;

	// Creates variable of JFrame frame to be used throughout the class
	public static void main(String[] args) {
		System.out.println("Please enter a document name for CITY DISTANCES.");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		System.out.println("Please eneter a document name for CITY COORDINATES"); 
		coordinates = in.nextLine(); 
		//Gets user input for the names of the files they want to use for 
		//both the distances and the xycoordinates of cities
		File inputFile = new File(str);
		// Reads in citypairs.dat
		Scanner input = null;
		try {
			input = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Please try again with the proper file name!");
			// if the system can not find the file it prints this error
		}
		while (input.hasNextLine()) {
			String s = input.nextLine();
			StringTokenizer t = new StringTokenizer(s);
			String city1 = t.nextToken();
			String city2 = t.nextToken();
			Double distance = Double.parseDouble(t.nextToken());
			Vertex v1 = null;
			Vertex v2 = null;
			v1 = g.getVertex(city1);
			//Checks to see if the graph already has a vertex with 
			//name of city
			if (v1 == null) {
				v1 = new Vertex(city1, Double.MAX_VALUE);
				g.add(v1);
			}
			//If it does not, it creates a new vertex with the city name 
			//and adds to graph; sets distance equal MAX_VALUE (this is 
			//for djikstra purposes)

			v2 = g.getVertex(city2);
			//Checks to see if the graph already has a vertex with 
			//name of city
			if (v2 == null) {
				v2 = new Vertex(city2, Double.MAX_VALUE);
				g.add(v2);
			}
			//Same as v1 operation 
			
			v1.addToList(v2, distance);
			v2.addToList(v1, distance);
			//Adds both cities and distances as an EDGE to each other's 
			//adjacency lists
		}
		input.close();

		frame = new JFrame();
		String[] cityNames = { "Albuquerque", "Atlanta", "Boston", "Chicago",
				"Dallas", "Denver", "LasVegas", "LosAngeles", "Memphis",
				"Miami", "NewOrleans", "NewYork", "Omaha", "Peoria", "Phoenix",
				"Pittsburgh", "SaltLake", "SanFrancisco", "St.Louis", "Tulsa",
				"Washington" };
		final JComboBox<String> cityList1 = new JComboBox<String>(cityNames);
		// Create a drop down box of cities

		final JComboBox<String> cityList2 = new JComboBox<String>(cityNames);
		// Create a drop down box of cities

		final int FIELD_WIDTH = 20;
		final JTextField textField = new JTextField(FIELD_WIDTH);
		textField.setText("Path Distance");
		// Text field for resulting path length 

		frame.setLayout(new FlowLayout());

		frame.add(cityList1);
		// Gets the selected item from the drop down
		frame.add(cityList2);
		// Gets the selected item from the drop down
		JButton start = new JButton("Find the shortest path!");
		frame.add(start);
		frame.add(textField);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city1 = (String) cityList1.getSelectedItem();
				//sets "city1" to the item selected from drop down
				String city2 = (String) cityList2.getSelectedItem();
				//sets "city2" to the item selected from the drop down
				performDijkstra(g, city1);
				//Performs Dijkstra w/ city 1 as source
				String result = g.getVertex(city2).toString();
				//Creates a to string that will later be used to create graph
				textField.setText(String.valueOf(getPath(g, city2)));
				//Sets the text field to the minimum path distance
				renderGraph(result);
				//Runs the method to make the graph
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void performDijkstra(Graph m, String s) {
		m.dijkstra(m.getVertex(s));
		//Runs Dijkstra on specified graph with specified source

	}

	public static double getPath(Graph m, String c) {
		return m.getVertex(c).path();
		//Gets the minimum path by running the vertex method 'path'
		//on the distination city 
	}

	public static void renderGraph(String s) {

		Map mc = new Map(s);
		//Runs the math method with string
		//In above method 's' is set to the toString
		//representation of the destination city 

		frame.setLayout(new BorderLayout());
		frame.add(mc, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
