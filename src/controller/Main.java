package controller;

import model.*;
import view.ViewStart;

import javax.swing.*;

public class Main {
	private static boolean waiting;
	private static BoardLayout boardLayout;
	private static int timer;
	private static BoardLayout[] boardLayouts;
	private static final PlayerController playerController = new PlayerController();
	
	public static void main(String[] args) {

		// macOS fix
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Game game;
		final LoadController loadController = new LoadController();
		ButtonController buttonController = new ButtonController();
		
		boardLayouts = loadController.loadLayouts();
		new ViewStart(boardLayouts, buttonController, loadController);
		waiting = true;
		
		while (waiting) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Cannot put main thread to sleep");
				e.printStackTrace();
			}
		}
		
		game = Game.getInstance();
		game.startGame(playerController, boardLayout, timer, buttonController);
	}

	public static void startGame(String player1Name, String player2Name, BoardLayout selectedBoardLayout, int timerNum) {
		playerController.getPlayer1().setPlayerName(player1Name);
		playerController.getPlayer2().setPlayerName(player2Name);
		boardLayout = selectedBoardLayout;
		timer = timerNum;
		waiting = false;
	}
}
