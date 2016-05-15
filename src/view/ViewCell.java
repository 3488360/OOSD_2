package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;

public class ViewCell extends JButton {
	private static final long serialVersionUID = -7155500879732378953L;
	
	private int row;
	private int col;
	boolean isVisible;
	
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

	public boolean getVisible () {
		return isVisible;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
