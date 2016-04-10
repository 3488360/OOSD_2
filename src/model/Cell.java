package model;

import interfaces.Piece;

public class Cell {
	protected int row;
	protected int col;
	protected boolean isVisible;
	
	private Piece pieceOnSquare = null; 
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
	
	public Piece getPiece(){
		return pieceOnSquare; 
	}
	
	public void setPiece(Piece piece){
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
