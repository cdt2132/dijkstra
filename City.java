//Caroline Trimble
//cdt2132
//Homework 4 
//City Class

public class City {
	//This city class is used to create the Vertices of the graph 
	//In the Map class so that the document can be read in and each
	//Line can be turned into a "city" and passed into an ArrayList of cities
	
	private String name; 
	//Name of city
	private int x;
	//X coordinate 
	private int y;
	//Y coordinate

	public City(String n, int one, int two) {
		name = n; 
		x = one; 
		y = two; 
	}
	//City constructor
	
	public String getName(){
		return name; 
	}
	//Getter method for name
	
	public int getX(){
		return x; 
	}
	//Getter method for X coordinate
	
	public int getY(){
		return y; 
	}
	//Getter method for Y coordinate
	
	public String toString(){
		String str = (name + "(" + x + ", " + y + ")");  
		return str; 
	}
	//ToString method for city class
}
