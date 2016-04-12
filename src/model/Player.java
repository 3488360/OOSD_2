package model;

public class Player {
	String name;
	int points;
	int numPieces;
	
	public Player(String p1) {
		name = p1;
		points = 500;
	}
	
	public void setPoints(int p) {
		points = p;
	}
	
	public void addPoints(int p) {
		points += p;
	}
	
	public void takePoints(int p) {
		points -= p;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumPieces() {
		return numPieces;
	}
	
	public void setNumPieces(int i) {
		numPieces = i;
	}
	
	public void setName (String name) {
		this.name = name;
	}
}
