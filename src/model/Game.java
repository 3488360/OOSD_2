package model;

import controller.GameController;
import controller.PlayerController;

import java.util.List;

public class Game {
	private static Game instance = null;
	private Board board;
	private Coordinate currentlySelected = null;
	private Coordinate destinationSelected = null;
	private boolean done = false;
	private Player turn;
	private GameController gameController; 
	
	//Singleton pattern. Make sure there is only one game object
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		
		return instance;
	}
	 
	private Game() {
		board = new Board();
	}

	public void startGame(Player player1, Player player2, String layoutName, int timerInt, BoardLayout[] boardLayouts) {
		boolean gameRunning = true;
		int sec;
		gameController = new GameController(this, new PlayerController(player1, player2));
		GameTimer timer = new GameTimer(gameController, timerInt);
		
		setupBoard(boardLayouts, layoutName);
		turn = player1;
		
		gameController.updateBoard();
		
		while (gameRunning) {
			gameController.updateTurn(turn);
			timer.start();
			
			while(!done) {
				try {
					Thread.sleep(500);
					sec = timer.checkTime();
					if (sec == 0) {
						done = true;
					} else {
						gameController.updateTimer(sec);
					}
				} catch (InterruptedException e) {
					System.out.println("Can't put main thread to sleep");
					e.printStackTrace();
				}
			}
			
			if (turn == player1) {
				turn = player2;
			} else {
				turn = player1;
			}
			
			if (timer.checkTime() == 0) {
				System.out.println("Timer ran out");
				gameController.message("Timer ran out!\n" + turn.getPlayerName() + "'s turn.");
			}
			
			timer.stop();
			
			done = false;
			
			if (checkWinning()) {
				gameRunning = false;
			}
		}
	}
	
	private void setupBoard(BoardLayout[] boardLayouts, String layoutName) {
		int b;
		Coordinate co;
		
		for (b = 0; b < boardLayouts.length; b++) {
			if (layoutName.equals(boardLayouts[b].getName())) {
				break;
			}
		}
		
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				co = new Coordinate(i, a);
				if (boardLayouts[b].getPiece(co) != null) {
					board.setPiece(co, boardLayouts[b].getPiece(co));
				}
			}
		}
	}

	private boolean checkWinning() {
		// TODO Implement this to check for winning conditions
		return false;
	}
	
	public void passCoordinates(Coordinate coor){
//		determine if the coordinates selected are valid or not 
		if(currentlySelected == null){ 
			if (board.getPiece(coor)!= null && turn == board.getPiece(coor).getPlayer()){
				currentlySelected = coor;
				gameController.updateSelectedPiece(board.getPiece(currentlySelected));
//			display all the places the piece can possibly move to
				List<Coordinate> co = board.getPiece(currentlySelected).getMoves(currentlySelected);
				Cell[][] cell = board.getAllCells(); 
				for(int i = 0; i < co.size(); i++){
					if(co.get(i).x < board.getWidth() && co.get(i).x >= 0 && co.get(i).y < board.getHeight() && co.get(i).y>= 0){
						if(board.getAllCells()[co.get(i).x][co.get(i).y].getVisible() == true){
							cell[co.get(i).x][co.get(i).y].setCanMoveTo(true);
						}
					}
				}
				gameController.updateBoard();
			} else {
				System.out.println("There is no piece located here or this piece is not yours");
			}
		}
		
		else if(sameCoordinates(currentlySelected, coor)){
			currentlySelected = null; 
			destinationSelected = null; 
			gameController.hideSelected();
			turnAllMoveableSquaresOff();
		}
		else{
			destinationSelected = coor; 
			calculateMove(); 
			currentlySelected = null;
			destinationSelected = null;
			gameController.hideSelected();
			turnAllMoveableSquaresOff();

		}
		
		gameController.updateBoard();
	}
	
	private boolean sameCoordinates(Coordinate cur, Coordinate dest){
		if(dest!=null && cur.x == dest.x && cur.y == dest.y) { 
			return true;
		} else {
			return false; 
		}
	}
	private void turnAllMoveableSquaresOff(){
		for(int i = 0; i < board.getHeight(); i++){
			for(int j = 0; j < board.getWidth(); j++){
				board.getAllCells()[i][j].setCanMoveTo(false);
			}
		}
	}	

	private void calculateMove() {
		int attack;
		int health;
		
		if (board.getPiece(currentlySelected) != null) {
			if (board.getPiece(destinationSelected) != null) {
				//If it's an attacking move
				System.out.println("Attacking!");
				if (board.getPiece(destinationSelected).getPlayer().equals(turn)) {
					gameController.message("You are trying to attack your own piece!");
				} else {
					attack = board.getPiece(currentlySelected).getStrength();
					health = board.getPiece(destinationSelected).getCurrentHealth();
					
					if (health > attack) {
						board.getPiece(destinationSelected).takeDamage(attack);
					} else {
						turn.setPoints(turn.getPoints() + (board.getPiece(destinationSelected).getCost()/4)*3);
						board.setPiece(destinationSelected, board.getPiece(currentlySelected));
						board.setPiece(currentlySelected, null);
						System.out.println("Successful move!");
						gameController.updateBoard();
						gameController.updatePoints();
					}
					System.out.println("Successful attack!");
					currentlySelected = null;
					destinationSelected = null;
					done = true;
				}
				
			} else {
				//If it's moving the piece
				if (turn.equals(board.getPlayer(currentlySelected))) {
					System.out.println("Moving!");
//					moves = board.getMovement(board.getPiece(currentlySelected), currentlySelected);
					if(board.getAllCells()[destinationSelected.x][destinationSelected.y].canMoveTo == true){
						board.setPiece(destinationSelected, board.getPiece(currentlySelected));
						board.setPiece(currentlySelected, null);
						gameController.updateBoard();
						currentlySelected = null;
						destinationSelected = null;
						done = true;
					}
				} else System.out.println("That is not " + turn.getPlayerName() + "'s piece!");
			}
		} else {
			//If no piece is under first selection
			currentlySelected = null;
			destinationSelected = null;
		}
	}
	
	public void close(){
		gameController.close();
		gameController = null;
	}

	public Board getBoard() {
		return board;
	}

	public void addPiece(String pieceName) {
		// TODO Implement this so as to allow the user to add new pieces and the cost of points.
	}
}