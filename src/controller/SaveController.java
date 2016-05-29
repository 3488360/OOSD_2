package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Board;
import model.BoardLayout;
import model.Coordinate;
import model.GameController;

public class SaveController {
	private static SaveController instance = null;
	//private Game game;
	//private PlayerController playerController;
	
	public static SaveController getInstance() {
		if (instance == null) {
			instance = new SaveController();
		}
		
		return instance;
	}
	
	private SaveController () {}
	
	public boolean saveGame(File saveFile, String player1, String player2, GameController gameController) {
		PlayerController pc = PlayerController.getInstance();
		Board board = gameController.getBoard();
		BoardLayout save = new BoardLayout(saveFile.getName().replace(".save", ""));;
		Coordinate co;
		FileOutputStream out = null;
		ObjectOutputStream out2 = null;
		
		try {
			out = new FileOutputStream(saveFile.getName().replace(".save", "") + ".save");
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
		save.setPlayer1Points(pc.getPlayerPoints("player1"));
		save.setPlayer2Points(pc.getPlayerPoints("player2"));
		save.setCurrentTime(gameController.getTime());
		save.setTurn(gameController.getTurn());
		save.setTime(gameController.getInitTime());
		save.setBoardShape(board.getBoardShape());
		
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
	
	public boolean saveLayout(File saveFile, Board board) {
		PlayerController pc = PlayerController.getInstance();
		String name = saveFile.getName().replace(".save", "");
		name = name.replace(".layout", "");
		BoardLayout save = new BoardLayout(name);
		Coordinate co;
		FileOutputStream out = null;
		ObjectOutputStream out2 = null;
		
		try {
			out = new FileOutputStream(saveFile.getName().replace(".layout", "") + ".layout");
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
		
		save.setPlayer1Points(pc.getPlayerPoints("player1"));
		save.setPlayer2Points(pc.getPlayerPoints("player2"));
		save.setBoardShape(board.getBoardShape());
		
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
