package model;

import controller.GameController;
import interfaces.*;
import view.MainUserInterface;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private MainUserInterface userInterface;
	
	private GameController gameController; 
	 
	public Game(String p1, String p2) {
		player1 = new Player(p1);
		player2 = new Player(p2);
		board = new Board();
		userInterface = new MainUserInterface(board, player1, player2);
		
		gameController = new GameController(userInterface, this);
		userInterface.addGameController(gameController); 
	}

	public void startGame(){
		Cell[][] cell = board.getAllCells();
		
		cell[7][7].setVisible(false);
		
		gameController.update();
		
	}
	
	public void movePiece(Piece piece, Coordinate a){
//	make this boolean in case it is a invalid move?
//	or have validation beforehand and if valid than call this method	
	}
	
	
	public void addController(GameController gc){
		gameController = gc; 
	}
	Coordinate currentlySelectedCoordinates = null;
	Coordinate destinationSelectedCoordinates = null;
	public void passCoordinates(Coordinate coor) {
		// TODO Auto-generated method stub
		Coordinate receivedCoordinates = coor;
		
		if(currentlySelectedCoordinates == null){
			currentlySelectedCoordinates = receivedCoordinates; 
		}
		else if(sameCoordinates(currentlySelectedCoordinates, destinationSelectedCoordinates)){
			currentlySelectedCoordinates = null; 
			destinationSelectedCoordinates = null; 
		}
		else{
			destinationSelectedCoordinates = receivedCoordinates; 
			calculateMove(); 
		}
	
		

	}
	private boolean sameCoordinates(Coordinate cur, Coordinate dest){
		if(dest!=null){
			if(cur.x == dest.x && cur.y == dest.y)  
				return true;
		}
		return false; 
	}
	private void calculateMove() {
		// TODO Auto-generated method stub
		System.out.println(currentlySelectedCoordinates.x + "," + currentlySelectedCoordinates.y +"-"+ destinationSelectedCoordinates.x+","+destinationSelectedCoordinates.y);
		
	}

	
	
	
	
	
}
