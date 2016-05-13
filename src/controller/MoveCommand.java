package controller;


import java.util.List;

import model.*; 

public class MoveCommand implements Command {
//	We need the gameController to send messages back to the gui client
//  For example if the move they performed has failed 	
	private GameController gameController; 
	
	
	
	private String player; 
	private Board board; 
	private Coordinate currentlySelected; 
	private Coordinate destinationSelected; 
	
	public MoveCommand(GameController gameController, String player, Board board,Coordinate currentlySelected, Coordinate destinationSelected){
		this.player = player;
		this.board = board; 
		this.currentlySelected = currentlySelected; 
		this.destinationSelected = destinationSelected;
		this.gameController = gameController; 
	}
	
	
	@Override
	public void execute(){
		if (player.equals(board.getPlayer(currentlySelected))){
			if(canMoveTo()){
				board.setPiece(destinationSelected,board.getPiece(currentlySelected));
				board.setPiece(currentlySelected, null);
//				gameController.updateBoard(); maybe put this after exiting the move function? 
				currentlySelected = null;
				destinationSelected = null;
//				return true;
			}
			else{
//				THIS IS REDUNDNT PLEASE ANSWER ASAP 
				gameController.message("This piece cannot move there");
			}
		}
//		return false;
	}

	
//	To be implemented
	public void setMoveableCells(){

	}
	
	private boolean canMoveTo(){
//		if the destination cell is in the list of movable cells
		List<Coordinate> moveableMoves = board.getMovement(currentlySelected);
		if (moveableMoves != null){
			for(Coordinate lists : moveableMoves){
				if(lists.x == destinationSelected.x && lists.y == destinationSelected.y)
					return true; 
			}
		}
		return false;
	}
		
	@Override
	public void undo(){
//		move it back to it's original square 
		board.setPiece(currentlySelected, board.getPiece(destinationSelected));
//		remove the piece that the piece just moved into 
		board.setPiece(destinationSelected, null);
		
	}

}
