package controller;

import model.Board;
import model.Cell;
import model.Coordinate;
import model.Player;

/**
 * Responsible for communication between the board that is displayed and the board in the model.
 */
public class BoardController {
	private Board board;
	
	public BoardController(Board b) {
		this.board = b;
	}

	public int getHeight() {
		return board.getHeight();
	}

	public int getWidth() {
		return board.getWidth();
	}

	public Cell[][] getAllCells() {
		return board.getAllCells();
	}

	public String getPieceIcon(Coordinate co) {
		return board.getPiece(co).getIcon();
	}

	public String getPieceName(Coordinate co) {
		return board.getPiece(co).getName();
	}

	public String getPiecePlayerColor(Coordinate co) {
		return board.getPiece(co).getPlayer().getName();
	}

	public boolean getPiece(Coordinate co) {
		if (board.getPiece(co) == null)
			return false;
				
		return true;
	}
	
	public Player getPlayer(Coordinate co){
		return board.getPlayer(co);
		
	}

}
