package controller.commands;

import java.util.List;

import controller.CommonCode;
import model.*; 

public class MoveCommand implements Command {
//	We need the gameController to send messages back to the gui client
//  For example if the move they performed has failed 	
	private boolean undoAbleMove = false;
	private String player; 
	private Board board; 
	private Coordinate currentlySelected; 
	private Coordinate destinationSelected; 
	private Game game;
	
	public MoveCommand(Game game, String player, Board board, Coordinate currentlySelected, Coordinate destinationSelected){
		this.player = player;
		this.board = board;
		this.currentlySelected = currentlySelected;
		this.destinationSelected = destinationSelected;
		this.game = game;
	}
	
	@Override
	public void execute(){
		if (player.equals(board.getPlayer(currentlySelected))){
			if(canMoveTo()){
				board.setPiece(destinationSelected,board.getPiece(currentlySelected));
				board.setPiece(currentlySelected, null);
//				gameController.updateBoard(); maybe put this after exiting the move function? 
//				return true;
				game.setDone(true);
				undoAbleMove = true;
			}
			else{
//				THIS IS REDUNDNT PLEASE ANSWER ASAP 
				CommonCode.message("This piece cannot move there");
			}
		}
//		return false;
	}

	
	private boolean canMoveTo(){
//		if the destination cell is in the list of movable cells
		List<Coordinate> moveableMoves = board.getMovement(currentlySelected, player);
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
		game.pause();
		game.setDone(true);
		game.resume();
	}

	@Override
	public boolean CanUndo() {
		return undoAbleMove;
	}

}
