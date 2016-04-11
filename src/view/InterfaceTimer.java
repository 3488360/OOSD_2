package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceTimer extends JPanel {
	private static final long serialVersionUID = -2178376943831400894L;
	
	private JLabel timer;
	private JLabel selectedPiece;
	private Box box;
	
	public InterfaceTimer(int time) {
		timer = new JLabel("Time: " + time);
		timer.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
		box = Box.createVerticalBox();
		box.add(timer);
		selectedPiece = new JLabel();
		setPreferredSize(new Dimension(120,120));
		add(box);
	}
	
	public void setInterfaceTimer(int time) {
		timer.setText("Time: " + time);
	}

	public void addSelectedPiece(String piece) {
		selectedPiece.setText("\n\n\nSelected: " + piece);
		box.add(selectedPiece);
		selectedPiece.setVisible(true);
	}

	public void hideSelected() {
		selectedPiece.setVisible(false);
		
	}
}
