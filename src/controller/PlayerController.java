package controller;

import model.Player;

public class PlayerController {
	private Player player1;
	private Player player2;
	
	public PlayerController() {
		player1 = new Player("player1");
		player2 = new Player("player2");
	}
	
	public int getPoints(String name) {
		if (name == "player1") {
			return player1.getPoints();
		}
		
		return player2.getPoints();
	}
	
	public Player getPlayer(String name) {
		if (name == "player1") {
			return player1;
		}
		
		return player2;
	}
	
	public String getPlayerName(String name) {
		if (name == "player1") {
			return player1.getPlayerName();
		} 
		
		return player2.getPlayerName();
	}
	
	public int getPlayerPoints(String name) {
		if (name == "player1") {
			return player1.getPoints();
		} 
		
		return player2.getPoints();
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
}
