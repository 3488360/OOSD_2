package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceTimer extends JPanel {
	private static final long serialVersionUID = -2178376943831400894L;
	
	private JLabel timer;
	
	public InterfaceTimer(int time) {
		timer = new JLabel("Time: " + time);
		timer.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
		add(timer);
	}
	
	public void setInterfaceTimer(int time) {
		timer.setText("Time: " + time);
	}
}
