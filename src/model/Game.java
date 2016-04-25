package model;

import controller.GameController;
import controller.PlayerController;

public class Game {
	private static Game instance = null;
	private Board board;
	private Coordinate currentlySelected = null;
	private Coordinate destinationSelected = null;
	private boolean done = false;
	private Player turn;
	private GameController gameController; 
	private Move moveDecider;
	
	//Singleton pattern. Makes sure there is only one game object
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	 
	private Game() {
		board = new Board();
	}

	/**
	 * Starts the game. Sets up the board first, then keeps track of whose turn it is.
	 * 
	 * @param player1 - The player 1 object.
	 * @param player2 - The player 2 object.
	 * @param layoutName - The selected board layout to load.
	 * @param timerInt - The timer's starting time as given by the user.
	 * @param boardLayouts - An array of all the boardLayouts as loaded from the Layouts file.
	 */
	public void startGame(Player player1, Player player2, String layoutName, int timerInt, BoardLayout[] boardLayouts) {
		boolean gameRunning = true;
		int sec;
		gameController = new GameController(this, new PlayerController(player1, player2));
		GameTimer timer = new GameTimer(gameController, timerInt);
		moveDecider = new Move(board, gameController);
		
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
	
	public void passCoordinates(Coordinate co) {
		// determine if the coordinates selected are valid or not
		if (currentlySelected == null) {
			currentlySelected = co;
			if (board.getPiece(currentlySelected) != null) {
				//There is a piece
				gameController.updateSelectedPiece(board.getPiece(co));
				if (turn == board.getPiece(currentlySelected).getPlayer()) {
					//If that piece belongs to the current player
					gameController.updateMoves(board.getPiece(currentlySelected).getMoves(currentlySelected));
					moveDecider.setMoveableMoves(board.getPiece(currentlySelected).getMoves(currentlySelected));
				}
			} else {
				currentlySelected = null;
			}
		} else if (sameCoordinates(currentlySelected, co)) {
			currentlySelected = null;
			destinationSelected = null;
			gameController.hideSelected();
			gameController.updateBoard();
		} else {
			destinationSelected = co;
			moveDecider.setCoordinates(currentlySelected, destinationSelected, turn);
			
			currentlySelected = null;
			destinationSelected = null;
			if(moveDecider.determineMove())
				done = true;  
			gameController.hideSelected();
			gameController.updateBoard();
		}
	}
	
	private boolean sameCoordinates(Coordinate co, Coordinate dest){
		if(dest!=null && co.x == dest.x && co.y == dest.y) { 
			return true;
		} else {
			return false; 
		}
	}
	
	//Keep for now
/*	private void turnAllMoveableSquaresOff(){
		for(int i = 0; i < board.getHeight(); i++){
			for(int j = 0; j < board.getWidth(); j++){
				board.getAllCells()[i][j].setCanMoveTo(false);
			}
		}
	}*/	
	
	public void close(){
		gameController.close();
		gameController = null;
		instance = null;
	}

	public Board getBoard() {
		return board;
	}

	public void addPiece(String pieceName) {
		// TODO Implement this so as to allow the user to add new pieces and the cost of points.
	}
}