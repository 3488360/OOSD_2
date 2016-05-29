package controller;

import model.*;
import model.pieces.*;
public class HealerCommand implements Command {
	private Coordinate currentlySelected;
	private Coordinate destinationSelected;
	private Board board; 
	private GameController gc;
	private boolean undoAbleMove = true;  
	private PieceInterface piece; 
	private int healingAmount; 
	
	public HealerCommand(Coordinate current, Coordinate destination, GameController gc, Board board){
		currentlySelected = current; 
		destinationSelected = destination; 
		this.board = board; 
		this.gc = gc; 
	}
	
	
	@Override
	public void execute() {
		piece = board.getPiece(destinationSelected);
		if (board.getPlayer(currentlySelected).equals(piece.getPlayerName())){
			if((piece.getCurrentHealth() - board.getPiece(currentlySelected).getStrength()) <= piece.getMaxHealth()){
				healingAmount = board.getPiece(currentlySelected).getStrength();
				piece.takeDamage(healingAmount);
				gc.getGame().setDone(true);
			}	
			else if (piece.getCurrentHealth() == piece.getMaxHealth()) {
				gc.message("Unable to heal - The Piece is at full health already");
				undoAbleMove = false; 
			} else {
				healingAmount = piece.getMaxHealth() - piece.getCurrentHealth(); 	
				piece.takeDamage(healingAmount);
				gc.getGame().setDone(true);
			}
		}else{
			undoAbleMove = false; 
		}	
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		piece.takeDamage(-healingAmount); 
	}

	@Override
	public boolean CanUndo() {
		return undoAbleMove;
	}

}
