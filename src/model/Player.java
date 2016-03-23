package model;

public class Player {
	String name;
	int points;
	
	public Player(String p1) {
		name = p1;
		points = 0;
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
}
