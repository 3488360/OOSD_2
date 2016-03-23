package controller;

import model.Coordinate;
import model.Game;
import view.MainUserInterface;

public class GameController {
	MainUserInterface userInterface;
	Game game;
	
	public GameController(MainUserInterface userInterface, Game game){
		this.userInterface = userInterface; 
		this.game = game; 
	}
	
	
	
	public void update() {
		userInterface.updateBoard();
		
	}



	public void passCoordinates(Coordinate coordinate) {
		game.passCoordinates(coordinate);
		
	}
	

	
	
	
	
	
	
	
	
}
