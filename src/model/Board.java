package model;

import java.util.ArrayList;
import java.util.List;

import model.pieces.PieceInterface;

//Information Expert. Keeps track of all cells.
public class Board {
	private int width = 0;
	private int height = 0;
	private Cell cells[][];
	private BoardShape boardShape;
	
	public Board(BoardShape boardShape) {
		this.boardShape = boardShape;
		cells = boardShape.getLayout();
		height = boardShape.getHeight();
		width = boardShape.getWidth();
	}
	
	public Cell[][] getAllCells() {
		return cells;
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setPiece(Coordinate co, PieceInterface p){
		cells[co.x][co.y].setPiece(p);
	}

	public PieceInterface getPiece(Coordinate co) {
		//System.out.println("Coordinates " + co.x + " " + co.y);
		
		try {
			return (cells[co.x][co.y].getPiece());
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
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

			if(x < 0 || x >= width || y < 0 || y >= height) {
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

			if(x < 0 || x >= width || y < 0 || y >= height) {
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
	
	public BoardShape getBoardShape() {
		return boardShape;
	}
	
	public int countPiece(String piece, String player) {
		int count = 0;
		
		if (piece.equals("") || player.equals("")) {
			return -1;
		}
		
		for (int a = 0; a < width; a++) {
			for (int b = 0; b < height; b++) {
				if (cells[a][b].getPiece() != null) {
					if (cells[a][b].getPiece().getName().equals(piece) && cells[a][b].getPiece().getPlayerName().equals(player)) {
						count++;
					}
				}
			}
		}
		System.out.println("Count is " + count);
		return count;
	}
	
	public void setupBoard(BoardLayout layout) {
		Coordinate co;
		
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				co = new Coordinate(i, a);
				if (layout.getPiece(co) != null) {
					setPiece(co, layout.getPiece(co));
				}
			}
		}
	}
}
