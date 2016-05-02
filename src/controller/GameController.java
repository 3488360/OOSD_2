package controller;

import java.util.List;

import model.Coordinate;
import model.Game;
import model.Player;
import model.pieces.PieceInterface;
import view.ViewMain;

public class GameController {
	private ViewMain userInterface;
	private Game game;
	
	public GameController(Game game, PlayerController playerController){
		userInterface = new ViewMain(this, new BoardController(game.getBoard()), playerController); 
		this.game = game; 
	}
	
	public void update() {
		userInterface.updateBoard();
	}

	public void passCoordinates(Coordinate coordinate) {
		game.passCoordinates(coordinate);
	}

	public void updateBoard() {
		userInterface.updateBoard();
	}
	
	public void hideSelected() {
		userInterface.hideSelected();
	}

	public void updateTimer(int sec) {
		userInterface.updateTimer(sec);
	}
	
	public void message(String m) {
		userInterface.message(m);
	}

	public void updateTurn(Player p) {
		userInterface.updateTurn(p.getPlayerName());
	}

	public void updatePoints() {
		userInterface.updatePoints();
	}
	
	public void close() {
		userInterface.getContentPane().removeAll();
		userInterface.setVisible(false);
	}

	public void addPiece(String pieceName) {
		game.addPiece(pieceName);		
	}

	public void updateSelectedPiece(PieceInterface p) {
		userInterface.updateSelectedPiece(p.getName(), Integer.toString(p.getCurrentHealth()), Integer.toString(p.getStrength()));
	}
	
	public void updateMoves(List<Coordinate> moves, List<Coordinate> attackRange, Coordinate currentlySelected) {
		userInterface.updateMoves(moves, attackRange, currentlySelected);
	}
}
