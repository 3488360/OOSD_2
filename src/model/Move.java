package model;

import java.util.List;

import controller.GameController;
import model.pieces.PieceInterface;

public class Move {
	private Coordinate currentlySelected; 
	private Coordinate destinationSelected; 
	private Board board;
	private String player; 
	private List<Coordinate> list;
	private GameController gameController; 
	
	public Move(Board board, GameController gc){
		this.board = board; 
		gameController = gc; 
	}
	
	public void setCoordinates(Coordinate current, Coordinate destination, String currentPlayer){
		currentlySelected = current;
		destinationSelected = destination;
		player = currentPlayer;
	}
	
	public boolean determineMove(){
		if (board.getPiece(currentlySelected) != null) {
			if (board.getPiece(destinationSelected) != null) {
				if (board.getPiece(currentlySelected).getName().equals("Healer")) 
					return healingMove();
				else
					return attackMove();
			}
			return normalMove();
		}
//		return command.execute()		

		return false;
	}
	
	public void setMoveableMoves(List<Coordinate> list){
		this.list = list;
	}
	
	private boolean canMoveTo(){
//		if the destination cell is in the list of movable cells
		if (list != null){
			for(Coordinate lists : list){
				if(lists.x == destinationSelected.x && lists.y == destinationSelected.y)
					return true; 
			}
		}
		return false;
	}
	
	private boolean Attack() {
		List<Coordinate> attackRange = board.getAttackRange(currentlySelected, player);
		if (list != null){
			for(Coordinate lists : attackRange){
				if(lists.x == destinationSelected.x && lists.y == destinationSelected.y)
					return true; 
			}
		}
		return false;
	}
	
	private boolean attackMove(){
		if (board.getPiece(destinationSelected).getPlayerName().equals(player)) {
			gameController.message("You are trying to attack your own piece!");
			return false;
		} 
		else{
			if(Attack()){
				int attack;
				int health;
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
				return true; 
			}
		}
		return false;
	}
	
	private boolean normalMove(){
		if (player.equals(board.getPlayer(currentlySelected))){
			if(canMoveTo()){
				board.setPiece(destinationSelected,board.getPiece(currentlySelected));
				board.setPiece(currentlySelected, null);
				gameController.updateBoard();
				currentlySelected = null;
				destinationSelected = null;
				return true;
			}	
		}
		return false;
	}
	
	private boolean healingMove() {
		PieceInterface piece = board.getPiece(destinationSelected);
		
		if (board.getPlayer(currentlySelected).equals(piece.getPlayerName())) {
			if ((piece.getCurrentHealth() - board.getPiece(currentlySelected).getStrength()) > piece.getMaxHealth()) {
				piece.takeDamage(board.getPiece(currentlySelected).getStrength());
			} else if (piece.getCurrentHealth() == piece.getMaxHealth()) {
				return false;
			} else {
				piece.takeDamage(piece.getMaxHealth() - piece.getCurrentHealth());
			}
			
			return true;
		}
		
		return false;
	}
	
}