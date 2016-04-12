package model;

//Code used from http://www.javaworld.com/article/2074996/hashcode-and-equals-method-in-java-object---a-pragmatic-concept.html
public class Coordinate {
	public int x;
	public int y; 
	
	public Coordinate(int row, int col) {
		x = row; 
		y = col; 
	}
	
	@Override
	public int hashCode() {
		if (y < 10) 
			return x*10+y;
		else
			return x*100+y;
	}
	
	@Override
	public boolean equals(Object o) {
		
		Coordinate co = (Coordinate)o;
		if (co.x == x && co.y == y)
			return true;
		return false;
	}
}
	