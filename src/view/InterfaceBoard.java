package view;

import javax.swing.*;

import controller.GameController;
import model.Board;
import model.Cell;
import model.Coordinate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfaceBoard extends JPanel {
	private static final long serialVersionUID = 8695643799420470531L;

	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private Board board;
	private InterfaceCell grid[][];
	private GameController gameController;

	public InterfaceBoard(Board b) {
		board = b;
		grid = new InterfaceCell[board.getHeight()][board.getWidth()];

		ActionListener gridButton = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e);
			}
		};

		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				grid[i][a] = new InterfaceCell(
						board.getAllCells()[i][a].getCol(),
						board.getAllCells()[i][a].getRow(),
						board.getAllCells()[i][a].getVisible(), /*
																 * board.getAllCells
																 * (
																 * )[i][a].getPiece
																 * ()
																 */null);
				grid[i][a].addActionListener(gridButton);
			}
		}

		setLayout(new GridLayout(15, 15));

		// Adds JButtons or JLabels
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				if (grid[i][a].getVisible() == true) {
					grid[i][a].setBackground(Color.ORANGE);
					grid[i][a].setForeground(Color.BLACK);
					grid[i][a].setBounds(grid[i][a].getRow(),
							grid[i][a].getCol(), CELLWIDTH, CELLHEIGHT);
					add(grid[i][a]);
				} else {
					add(new JLabel());
				}
			}
		}
	}

	public void addGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void updateBoard() {
		// maps the cells from interface cell to the board cell
		// for (int i = 0; i < board.getWidth(); i++) {
		// for (int a = 0; a < board.getHeight(); a++) {
		// grid[i][a].setCanMoveTo(board.getAllCells()[i][a]
		// .getCanMoveTo());
		// }
		// }
		turnOffAllMoveableCells();
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				if (grid[i][a].getVisible() == true) {
					if (board.getPiece(new Coordinate(i, a)) != null) {
						grid[i][a].setBackground(Color.BLUE);
						grid[i][a].setIcon(board.getPiece(new Coordinate(i, a))
								.getIcon());
					} else {	
						grid[i][a].setBackground(Color.ORANGE);
						grid[i][a].setIcon(null);
					}
					// if (grid[i][a].canMoveTo == true) {
					// grid[i][a].setBackground(Color.GREEN);
					// }
				}
			}
		}
	}

	private void buttonPressed(ActionEvent e) {
		InterfaceCell button = (InterfaceCell) e.getSource();
		System.out.println("Cell " + button.getCol() + " " + button.getRow()
				+ " has been selected!");
		gameController.passCoordinates(new Coordinate(button.getCol(), button
				.getRow()));
	}

	public void updateCells(List<Coordinate> list) {
		// turns all the cells the piece can move to green
		for (Coordinate moveableCoordinates : list) {
			if (moveableCoordinates.x < board.getWidth()
					&& moveableCoordinates.x >= 0
					&& moveableCoordinates.y < board.getHeight()
					&& moveableCoordinates.y >= 0) {
				grid[moveableCoordinates.x][moveableCoordinates.y]
						.setCanMoveTo(true);
				grid[moveableCoordinates.x][moveableCoordinates.y]
						.setBackground(Color.GREEN);
			}
		}
	}

	private void turnOffAllMoveableCells() {
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				grid[i][j].setCanMoveTo(false);
			}
		}
	}

}
