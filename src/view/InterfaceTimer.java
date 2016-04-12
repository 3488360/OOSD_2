package view;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterfaceTimer extends JPanel {
    private static final long serialVersionUID = -2178376943831400894L;
    private JLabel timer;
    private Box box;
    private JLabel selectedPiece;
    
    public InterfaceTimer() {
    	timer = new JLabel("Time: 00");
    	timer.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
    	timer.setBorder(new EmptyBorder(0, 0, 0, 60));
		box = Box.createVerticalBox();
		box.add(timer);
		selectedPiece = new JLabel();
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


	