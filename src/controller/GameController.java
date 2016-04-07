package controller;

import model.Coordinate;
import model.Game;
import model.Player;
import view.MainUserInterface;

public class GameController {
	MainUserInterface userInterface;
	Game game;
	
	public GameController(MainUserInterface userInterface, Game game){
		this.userInterface = userInterface; 
		this.game = game; 
	}
	/****************Functions that update the user interface****/
	public void updatePoints(){
		userInterface.updatePoints();
	}
	
	public void updateTurnText(Player p){
		userInterface.updateTurn(p);
	}
	
	public void updateSelectedPiece(String selectedPiece){
		userInterface.updateSelectedPiece(selectedPiece);
	}
	
	public void hideSelected(){
		userInterface.hideSelected();
	}
	public void message(String m){
		userInterface.message(m);
	}
	
	public void updateBoard() {
		System.out.println("Updating the board");
		userInterface.updateBoard();
	}
	
	public void close() {
		userInterface.getContentPane().removeAll();
		userInterface.setVisible(false);
		userInterface = null;
	}

	
/**********Sends the game coordinates to the game engine********/
	public void passCoordinates(Coordinate coordinate) {
//	sends the coordinates the user just pressed to the game(model) 
		game.passCoordinates(coordinate);
	}
	
	
}
