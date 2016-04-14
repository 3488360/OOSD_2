package controller;

import model.Player;

public class PlayerController {
	private Player player1;
	private Player player2;
	
	public PlayerController(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int getPoints(String name) {
		if (name == player1.getName()) {
			return player1.getPoints();
		}
		
		return player2.getPoints();
	}
	
	public String getPlayerName(String name) {
		if (name == player1.getName()) {
			return player1.getPlayerName();
		}
		
		return player2.getPlayerName();
	}
}
