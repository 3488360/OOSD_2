package controller;

import model.*;
import view.ViewStart;

public class Main {
	private static boolean waiting;
	private static BoardLayout boardLayout;
	private static int timer;
	private static BoardLayout[] boardLayouts;
	private static final PlayerController playerController = new PlayerController();
	private static ViewStart start;
	private static LoadController loadController = new LoadController();
	
	public static void main(String[] args) {
		ButtonController buttonController = new ButtonController();
		
		boardLayouts = loadController.loadLayouts2();
		start = new ViewStart(boardLayouts, buttonController, loadController);
		waiting = true;
		
		while (waiting) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Cannot put main thread to sleep");
				e.printStackTrace();
			}
		}
		
		Game game = Game.getInstance(boardLayout);
		game.startGame(playerController, boardLayout, timer, buttonController);
	}

	public static void startGame(String player1Name, String player2Name, BoardLayout selectedBoardLayout, int timerNum) {
		playerController.getPlayer1().setPlayerName(player1Name);
		playerController.getPlayer2().setPlayerName(player2Name);
		boardLayout = selectedBoardLayout;
		timer = timerNum;
		waiting = false;
	}

	public static void startLayoutEditor() {
		new LayoutEditorController();
	}

	public static void showStart() {
		boardLayouts = loadController.loadLayouts2();
		start.setLayouts(boardLayouts);
		start.setVisible(true);
	}
}
