package model;

import java.util.ArrayList;
import java.util.Observer;

import controller.GameController;
import interfaces.*;
import view.MainUserInterface;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private MainUserInterface userInterface;
	
	private GameController gameController; 
	private ArrayList<Observer> observer = new ArrayList<Observer>();  
	public Game(String p1, String p2) {
		player1 = new Player(p1);
		player2 = new Player(p2);
		board = new Board();
		userInterface = new MainUserInterface(board, player1, player2);
		gameController = new GameController(userInterface, this);
		userInterface.addGameController(gameController); 
		addObserver(userInterface);
		
	}

	public void addObserver(MainUserInterface mu){
		observer.add(mu);		
	}
	
	public void startGame(){
		
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
		else if(sameCoordinates(currentlySelectedCoordinates, receivedCoordinates)){
			currentlySelectedCoordinates = null; 
			destinationSelectedCoordinates = null; 
		}
		else{
			destinationSelectedCoordinates = receivedCoordinates; 
			calculateMove(); 
		}
	}
	private boolean sameCoordinates(Coordinate cur, Coordinate dest){
			if(cur.x == dest.x && cur.y == dest.y){  
				return true;
			}	
		return false; 
	}
	private void calculateMove() {
		// TODO Auto-generated method stub
		Cell cells[][] = board.getAllCells(); 	
		cells[destinationSelectedCoordinates.getx()][destinationSelectedCoordinates.gety()].setVisible(false);
		for(int i = 0; i < observer.size(); i++){	
			observer.get(i).update(null, cells);
		}
		
	}	
}