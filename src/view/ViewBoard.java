package view;

import javax.swing.*;

import controller.ButtonController;
import model.Board;
import model.Coordinate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private ButtonController buttonController;
	private ViewPiece viewPiece;
	private Board board;
	
	public ViewBoard(ButtonController buttonController, Board board) {
		viewPiece = new ViewPiece();
		this.buttonController = buttonController;
		this.board = board;
		grid = new ViewCell[board.getHeight()][board.getWidth()];
		
		MouseListener gridButton = getMouseListener();
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				grid[i][a] = new ViewCell(board.getAllCells()[i][a].getCol(), board.getAllCells()[i][a].getRow(), board.getAllCells()[i][a].getVisible());
				grid[i][a].addMouseListener(gridButton);
			}
		}
		
		setLayout(new GridLayout(15, 15));
		
		//Adds JButtons or JLabels
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
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
	
	private MouseListener getMouseListener() {
		 return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewCell button = (ViewCell)e.getSource();
				buttonController.passCoordinates(new Coordinate(button.getCol(), button.getRow()));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ViewCell button = (ViewCell)e.getSource();
				if(buttonController.getPendingMove() != null) {
					button.raiseBorder();
					buttonController.setPendingMove(new Coordinate(button.getCol(), button.getRow()));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((ViewCell) e.getSource()).resetBorder();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				ViewCell button = (ViewCell)e.getSource();
				Coordinate co = new Coordinate(button.getCol(), button.getRow());
				buttonController.passCoordinates(co);
				buttonController.setPendingMove(co);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				ViewCell button = (ViewCell) e.getSource();
				Coordinate co = new Coordinate(button.getCol(), button.getRow());
				co = buttonController.getPendingMove();
				button = grid[co.x][co.y];
				buttonController.passCoordinates(co);
				buttonController.setPendingMove(null);
				button.resetBorder();
			}

		};
	}
	
	public void updateBoard () {
		Coordinate co;
		
		//Colour key:
		//		Orange - Background colour
		//		Yellow - Player 1 pieces
		//		Grey - Player 2 pieces
		//		Green - Cells that the currently selected piece can move to
		//		Red - Cells that the currently selected piece can attack
		// 		White - Currently selected cell
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				if (grid[i][a].getVisible() == true){
					co = new Coordinate(i, a);
					if (board.getPiece(co) != null) {
						if (board.getPiece(co).getPlayerName().equals("player1"))
							grid[i][a].setBackground(Color.YELLOW);
						else
							grid[i][a].setBackground(Color.GRAY);
						grid[i][a].setIcon(viewPiece.getIcon(board.getPiece(co).getName(), board.getPiece(co).getIcon()));
					} else {
						grid[i][a].setBackground(Color.ORANGE);
						grid[i][a].setIcon(null);
					}
				}
			}
		}
	}

	public void updateCells(List<Coordinate> list) {
		// turns all the cells the piece can move to green
		for (Coordinate moveableCoordinates : list) {
			if (moveableCoordinates.x < board.getWidth() && moveableCoordinates.x >= 0 && moveableCoordinates.y < board.getHeight() && moveableCoordinates.y >= 0) {
				grid[moveableCoordinates.x][moveableCoordinates.y].setBackground(Color.GREEN);
			}
		}
	}
	
	public void updateAttackRange(List<Coordinate> attackRange) {
		for (Coordinate attackRanges : attackRange) {
/*			if (grid[attackRanges.x][attackRanges.y].getBackground() == Color.GREEN)
				grid[attackRanges.x][attackRanges.y].setBackground(Color.PINK);
			else*/
				grid[attackRanges.x][attackRanges.y].setBackground(Color.RED);
		}
	}
}
