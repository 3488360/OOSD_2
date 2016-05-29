package controller;

import model.*;
import view.AbstractUIFactory;
import view.ViewSelectTheme;
import view.ViewStart;

public class Main {
	private static boolean waiting;
	private static BoardLayout boardLayout;
	private static int timer;
	private static BoardLayout[] boardLayouts;
	private static ViewStart start;
	private static String factory;
	private static ButtonController buttonController;
	private static AbstractUIFactory uiFactory;
	private static final LoadController loadController = new LoadController();
	
	public static void main(String[] args) {
		ViewSelectTheme selectTheme = new ViewSelectTheme();
		selectTheme.setVisible(true);
		waiting = true;
		
		while (waiting) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Cannot put main thread to sleep");
				e.printStackTrace();
			}
		}
		
		buttonController = new ButtonController();
		uiFactory = AbstractUIFactory.getFactory(factory);
		
		boardLayouts = loadController.loadLayouts2();
		start = new ViewStart(boardLayouts, buttonController, uiFactory);
		waiting = true;

		while (waiting) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Cannot put main thread to sleep");
				e.printStackTrace();
			}
		}

		GameController gameController = new GameController();

		gameController.startGame(boardLayout, timer, buttonController, uiFactory);
	}

	public static void start(String factory) {
		Main.factory = factory;
		waiting = false;
		
	}

	public static void startGame(String player1Name, String player2Name, BoardLayout selectedBoardLayout, int timerNum) {
		PlayerController pc = PlayerController.getInstance();
		pc.setName("player1", player1Name);
		pc.setName("player2", player2Name);
		boardLayout = selectedBoardLayout;
		timer = timerNum;
		waiting = false;
	}

	public static void startLayoutEditor() {
		new LayoutEditorController(AbstractUIFactory.getFactory(factory));
	}

	public static void showStart() {
		boardLayouts = loadController.loadLayouts2();
		start.setLayouts(boardLayouts);
		start.setVisible(true);
	}
}
