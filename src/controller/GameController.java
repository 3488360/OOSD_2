package controller;

import java.util.List;

import model.Coordinate;
import model.Game;
import model.GameTimer;
import model.Player;
import model.pieces.PieceInterface;
import view.AbstractUIFactory;
import view.ViewMain;

public class GameController {
	private ViewMain userInterface;
	private Game game;
	
	public GameController(Game game, PlayerController playerController, ButtonControllerInterface buttonController, GameTimer timer, AbstractUIFactory uiFactory) {
		this.game = game; 
		userInterface = new ViewMain(this, game.getBoard(), playerController, buttonController, timer, uiFactory);
	}
	
	public Game getGame() {
		return game;
	}
	
	public void update() {
		userInterface.updateBoard();
	}

	public void updateBoard() {
		userInterface.updateBoard();
	}
	
	public void hideSelected() {
		userInterface.hideSelected();
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

	public void updateSelectedPiece(PieceInterface p) {
		userInterface.updateSelectedPiece(p.getName(), Integer.toString(p.getCurrentHealth()), Integer.toString(p.getStrength()));
	}
	
	public void updateMoves(List<Coordinate> moves) {
		userInterface.updateMoves(moves);
	}
	
	public void updateAttackRange(List<Coordinate> attackRange) {
		userInterface.updateAttackRange(attackRange);
	}

	public ViewMain getInterface() {
		return userInterface;
	}
}
