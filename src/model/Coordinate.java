package model;

//Code used from http://www.javaworld.com/article/2074996/hashcode-and-equals-method-in-java-object---a-pragmatic-concept.html
public class Coordinate {
	public int x;
	public int y; 
	
	public Coordinate(int row, int col) {
		x = row; 
		y = col; 
	}
	
	//Needs to be overridden so hashmap works in BoardLayout
	@Override
	public int hashCode() {
		if (y < 10) 
			return x*10+y;
		else
			return x*100+y;
	}
	
	//Needs to be overridden so hashmap works in BoardLayout
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Coordinate)) {
			return false;
		}
		Coordinate co = (Coordinate)o;
		return (co.x == x && co.y == y);
	}
}
	