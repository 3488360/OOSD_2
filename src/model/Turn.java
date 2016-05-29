package model;

import controller.PlayerController;

public class Turn {
	private PlayerController playerController = PlayerController.getInstance();
	private String turn;
	private int tracker = 0;
	private final int amount = playerController.getAmount();
	
	public Turn() {
		turn = "player1";
		tracker = 1;
	}
	
	public Turn(String turn) {
		this.turn = turn;
		
		try {
			tracker = Integer.parseInt(turn.split("player")[0]);
		} catch (NumberFormatException e) {
			System.out.println("Could not find player number and could not set tracker properly");
			tracker = 1;
		}
	}
	
	public String nextTurn() {
		tracker++;
		
		if (tracker > amount) {
			tracker = 1;
		}
		
		turn = "player" + tracker;
		
		return turn;
	}
	
	public String getTurn() {
		return turn;
	}
	
}
