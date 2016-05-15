package model;

import java.util.ArrayList;
import java.util.List;

import model.pieces.PieceInterface;

//Information Expert. Keeps track of all cells.
public class Board {
	private final int WIDTH = 15;
	private final int HEIGHT = 15;
	private Cell cells[][] = new Cell[WIDTH][HEIGHT];
	
	PieceInterface[] pieces = new PieceInterface[5]; 
	
	public Board () {
		for (int a = 0; a < WIDTH; a++) {
			for (int b = 0; b < HEIGHT; b++) {
				if ((a == 0 && b == 0) || (a == 0 && b == 1) || (a == 0 && b == 2) || (a == 0 && b == 3) || (a == 0 && b == 4) || (a == 1 && b == 0) || (a == 1 && b == 1) || (a == 1 && b == 2) || (a == 1 && b == 3) || (a == 1 && b == 4) || (a == 2 && b == 0) || (a == 2 && b == 1) || (a == 2 && b == 2) || (a == 2 && b == 3) || (a == 2 && b == 4) || (a == 3 && b == 0) || (a == 3 && b == 1) || (a == 3 && b == 2) || (a == 3 && b == 3) || (a == 3 && b == 4) || (a == 4 && b == 0) || (a == 4 && b == 1) || (a == 4 && b == 2) || (a == 4 && b == 3) || (a == 4 && b == 4) || (a == 11 && b == 0) || (a == 11 && b == 1) || (a == 11 && b == 2) || (a == 11 && b == 3) || (a == 11 && b == 4) || (a == 12 && b == 0) || (a == 12 && b == 1) || (a == 12 && b == 2) || (a == 12 && b == 3) || (a == 12 && b == 4) || (a == 13 && b == 0) || (a == 13 && b == 1) || (a == 13 && b == 2) || (a == 13 && b == 3) || (a == 13 && b == 4) || (a == 10 && b == 0) || (a == 10 && b == 1) || (a == 10 && b == 2) || (a == 10 && b == 3) || (a == 10 && b == 4) || (a == 14 && b == 0) || (a == 14 && b == 1) || (a == 14 && b == 2) || (a == 14 && b == 3) || (a == 14 && b == 4) || (a == 0 && b == 10) || (a == 0 && b == 11) || (a == 0 && b == 12) || (a == 0 && b == 13) || (a == 0 && b == 14) || (a == 1 && b == 10) || (a == 1 && b == 11) || (a == 1 && b == 12) || (a == 1 && b == 13) || (a == 1 && b == 14) || (a == 2 && b == 10) || (a == 2 && b == 11) || (a == 2 && b == 12) || (a == 2 && b == 13) || (a == 2 && b == 14) || (a == 3 && b == 10) || (a == 3 && b == 11) || (a == 3 && b == 12) || (a == 3 && b == 13) || (a == 3 && b == 14) || (a == 4 && b == 10) || (a == 4 && b == 11) || (a == 4 && b == 12) || (a == 4 && b == 13) || (a == 4 && b == 14) || (a == 10 && b == 10) || (a == 10 && b == 11) || (a == 10 && b == 12) || (a == 10 && b == 13) || (a == 10 && b == 14) || (a == 11 && b == 10) || (a == 11 && b == 11) || (a == 11 && b == 12) || (a == 11 && b == 13) || (a == 11 && b == 14) || (a == 12 && b == 10) || (a == 12 && b == 11) || (a == 12 && b == 12) || (a == 12 && b == 13) || (a == 12 && b == 14) || (a == 13 && b == 10) || (a == 13 && b == 11) || (a == 13 && b == 12) || (a == 13 && b == 13) || (a == 13 && b == 14) || (a == 14 && b == 10) || (a == 14 && b == 11) || (a == 14 && b == 12) || (a == 14 && b == 13) || (a == 14 && b == 14)) {
					cells[a][b] = new Cell(a, b, false);
				} else {
					cells[a][b] = new Cell(a, b, true);
				}
			}
		}
	}
	
	public Cell[][] getAllCells() {
		return cells;
	}
	
	public int getWidth () {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public void setPiece(Coordinate co, PieceInterface p){
		cells[co.x][co.y].setPiece(p);
	}

	public PieceInterface getPiece(Coordinate co) {
		return (cells[co.x][co.y].getPiece());
	}

	public List<Coordinate> getMovement(Coordinate co, String player) {
		List<Coordinate> validMovements = new ArrayList<Coordinate>();
		for(Coordinate coordinate : getPiece(co).getMoves(co)) {
			// check bounds:
			int x = coordinate.x;
			int y = coordinate.y;
			
			if (coordinate.equals(co)) {
				continue;
			}

			if(x < 0 || x >= 15 || y < 0 || y >= 15) {
				continue;
			}

			// cross check
			if((x >= 0 && x < 5) || (x >= 10 && x < 15))
				if ((y >= 0 && y < 5) || (y >= 10 && y < 15)) {
				continue;
			}
			
			if (getPiece(coordinate) != null && getPiece(coordinate).getPlayerName().equals(player)) {
				continue;
			}
			validMovements.add(coordinate);

		}
		return validMovements;
	}
	
	public List<Coordinate> getAttackRange(Coordinate co, String player) {
		List<Coordinate> validMovements = new ArrayList<Coordinate>();
		for(Coordinate coordinate : getPiece(co).getAttackRange(co)) {
			// check bounds:
			int x = coordinate.x;
			int y = coordinate.y;
			
			if (coordinate.equals(co)) {
				continue;
			}

			if(x < 0 || x >= 15 || y < 0 || y >= 15) {
				continue;
			}

			// cross check
			if((x >= 0 && x < 5) || (x >= 10 && x < 15))
				if ((y >= 0 && y < 5) || (y >= 10 && y < 15)) {
				continue;
			}
			
			if (getPiece(coordinate) == null) {
				continue;
			} else if (getPiece(coordinate).getPlayerName().equals(player) && !getPiece(co).getName().equals("Healer")) {
				continue;
			} else if (!getPiece(coordinate).getPlayerName().equals(player) && getPiece(co).getName().equals("Healer")) {
				continue;
			}
			validMovements.add(coordinate);

		}
		return validMovements;
	}

	public String getPlayer(Coordinate co) {
		if (cells[co.x][co.y].getPiece() != null)
			return cells[co.x][co.y].getPiece().getPlayerName();
		return null;
	}	
}
