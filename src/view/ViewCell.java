package view;

import model.State;

import javax.swing.*;
import java.awt.*;

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
		resetBorder();
	}

	public void resetBorder() {
		setBorder(UIManager.getBorder("Button.border"));
	}

	public void raiseBorder() {
		setBorder(BorderFactory.createLoweredBevelBorder());
	}


	public void setState(State state) {
		Color color;
		switch(state) {

			case PLAYER1:
				color = Color.YELLOW;
				break;

			case PLAYER2:
				color = Color.GRAY;
				break;

			case ATTACK:
				color = Color.RED;
				break;

			case MOVE:
				color = Color.GREEN;
				break;

			case NORMAL:
			default:
				color = Color.ORANGE;
				break;
		}

		setBackground(color);
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
