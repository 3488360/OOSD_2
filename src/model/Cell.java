package model;

import model.pieces.PieceInterface;

//Information expert. Keeps track of the settings of each individual cell.
public class Cell {
	protected int row;
	protected int col;
	protected boolean isVisible;
	private PieceInterface pieceOnSquare = null; 
	protected boolean canMoveTo = false;

	public Cell (int i, int j, boolean visible) {
		row = i;
		col = j;
		isVisible = visible;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public boolean getVisible() {
		return isVisible;
	}
	
	public PieceInterface getPiece(){
		return pieceOnSquare; 
	}
	
	public void setPiece(PieceInterface piece){
		pieceOnSquare = piece;  
	}
	
	public void setVisible(boolean vis){
		isVisible = vis;
	}	
	
	public void setCanMoveTo(boolean canMove){
		canMoveTo = canMove; 
	}
	
	public boolean getCanMoveTo(){
		return canMoveTo; 
	}
}
