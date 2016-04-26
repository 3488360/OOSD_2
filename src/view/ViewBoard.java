package view;

import javax.swing.*;

import controller.BoardController;
import controller.GameController;
import model.Coordinate;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Creates the board in the centre of the interface that the user can interact with.
 * Is responsible for displaying where pieces are located and shows where they can move/attack.
 */
public class ViewBoard extends JPanel {
	private static final long serialVersionUID = 8695643799420470531L;
	
	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private ViewCell grid[][];
	private GameController gameController;
	private ViewPiece viewPiece;
	private BoardController boardController;
	
	public ViewBoard(GameController gameController, BoardController boardCont) {
		viewPiece = new ViewPiece();
		this.gameController = gameController;
		this.boardController = boardCont;
		grid = new ViewCell[boardController.getHeight()][boardController.getWidth()];
		
		ActionListener gridButton = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e);
			}
		};
		
		for (int i = 0; i < boardController.getWidth(); i++) {
			for (int a = 0; a < boardController.getHeight(); a++) {
				grid[i][a] = new ViewCell(boardController.getAllCells()[i][a].getCol(), boardController.getAllCells()[i][a].getRow(), boardController.getAllCells()[i][a].getVisible());
				grid[i][a].addActionListener(gridButton);
			}
		}
		
		setLayout(new GridLayout(15, 15));
		
		//Adds JButtons or JLabels
		for (int i = 0; i < boardController.getWidth(); i++) {
			for (int a = 0; a < boardController.getHeight(); a++) {
				if (grid[i][a].getVisible() == true){
					grid[i][a].setBackground(Color.ORANGE);
					grid[i][a].setForeground(Color.BLACK);
					grid[i][a].setBounds(grid[i][a].getRow(), grid[i][a].getCol(), CELLWIDTH, CELLHEIGHT);
					add(grid[i][a]);
				} else {
					add(new JLabel());
				}
			}
		}
	}
	
	public void updateBoard () {
		Coordinate co;
		
		for (int i = 0; i < boardController.getWidth(); i++) {
			for (int a = 0; a < boardController.getHeight(); a++) {
				grid[i][a].setCanMoveTo(boardController.getAllCells()[i][a].getCanMoveTo());
			}
		}
		
		//Colour key:
		//		Orange - Background colour
		//		Yellow - Player 1 pieces
		//		Grey - Player 2 pieces
		//		Green - Cells that the currently selected piece can move to
		//		Red - Cells that the currently selected piece can attack	<--- To be implemented
		// 		White - Currently selected cell
		
		for (int i = 0; i < boardController.getWidth(); i++) {
			for (int a = 0; a < boardController.getHeight(); a++) {
				if (grid[i][a].getVisible() == true){
					co = new Coordinate(i, a);
					if (boardController.getPiece(co)) {
						if (boardController.getPiecePlayerColor(co).equals("player1"))
							grid[i][a].setBackground(Color.YELLOW);
						else
							grid[i][a].setBackground(Color.GRAY);
						grid[i][a].setIcon(viewPiece.getIcon(boardController.getPieceName(co), boardController.getPieceIcon(co)));
					} else {
						grid[i][a].setBackground(Color.ORANGE);
						grid[i][a].setIcon(null);
					}
					if(grid[i][a].canMoveTo == true){
						grid[i][a].setBackground(Color.GREEN);
					}
				}
			}
		}
	}
	
	private void buttonPressed(ActionEvent e) {
		ViewCell button = (ViewCell)e.getSource();
		if (button.getBackground() != Color.WHITE)
			button.setBackground(Color.WHITE);
		else
			button.setBackground(Color.ORANGE);

		gameController.passCoordinates(new Coordinate(button.getCol(), button.getRow())); 
	}

	public void updateCells(List<Coordinate> list, List<Coordinate> attackRange, Coordinate currentlySelected){
		// turns all the cells the piece can move to green and all sqaures the piece can attack to red
		for(Coordinate moveableCoordinates : list) {
			 if(withinBoardDimensions(moveableCoordinates)){ 
				 grid[moveableCoordinates.x][moveableCoordinates.y].setCanMoveTo(true);
				 grid[moveableCoordinates.x][moveableCoordinates.y].setBackground(Color.GREEN);
			 }
		}
		for(Coordinate attackCoordinates : attackRange){
			if(withinBoardDimensions(attackCoordinates)){
				if(grid[attackCoordinates.x][attackCoordinates.y].getCanMoveTo()){
					if(boardController.getPiece(attackCoordinates)){
						if(!(boardController.getPlayer(attackCoordinates).equals(boardController.getPlayer(currentlySelected)))){
							grid[attackCoordinates.x][attackCoordinates.y].setBackground(Color.RED); 	
						}
					}	
				}
			}	
		}
	}	
			
	
	
	private boolean withinBoardDimensions(Coordinate moveableCoordinates){
		if(moveableCoordinates.x < boardController.getWidth()
				&& moveableCoordinates.x >= 0
				&& moveableCoordinates.y < boardController.getHeight()
				&& moveableCoordinates.y >= 0){
				return true;
		}		
		return false;		
	}
		
		
	
}
