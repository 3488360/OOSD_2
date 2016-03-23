package controller;

import javax.swing.JOptionPane;

import model.Game;
import view.MainUserInterface;

public class Main {

	public static void main(String[] args) {
		Game game;
		String player1;
		String player2;
		
//		player1 = (String) JOptionPane.showInputDialog(null, "Please enter player 1's name:", "Player 1 Name", 3);
//		player2 = (String) JOptionPane.showInputDialog(null, "Please enter player 2's name:", "Player 2 Name", 3);
		player1 = "john";
		player2 = "adi"; 
		game = new Game(player1, player2);
		game.startGame();
		/*GameEngine gameEngine
		 * GUI gui = new GUI(gameEngine);
		Controller controller = new Controller(gui,gameEngine);
		
		gameEngine.addGameEngineCallback(controller);*/
	}

}
