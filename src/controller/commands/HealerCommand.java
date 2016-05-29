package controller.commands;

import controller.CommonCode;
import controller.GameController;
import model.*;
import model.pieces.*;

public class HealerCommand implements Command {
	private Coordinate currentlySelected;
	private Coordinate destinationSelected;
	private Board board; 
	private GameController gameController;
	private boolean undoAbleMove = true;  
	private PieceInterface piece; 
	private int damageTaken; 
	
	public HealerCommand(Coordinate current, Coordinate destination, GameController gameController, Board board){
		currentlySelected = current; 
		destinationSelected = destination; 
		this.board = board; 
		this.gameController = gameController;
	}
	
	
	@Override
	public void execute() {
		piece = board.getPiece(destinationSelected);
		if (board.getPlayer(currentlySelected).equals(piece.getPlayerName())){
			if((piece.getCurrentHealth() - board.getPiece(currentlySelected).getStrength()) <= piece.getMaxHealth()){
				damageTaken = board.getPiece(currentlySelected).getStrength();
				piece.takeDamage(damageTaken);
				gameController.setDone(true);
			}	
			else if (piece.getCurrentHealth() == piece.getMaxHealth()) {
				CommonCode.message("Unable to heal - The Piece is at full health already");
				undoAbleMove = false; 
			} else {
				damageTaken = piece.getMaxHealth() - piece.getCurrentHealth(); 	
				piece.takeDamage(damageTaken);
				gameController.setDone(true);
			}
		}else{
			undoAbleMove = false; 
		}	
	}

	@Override
	public void undo() {
		piece.takeDamage(-damageTaken); 
	}

	@Override
	public boolean CanUndo() {
		return undoAbleMove;
	}

}