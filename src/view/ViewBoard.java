package view;

import javax.swing.*;

import controller.GameController;
import model.Board;
import model.Coordinate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewBoard extends JPanel {
	private static final long serialVersionUID = 8695643799420470531L;
	
	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private Board board;
	private ViewCell grid[][];
	private GameController gameController; 
	
	public ViewBoard(GameController gameController, Board b) {
		this.gameController = gameController;
		board = b;
		grid = new ViewCell[board.getHeight()][board.getWidth()];
		
		ActionListener gridButton = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e);
			}
		};
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				grid[i][a] = new ViewCell(board.getAllCells()[i][a].getCol(), board.getAllCells()[i][a].getRow(), board.getAllCells()[i][a].getVisible(), /*board.getAllCells()[i][a].getPiece()*/null);
				grid[i][a].addActionListener(gridButton);
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
	
	public void updateBoard () {
		Coordinate co;
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				grid[i][a].setCanMoveTo(board.getAllCells()[i][a].getCanMoveTo());
			}
		}	
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int a = 0; a < board.getHeight(); a++) {
				if (grid[i][a].getVisible() == true){
					co = new Coordinate(i, a);
					if (board.getPiece(co) != null) {
						if (board.getPiece(co).getPlayer().getName().equals("player1"))
							grid[i][a].setBackground(Color.YELLOW);
						else
							grid[i][a].setBackground(Color.GRAY);
						grid[i][a].setIcon(board.getPiece(co).getIcon());
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

	public void updateCells(List<Coordinate> list) {
		// turns all the cells the piece can move to green
		for (Coordinate moveableCoordinates : list) {
			if (moveableCoordinates.x < board.getWidth()
					&& moveableCoordinates.x >= 0
					&& moveableCoordinates.y < board.getHeight()
					&& moveableCoordinates.y >= 0) {
				grid[moveableCoordinates.x][moveableCoordinates.y].setCanMoveTo(true);
				grid[moveableCoordinates.x][moveableCoordinates.y].setBackground(Color.GREEN);
			}
		}
	}
}
