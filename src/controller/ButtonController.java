package controller;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Board;
import model.Coordinate;
import model.Game;
import model.Move;
import view.ViewMain;

public class ButtonController implements ButtonControllerInterface {
	private Board board;
	private Game game;
	private Coordinate currentlySelected;
	private String pieceName = null;
	private boolean addPiece;
	private GameController gameController;
	private Move moveDecider;
	private ViewMain userInterface;
	private boolean isPaused = false;
	private SaveController saveController;
	
	public void setGameVariables(Board b, Game g, GameController gc, Move m, ViewMain userInterface, SaveController saveController) {
		this.board = b;
		this.game = g;
		this.gameController = gc;
		this.moveDecider = m;
		this.userInterface = userInterface;
		this.saveController = saveController;
	}
	
	public void passCoordinates(Coordinate co) {
		if (addPiece) {
			//Placing a new piece onto the board
			if (board.getPiece(co) == null) {
				game.addPiece(pieceName, co); //Call factory to create a new piece
				addPiece = false;
				gameController.updateBoard();
			}
		} else {
			//Determine if the coordinates selected are valid or not
			if (currentlySelected == null) {
				currentlySelected = co;
				if (board.getPiece(currentlySelected) != null) {
					//There is a piece
					gameController.updateSelectedPiece(board.getPiece(co));
					if (game.getTurn().equals(board.getPiece(currentlySelected).getPlayerName())) {
						//If that piece belongs to the current player
						gameController.updateMoves(board.getMovement(co, game.getTurn()));
						gameController.updateAttackRange(board.getAttackRange(co, game.getTurn()));
						moveDecider.setMoveableMoves(board.getPiece(currentlySelected).getMoves(currentlySelected));
					}
				} else {
					currentlySelected = null;
				}
			} else if (sameCoordinates(currentlySelected, co)) {
				currentlySelected = null;
				gameController.hideSelected();
				gameController.updateBoard();
			} else {
				moveDecider.setCoordinates(currentlySelected, co, game.getTurn());
				
				currentlySelected = null;
				if(moveDecider.determineMove())
					game.setDone(true);  
				gameController.hideSelected();
				gameController.updateBoard();
			}
		}
	}
	
	private boolean sameCoordinates(Coordinate co, Coordinate dest){
		if(dest!=null && co.x == dest.x && co.y == dest.y) { 
			return true;
		} else {
			return false; 
		}
	}
	
	public void pause() {
		if (isPaused) {
			isPaused = false;
			game.resume();
			userInterface.resume();
		} else {
			isPaused = true;
			game.pause();
			userInterface.pause();		
		}
	}
	
	public void addPiece(String pieceName) {
		if (addPiece) {
			addPiece = false;
			this.pieceName = null;
			return;
		}
		
		addPiece = true;
		this.pieceName = pieceName;
	}
	
	public void exit() {
		int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit?", "Warning", 0);
		
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);	
		}
	}

	public Game getGame() {
		return game;
	}

	public void saveGame(String player1, String player2) {
		pause();
		
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save Files", "save", "save");
		fc.setFileFilter(filter);
		
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (!saveController.saveGame(fc.getSelectedFile(), player1, player2)) {
				JOptionPane.showMessageDialog(null, "An error occured when trying to save the file. Please see console for details.", "Error", 0);
				return;
			}
		}

		pause();
		
	}
}
