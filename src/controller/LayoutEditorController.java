package controller;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Board;
import model.BoardShape;
import model.Coordinate;
import model.GameController;
import model.pieces.ArcherPiece;
import model.pieces.GodPiece;
import model.pieces.HealerPiece;
import model.pieces.KingPiece;
import model.pieces.PieceInterface;
import model.pieces.QueenPiece;
import model.pieces.SoldierPiece;
import model.pieces.TankPiece;
import model.pieces.TestPiece;
import model.pieces.WizardPiece;
import view.AbstractUIFactory;
import view.ViewBoardEditor;
import view.ViewLayoutEditor;

public class LayoutEditorController implements ButtonControllerInterface {
	private Board board;
	private boolean addPiece;
	private String pieceName;
	private boolean playerSelected;
	private String playerName;
	private ViewLayoutEditor userInterface;
	private ViewBoardEditor boardEditor;
	private SaveController saveController;
	private PlayerController pc;
	private AbstractUIFactory uiFactory;
	
	public LayoutEditorController(AbstractUIFactory uiFactory) {
		pc = PlayerController.getInstance();
		this.uiFactory = uiFactory;
		this.saveController = SaveController.getInstance();
		boardEditor = new ViewBoardEditor(this, uiFactory);
	}
	
	public void setGameVariables(Board board, ViewLayoutEditor userInterface, SaveController saveController) {
		if (board != null)
			this.board = board;
		
		if (userInterface != null)
			this.userInterface = userInterface;
		
		if (saveController != null)
			this.saveController = saveController;
	}

	public void passCoordinates(Coordinate co) {
		//System.out.println(co.x + " and " + co.y + " was selected");
		if (addPiece && playerSelected) {
			if (board.getPiece(co) != null && playerName.equals("Delete")) {
				board.setPiece(co, null);
			} else {
				PieceInterface p = getPiece(pieceName, playerName);
				board.setPiece(co, p);
			}
			userInterface.updateBoard();
		}
	}
	
	private PieceInterface getPiece(String name, String p) {
		switch (name) {
			case "King": return new KingPiece(p);
			
			case "Archer": return new ArcherPiece(p);
			
			case "God": return new GodPiece(p);
			
			case "Healer": return new HealerPiece(p);
			
			case "Queen": return new QueenPiece(p);
			
			case "Soldier": return new SoldierPiece(p);
			
			case "Tank": return new TankPiece(p);
			
			case "Test": return new TestPiece(p);
			
			case "Wizard": return new WizardPiece(p);
		}
	
		return null;
	}

	@Override
	public void pause() {
		//Not needed in layout editor
	}

	@Override
	public void addPiece(String pieceName) {
		addPiece = true;
		this.pieceName = pieceName;
		userInterface.pieceSelected(pieceName);
	}

	public void exit() {
		if (userInterface != null) {
			userInterface.dispose();
			userInterface = null;
		} else {
			boardEditor.dispose();
			boardEditor = null;
		}
		Main.showStart();
	}

	public GameController getGame() {
		return null;
	}

	public void playerSelected(String playerName) {
		if (playerName.equals("Player 1")) {
			playerSelected = true;
			this.playerName = "player1";
			userInterface.updateJButtons("Player 1");
		} else if (playerName.equals("Player 2")) {
			playerSelected = true;
			this.playerName = "player2";
			userInterface.updateJButtons("Player 2");
		} else {
			this.playerName = null;
			playerSelected = false;
		}
		//System.out.println("Player selected is " + this.playerName);
	}

	public void saveGame(String player1, String player2) {
		boolean team1King = false;
		boolean team1Queen = false;
		boolean team2King = false;
		boolean team2Queen = false;
		
		if (board.countPiece("King", "player1") > 0) {
			team1King = true;
		}
		
		if (board.countPiece("Queen", "player1") > 0) {
			team1Queen = true;
		}
		
		if (board.countPiece("King", "player2") > 0) {
			team2King = true;
		}
		
		if (board.countPiece("Queen", "player2") > 0) {
			team2Queen = true;
		}
		
		//System.out.println("team1King = " + team1King + "   team1Queen = " + team1Queen + "   team2King = " + team2King + "   team2Queen = " + team2Queen);
		
		if (!((team1King && team2Queen) || (team1Queen && team2King))) {
			JOptionPane.showMessageDialog(null, "There must be at least one King on one side and one Queen on the other.", "Alert", 3);
			return;
		}
		
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir")/* + "/layouts"*/);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Layout Files", "layout", "layout");
		fc.setFileFilter(filter);
		
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (!saveController.saveLayout(fc.getSelectedFile(), board)) {
				JOptionPane.showMessageDialog(null, "An error occured when trying to save the file. Please see console for details.", "Error", 0);
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Layout saved successfully!", "Alert", 3);
			}
		}
	}

	public void next(String shape, int x, int y) {
		boardEditor.dispose();
		boardEditor = null;
		
		if (shape.equals("Circle")) {
			userInterface = new ViewLayoutEditor(new BoardShape("Circle", x), this, pc, uiFactory);
		} else {
			userInterface = new ViewLayoutEditor(new BoardShape(shape, y, x), this, pc, uiFactory);
		}
	}

	@Override
	public void undo() {
		//Not needed
	}
}
