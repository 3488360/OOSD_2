package controller;

import java.util.HashMap;

import model.Player;

public class PlayerController {
	private static PlayerController instance;
	private HashMap<String, Player> players = new HashMap<String, Player>();
	private int amount = 0;
	
	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
		}
		
		return instance;
	}
	
	private PlayerController() {
		amount++;
		players.put("player" + amount, new Player("player" + amount));
		amount++;
		players.put("player" + amount, new Player("player" + amount));
	}
	
	public int getPoints(String name) {
		return players.get(name).getPoints();
	}
	
	public String getPlayerName(String name) {
		return players.get(name).getPlayerName();
	}
	
	public int getPlayerPoints(String name) {
		return players.get(name).getPoints();
	}
	
	public int getAmount() {
		return amount;
	}

	public void setName(String name, String playerName) {
		players.get(name).setPlayerName(playerName);
	}
	
	public void setPoints(String name, int points) {
		players.get(name).setPoints(points);;
	}
}
