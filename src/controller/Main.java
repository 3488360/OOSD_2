package controller;

import model.Game;

public class Main {
	static Game game;
	
	public static void main(String[] args) {
		game = new Game();
		game.startGame();
		/*GameEngine gameEngine
		 * GUI gui = new GUI(gameEngine);
		Controller controller = new Controller(gui,gameEngine);
		
		gameEngine.addGameEngineCallback(controller);*/
	}
	
	public static void restartGame() {
		game.close();
		game = null;
		System.gc();
		game = new Game();
		game.startGame();
	}
}
