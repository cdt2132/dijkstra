//Caroline Trimble
//cdt2132
//Homework 4 
//Map Class -- Class that draws the map to be put into the 
//JFrame in CityDistance class


import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Map extends JComponent {
	private static String list;

	public Map(String l) {
		list = l;
		setPreferredSize(new Dimension(800, 500));
	}

	public void paintComponent(Graphics g) {
		File inputFile = new File(CityDistance.coordinates);
		//Gets the document name from the CityDistance tester class
		//This is just done so that the user can enter both file names at the 
		//beginning 
		// Creates a new file with with the x and y vertices of each city
		ArrayList<City> cities = new ArrayList<City>();
		//Creates new array list of all cities in the document
		Graphics2D g2 = (Graphics2D) g;
		//Creates graphics drawer for use in drawing map 
		g.translate(0, 500);
		//Translates the graph so the origin is set at the bottom of window
		//This is just the way I decided to get the Y coordinates to appear on 
		//the screen
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
			String cityName = t.nextToken();
			//The city name is the first word on each line
			Integer x = (Integer.parseInt(t.nextToken()) / 5);
			//The x coordinate is the second on each line 
			//Divided all x coordinates by 5 so they would fit in smaller window
			Integer y = ((Integer.parseInt(t.nextToken()) / 5)*-1-200);
			//Y coordinate is third on each line 
			//Divided y coordinates by five so would fit in smaller window
			//Then multiplied by (-1) to "flip" the y coordinate
			//Then subtracted 200 to "bring" it into the window
			//While playing around these were the corrections I found 
			//that represented the graph best
			//It will not skew the graph in any way because it is done to all
			//Y coordinates
			City city = new City(cityName, x, y);
			//Constructs a new city with the name, x, and y coordinates 
			cities.add(city);
			//adds the city to the city array list 
		}

		StringTokenizer t = new StringTokenizer(list);
		ArrayList<City> selectedCities = new ArrayList<City>();
		//Creates another new ArrayList selected cities 
		while (t.hasMoreTokens()) {
			String name = t.nextToken();
			for (int i = 0; i < cities.size(); i++) {
				String cname = (cities.get(i).getName());
				if (name.equals(cname)) {
					selectedCities.add(cities.get(i));
					//If the name on the string result of the Dijkstra matches 
					//The city at the point in the city array list, we add it to 
					//selected cities
				}
			}
		}
		
		if(selectedCities.size() == 2){
			//This is just a method for taking care of drawing lines 
			//When there are only two cities in the path 
			String nFirst = selectedCities.get(0).getName();
			//Gets first city in array's name
			int xFirst = selectedCities.get(0).getX();
			//Gets x cord
			int yFirst = selectedCities.get(0).getY();
			//Gets y cord
			g2.setColor(Color.MAGENTA);
			g2.fillOval(xFirst, yFirst, 5, 5);
			//Draws oval at x and y coordinate 
			g2.setColor(Color.BLACK);
			g2.drawString(("Finish: " + nFirst), xFirst - 16, yFirst + 16);
			//Prints out title with "finish" because of the way the 
			//Dijkstra and the toString method work 
			String nLast = selectedCities.get(selectedCities.size() -1).getName();
			int xLast = selectedCities.get(selectedCities.size()-1).getX();
			int yLast = selectedCities.get(selectedCities.size()-1).getY();
			//Gets name, x, and y for other element
			g2.setColor(Color.MAGENTA);
			g2.fillOval(xLast, yLast, 5, 5);
			//Makes oval 
			g2.setColor(Color.BLACK);
			g2.drawString(("Start: " + nLast), xLast - 16, yLast + 16);
			//Print name with "Start" because of the way the methods work
			g2.setColor(Color.BLUE);
			g2.drawLine(xFirst, yFirst, xLast, yLast);

		}
		
		//Otherwise, if there are more than two cities, this method runs
		String nFirst = selectedCities.get(0).getName();
		int xFirst = selectedCities.get(0).getX();
		int yFirst = selectedCities.get(0).getY();
		//Get first city's name and coordinates
		g2.setColor(Color.MAGENTA);
		g2.fillOval(xFirst, yFirst, 5, 5);
		g2.setColor(Color.BLACK);
		g2.drawString(("Finish: " + nFirst), xFirst - 16, yFirst + 16);
		//Prints oval and name with same start/finish switch as before

		for (int j = 1; j < (selectedCities.size() - 1); j++) {
			int xPrev = selectedCities.get(j - 1).getX();
			int yPrev = selectedCities.get(j - 1).getY();
			//Gets x and y coordinates of pervious oval (city)
			String nCurr = selectedCities.get(j).getName();
			int xCurr = selectedCities.get(j).getX();
			int yCurr = selectedCities.get(j).getY();
			//Gets name, x and y coordinates of current city  
			int xNext = selectedCities.get(j + 1).getX();
			int yNext = selectedCities.get(j + 1).getY();
			//Gets x and y of next city 
			g2.setColor(Color.MAGENTA);
			g2.fillOval(xCurr, yCurr, 5, 5);
			//Draws oval for current city 
			g2.setColor(Color.BLACK);
			g2.drawString(nCurr, xCurr - 16, yCurr + 16);
			//Prints name
			g2.setColor(Color.BLUE);
			g2.drawLine(xPrev, yPrev, xCurr, yCurr);
			g2.drawLine(xCurr, yCurr, xNext, yNext);
			//Draws line between current city and previous city
			//and current city and next city 
		}

		String nLast = selectedCities.get(selectedCities.size() -1).getName();
		int xLast = selectedCities.get(selectedCities.size()-1).getX();
		int yLast = selectedCities.get(selectedCities.size()-1).getY();
		//Gets the name, x, and y of the last city on the list
		g2.setColor(Color.MAGENTA);
		g2.fillOval(xLast, yLast, 5, 5);
		//Draws the last oval 
		g2.setColor(Color.BLACK);
		g2.drawString(("Start: " + nLast), xLast - 16, yLast + 16);
		//Prints out name with same start/finish switch 
		

	}
}
