package controller;

import model.Coordinate;
import model.GameController;

public interface ButtonControllerInterface {	
	/**
	 * Works out what to do when a button on the board has been clicked
	 * 
	 * @param co - Coordinate
	 */
	public void passCoordinates(Coordinate co);
	
	/**
	 * Pauses the game
	 */
	public void pause();
	
	/**
	 * @param pieceName - The name of the piece to add
	 */
	public void addPiece(String pieceName);
	
	/**
	 * Exits the program
	 */
	public void exit();
	
	/**
	 * @return The main game object
	 */
	public GameController getGame();
	
	public void saveGame(String player1, String player2);

	public void undo();
}
