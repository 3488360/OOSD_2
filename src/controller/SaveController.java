package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Board;
import model.BoardLayout;
import model.Coordinate;
import model.Game;

public class SaveController {
	private Game game;
	private Board board;
	private PlayerController playerController;
	
	public SaveController (Game game, PlayerController playerController) {
		this.game = game;
		this.playerController = playerController;
	}
	
	public boolean saveGame(String saveName, String player1, String player2) {
		BoardLayout save = new BoardLayout(saveName);
		board = game.getBoard();
		Coordinate co;
		FileOutputStream out = null;
		ObjectOutputStream out2 = null;
		
		try {
			out = new FileOutputStream(saveName + ".save");
			out2 = new ObjectOutputStream(out);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				out.close();
			} catch (IOException e2) {
				e2.printStackTrace();
				return false;
			}
			return false;
		}
		
		for (int a = 0; a < board.getWidth(); a++) {
			for (int b = 0; b < board.getHeight(); b++) {
				co = new Coordinate(a, b);
				if (board.getPiece(co) != null) {
					save.addPiece(co, board.getPiece(co));
				}
			}
		}
		
		save.setPlayers(player1, player2);
		save.setPlayer1Points(playerController.getPlayerPoints("player1"));
		save.setPlayer2Points(playerController.getPlayerPoints("player2"));
		save.setCurrentTime(game.getTime());
		save.setTurn(game.getTurn().getName());
		save.setTime(game.getInitTime());
		
		try {
			out2.writeObject(save);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				out2.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
}
