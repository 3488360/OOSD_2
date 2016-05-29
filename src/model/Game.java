package model;

import java.util.Observable;
import java.util.Observer;

import controller.ButtonController;
import controller.CommonCode;
import controller.PlayerController;
import view.AbstractUIFactory;
import view.ViewMain;

public class Game implements Observer {
	private Board board;
	private boolean done = false;
	private String turn;
	private int timerTime = 60;
	private int timerInt;
	private GameTimer timer;
	private PlayerController playerController;
	private ViewMain userInterface;
	private Turn turnTracker;
	
	public Game() {
		playerController = PlayerController.getInstance();
	}

	public void startGame(BoardLayout layout, int timerInt, ButtonController buttonController, AbstractUIFactory uiFactory) {
		board = new Board(layout.getBoardShape());
		boolean gameRunning = true;
		this.timerInt = timerInt;
		
		setupGame(layout);
		
		if (layout.getCurrentTime() == -1)
			timer = new GameTimer(timerInt);
		else
			timer = new GameTimer(timerInt, layout.getCurrentTime());
			
		timer.addObserver(this);
		
		userInterface = new ViewMain(board, playerController, buttonController, timer, uiFactory);

		buttonController.setGameVariables(board, this, userInterface);
		
		board.setupBoard(layout);
		
		turnTracker = new Turn();
		
		if (layout.getTurn() == null) {
			turnTracker = new Turn();
		} else {
			turnTracker = new Turn(layout.getTurn());
		}
		
		userInterface.updateBoard();
		
		while (gameRunning) {
			userInterface.updateTurn(turn);
			timer.start();
			
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
				CommonCode.message("Timer ran out!\n" + playerController.getPlayerName(turnTracker.getTurn()) + "'s turn.");
			}
			
			timer.stop();
			
			timerTime = timerInt;
			
			done = false;
			
			if (checkWinning()) {
				System.out.println("Game has ended!");
				gameRunning = false;
			} else {
				turnTracker.nextTurn();
			}

		}
		timer.deleteObserver(this);
		
	}
	
	private void setupGame(BoardLayout layout) {
		if (layout.getPlayer1Name() != null) {
			playerController.setName("player1", layout.getPlayer1Name());
		}
		
		if (layout.getPlayer2Name() != null) {
			playerController.setName("player2", layout.getPlayer2Name());
		}
		
		if (layout.getPlayer1Points() != -1) {
			playerController.setPoints("player1", layout.getPlayer1Points());
		}
		
		if (layout.getPlayer2Points() != -1) {
			playerController.setPoints("player2", layout.getPlayer2Points());
		}
	}

	private boolean checkWinning() {
		boolean oneFinished = false;
		boolean twoFinished = false; 
		
		if (board.countPiece("King", "player1") < 1 && board.countPiece("Queen", "player1") < 1) {
			oneFinished = true;
			CommonCode.message(playerController.getPlayerName("player2") + " has won!");
		} else if (board.countPiece("King", "player2") < 1 && board.countPiece("Queen", "player2") < 1) {
			twoFinished = true;
			CommonCode.message(playerController.getPlayerName("player1") + " has won!");
		}
		
		System.out.println("oneFinished = " + oneFinished + " twoFinished = " + twoFinished);
		return oneFinished || twoFinished;
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

	public void setDone(boolean done) {
		this.done = done;
	}
	
	public String getTurn() {
		return turnTracker.getTurn();
	}

	public void updatePoints() {
		userInterface.updatePoints();
	}

	public void updateBoard() {
		userInterface.updateBoard();
	}

	public void hideSelected() {
		userInterface.hideSelected();
	}
}