package view;

import javax.swing.JButton;

import interfaces.Piece;

public class InterfaceCell extends JButton {
	private static final long serialVersionUID = -7155500879732378953L;
	
	private int row;
	private int col;
	private Piece piece;
	boolean isVisible;
	
	public InterfaceCell (int r, int c, boolean v, Piece p) {
		row = r;
		col = c;
		isVisible = v;
		piece = p;
	}
	
	public boolean getVisible () {
		return isVisible;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece p) {
		piece = p;
	}
}
