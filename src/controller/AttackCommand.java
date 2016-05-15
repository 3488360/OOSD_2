package controller;

import java.util.List;

import model.*;
import model.pieces.PieceInterface;

public class AttackCommand implements Command {
//	We need a game controller to notify the user client that an attack has failed
	private GameController gameController; 
	
//	A list that contains coordinates of all the possible places the piece can attack to 
	private List<Coordinate> attackingMoves; 
	
// Required for the undo function ... adding health back to piece, adding point back to the player
	int attack; 
	int health; 
	
	private Board board;
	private String currentPlayer; 
	private Coordinate currentlySelected; 
	private Coordinate destinationSelected; 
	private PieceInterface pieceBeingAttacked; 
	
	
	
	public AttackCommand(GameController gameController, Board board, Coordinate currentSelected, Coordinate destinationSelected, String currentPlayer){
		this.gameController = gameController; 
		this.board = board; 
		this.currentlySelected = currentSelected;
		this.destinationSelected = destinationSelected;
		this.currentPlayer = currentPlayer;
	}
	
	
	@Override
	public void execute() {
		if (board.getPiece(destinationSelected).getPlayerName().equals(currentPlayer)) {
			gameController.message("You are trying to attack your own piece!");
		} 
		
		else{
			if(canAttackTo()){
				pieceBeingAttacked = board.getPiece(destinationSelected);
				attack = board.getPiece(currentlySelected).getStrength();
				health = pieceBeingAttacked.getCurrentHealth();
				
				if (health > attack) {
					pieceBeingAttacked.takeDamage(attack);
				}else {
//					player.setPoints(player.getPoints() + (board.getPiece(destinationSelected).getCost() / 4) * 3);
					board.setPiece(destinationSelected,	board.getPiece(currentlySelected));
					board.setPiece(currentlySelected, null);
					gameController.updateBoard();
					gameController.updatePoints();
				}
				gameController.getGame().setDone(true);
			}
			else{
//													IS THIS REDUNDANT?
				gameController.message("This piece cannot be attacked");
			}
		}
//		return false;
	}

	private boolean canAttackTo() {
		List<Coordinate> attackRange = board.getAttackRange(currentlySelected, currentPlayer);
		System.out.println("We want to go to: " + destinationSelected.x + "," + destinationSelected.y);
		if (attackRange != null){
			for(Coordinate lists : attackRange){
				if(lists.x == destinationSelected.x && lists.y == destinationSelected.y)
					return true; 
			}
		}
		return false;
	}

	@Override
	public void undo() {
//		If the piece wasn't destroyed 	
//		add the health lost back to the piece that was attacked 
		
		if(board.getPiece(currentlySelected) == null){
//			if the piece was destroyed instead 
			board.setPiece(currentlySelected, board.getPiece(destinationSelected));
			board.setPiece(destinationSelected, pieceBeingAttacked);			
		}else
		{
			board.getPiece(destinationSelected).takeDamage(-attack); 
		}
		
		gameController.getGame().setDone(true);
		

		
	}

}
