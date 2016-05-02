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
				if(((a >= 0 && a < 5) || (a >= 10 && a < 15)) && ((b >= 0 && b < 5) || (b >= 10 && b < 15))) {
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

	public List<Coordinate> getMovement(PieceInterface p, Coordinate co) {
		List<Coordinate> validMovements = new ArrayList<Coordinate>();
		for(Coordinate coordinate : p.getMoves(co)) {
			// check bounds:
			int x = coordinate.x;
			int y = coordinate.y;

			if(x < 0 || x >= 15 || y < 0 || y >= 15) {
				continue;
			}

			// cross check
			if((x >= 0 && x < 5) || (x >= 10 && x < 15))
				if ((y >= 0 && y < 5) || (y >= 10 && y < 15)) {
				continue;
			}
			validMovements.add(coordinate);

		}
		return validMovements;
	}

	public Player getPlayer(Coordinate co) {
		return cells[co.x][co.y].getPiece().getPlayer();
	}	
}
