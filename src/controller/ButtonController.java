package controller;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.*;
import model.Board;
import model.Coordinate;
import model.GameController;
import view.ViewMain;

public class ButtonController implements ButtonControllerInterface {
	private Board board;
	private GameController gameController;
	private Coordinate currentlySelected;
	private String pieceName = null;
	private boolean addPiece;
	private ViewMain userInterface;
	private boolean isPaused = false;
	private CommandManager cmg;
//	private PlayerController playerController = PlayerController.getInstance();
	
	public void setGameVariables(Board b, GameController g, ViewMain userInterface) {
		this.board = b;
		this.gameController = g;
		this.userInterface = userInterface;
		
		cmg = new CommandManager(gameController);
		cmg.addPlayer("player1");
		cmg.addPlayer("player2");
	}
	
	public void passCoordinates(Coordinate co) {
		if (addPiece) {
			//Placing a new piece onto the board
			if (board.getPiece(co) == null) {
				cmg.executeCommand(new AddPieceCommand(gameController, pieceName, co));
				addPiece = false;
				userInterface.updateBoard();
			}
		} else {
			// Determine if the coordinates selected are valid or not
				
			if (currentlySelected == null) {
				currentlySelected = co;
				displayClickableLocations(currentlySelected); 
			}	
			else if(sameCoordinates(currentlySelected, co)) {
				resetBoardDisplays();
			}
			else {
				calculateMove(co);
				resetBoardDisplays(); 
			}
		}
	}
	
	private void resetBoardDisplays(){
		currentlySelected = null;
		userInterface.hideSelected();
		userInterface.updateBoard();
	}
	
	private void displayClickableLocations(Coordinate co) {
		if (board.getPiece(currentlySelected) != null) {
			// There is a piece
			userInterface.updateSelectedPiece(board.getPiece(co));
			if (gameController.getTurn().equals(board.getPiece(currentlySelected).getPlayerName())) {
				// If that piece belongs to the current player
				userInterface.updateMoves(board.getMovement(co, gameController.getTurn()));
				userInterface.updateAttackRange(board.getAttackRange(co, gameController.getTurn()));
			}
		} else {
			currentlySelected = null;
		}
	}
	

	private void calculateMove(Coordinate co){
		if (board.getPiece(currentlySelected) != null) {
			if (board.getPiece(co) != null) {
				if (board.getPiece(currentlySelected).getName().equals("Healer")){
					cmg.executeCommand(new HealerCommand(currentlySelected, co, gameController, board));
				}
				else{
					cmg.executeCommand(new AttackCommand(gameController, board, currentlySelected, co, gameController.getTurn()));
				}
			} else {
				cmg.executeCommand(new MoveCommand(gameController, gameController.getTurn(), board, currentlySelected, co));
			}
		}
	}
	
	public void undo() {
		cmg.undo();
		userInterface.updateBoard();
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
			gameController.resume();
			userInterface.resume();
		} else {
			isPaused = true;
			gameController.pause();
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

	public GameController getGame() {
		return gameController;
	}

	public void saveGame(String player1, String player2) {
		pause();
		
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save Files", "save", "save");
		fc.setFileFilter(filter);
		
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (!SaveController.getInstance().saveGame(fc.getSelectedFile(), player1, player2, gameController)) {
				JOptionPane.showMessageDialog(null, "An error occured when trying to save the file. Please see console for details.", "Error", 0);
				return;
			}
		}

		pause();
	}
}
