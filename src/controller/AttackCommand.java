package controller;

import java.util.List;

import model.*;

public class AttackCommand implements Command {
//	We need a game controller to notify the user client that an attack has failed
	private GameController gameController; 
	
//	A list that contains coordinates of all the possible places the piece can attack to 
	private List<Coordinate> attackingMoves; 
	
// Required for the undo function ... adding health back to piece, adding point back to the player
	int attack; 
	int health; 
	
	private Board board;
	private Player currentPlayer; 
	private Coordinate currentlySelected; 
	private Coordinate destinationSelected; 
	
	
	
	
	public AttackCommand(GameController gameController, Board board){
		this.gameController = gameController; 
		this.board = board; 
	}
	
	
	@Override
	public void execute() {
		if (board.getPiece(destinationSelected).getPlayerName() == currentPlayer.getName()) {
			gameController.message("You are trying to attack your own piece!");
//			return false;
		} 
		else{
			if(canAttackTo()){
				attack = board.getPiece(currentlySelected).getStrength();
				health = board.getPiece(destinationSelected).getCurrentHealth();
				
				if (health > attack) {
					board.getPiece(destinationSelected).takeDamage(attack);
				} else {
//					player.setPoints(player.getPoints() + (board.getPiece(destinationSelected).getCost() / 4) * 3);
					board.setPiece(destinationSelected,	board.getPiece(currentlySelected));
					board.setPiece(currentlySelected, null);
					gameController.updateBoard();
					gameController.updatePoints();
				}
//				return true; 
			}
			else{
//													IS THIS REDUNDANT?
				gameController.message("This piece cannot be attacked");
			}
		}
//		return false;
	}

	private boolean canAttackTo() {
		List<Coordinate> attackRange = board.getAttackRange(currentlySelected, currentPlayer.getName());
		if (attackingMoves != null){
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
		board.getPiece(destinationSelected).takeDamage(-attack); 
		
		
//		if the piece was destroyed instead 
		board.setPiece(currentlySelected, board.getPiece(destinationSelected));
		board.setPiece(destinationSelected, null);
		
	}

}
