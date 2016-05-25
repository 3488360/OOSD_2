package model;

import java.util.Observable;
import java.util.Observer;

import controller.ButtonController;
import controller.GameController;
import controller.PlayerController;
import controller.SaveController;
import model.pieces.ArcherPiece;
import model.pieces.GodPiece;
import model.pieces.HealerPiece;
import model.pieces.KingPiece;
import model.pieces.PieceInterface;
import model.pieces.QueenPiece;
import model.pieces.SoldierPiece;
import model.pieces.TankPiece;
import model.pieces.TestPiece;
import model.pieces.WizardPiece;

public class Game implements Observer {
	private static Game instance = null;
	private Board board;
	private boolean done = false;
	private Player turn;
	private GameController gameController; 
	private int timerTime = 60;
	private int timerInt;
	private PlayerController playerController;
	private GameTimer timer;
	
	//Singleton pattern. Makes sure there is only one game object
	public static Game getInstance(BoardLayout boardLayout) {
		if (instance == null) {
			instance = new Game(boardLayout);
		}
		return instance;
	}
	
	private Game(BoardLayout boardLayout) {
		board = new Board(boardLayout.getBoardShape());
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
	public void startGame(PlayerController playerController, BoardLayout layout, int timerInt, ButtonController buttonController) {
		boolean gameRunning = true;
		this.timerInt = timerInt;
		Move moveDecider;
		Player player1 = playerController.getPlayer1();
		Player player2 = playerController.getPlayer2();
		this.playerController = playerController;
		setupGame(layout, player2, player2);
		
		if (layout.getCurrentTime() == -1)
			timer = new GameTimer(timerInt);
		else
			timer = new GameTimer(timerInt, layout.getCurrentTime());
			
		timer.addObserver(this);
		gameController = new GameController(this, playerController, buttonController, timer);
		moveDecider = new Move(board, gameController);
		buttonController.setGameVariables(board, this, gameController, moveDecider, gameController.getInterface(), new SaveController(this, playerController));
		
		setupBoard(layout);
		
		if (layout.getTurn() == null) {
			turn = player1;
		} else {
			if (layout.getTurn().equals("player1"))
				turn = player1;
			else
				turn = player2;
		}
		
		gameController.updateBoard();
		
		while (gameRunning) {
			gameController.updateTurn(turn);
			timer.start();
			
			//System.out.println("It is " + turn.getName() + "'s turn!");
			
			while(!done) {
				try {
					Thread.sleep(500);
					if (timerTime == 0) {
						done = true;
					}
				} catch (InterruptedException e) {
					System.out.println("Can't put main thread to sleep");
					e.printStackTrace();
				}
			}
			
			if (timerTime == 0) {
				System.out.println("Timer ran out");
				gameController.message("Timer ran out!\n" + turn.getPlayerName() + "'s turn.");
			}
			
			if (turn.equals(player1)) {
				turn = player2;
			} else {
				turn = player1;
			}
			
			timer.stop();
			
			timerTime = timerInt;
			
			done = false;
			
			if (checkWinning()) {
				gameRunning = false;
			}
		}
		timer.deleteObserver(this);
	}
	
	private void setupGame(BoardLayout layout, Player player1, Player player2) {
		if (layout.getPlayer1Name() != null) {
			player1.setPlayerName(layout.getPlayer1Name());
		}
		
		if (layout.getPlayer2Name() != null) {
			player2.setPlayerName(layout.getPlayer2Name());
		}
		
		if (layout.getPlayer1Points() != -1) {
			player1.setPoints(layout.getPlayer1Points());
		}
		
		if (layout.getPlayer2Points() != -1) {
			player2.setPoints(layout.getPlayer2Points());
		}
	}
	
	private void setupBoard(BoardLayout layout) {
		Coordinate co;
		
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				co = new Coordinate(i, a);
				if (layout.getPiece(co) != null) {
					board.setPiece(co, layout.getPiece(co));
				}
			}
		}
	}

	private boolean checkWinning() {
		// TODO Implement this to check for winning conditions
		return false;
	}
	
	public void pause() {
		timer.pause();
	}
	
	public void resume() {
		timer.resume();
	}

	public Board getBoard() {
		return board;
	}

	public void addPiece(String pieceName, Coordinate co) {
		PieceInterface p = getPiece(pieceName, turn.getName());
		if (turn.getPoints() >= p.getCost()) {
			board.setPiece(co, p);
			turn.setPoints(turn.getPoints() - p.getCost());
			gameController.updatePoints();
		} else {
			gameController.message("Not enough points to buy that piece.");
		}
	}

	@Override
	public void update(Observable timer, Object arg) {
		timerTime = (int) arg;
	}
	
	public int getTime() {
		return timerTime;
	}
	
	public int getInitTime() {
		return timerInt;
	}
	
	private PieceInterface getPiece(String name, String p) {
		switch (name) {
			case "King": return new KingPiece(p);
			
			case "Archer": return new ArcherPiece(p);
			
			case "God": return new GodPiece(p);
			
			case "Healer": return new HealerPiece(p);
			
			case "Queen": return new QueenPiece(p);
			
			case "Soldier": return new SoldierPiece(p);
			
			case "Tank": return new TankPiece(p);
			
			case "Test": return new TestPiece(p);
			
			case "Wizard": return new WizardPiece(p);
		}
	
		return null;
	}

	public String getTurn() {
		return turn.getName();
	}

	public Player getPlayer1() {
		return playerController.getPlayer1();
	}
	
	public Player getPlayer2() {
		return playerController.getPlayer2();
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}