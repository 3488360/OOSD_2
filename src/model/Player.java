package model;

//Information expert. Stores the player's name and points.
public class Player {
	private String playerName; 	//The player's name
	private String name;		//If they are player1 or player2
	private int points;			//Points to buy pieces with
	
	public Player(String p1) {
		name = p1;
		points = 500;
	}
	
	public void setPlayerName (String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPoints(int p) {
		points = p;
	}
	
	public int getPoints() {
		return points;
	}
}
