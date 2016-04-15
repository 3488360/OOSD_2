package view;

import javax.swing.JButton;

public class ViewCell extends JButton {
	private static final long serialVersionUID = -7155500879732378953L;
	
	private int row;
	private int col;
	boolean isVisible;
	protected boolean canMoveTo = false;
	
	public ViewCell (int row, int col, boolean isVisible) {
		this.row = row;
		this.col = col;
		this.isVisible = isVisible;
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
	
	public boolean getCanMoveTo(){
		return canMoveTo; 
	}
	
	public void setCanMoveTo(boolean canMove){
		canMoveTo = canMove; 
	}
}
