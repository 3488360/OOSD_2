package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InterfaceButtons extends JPanel {
	private static final long serialVersionUID = 5295137264520068029L;
	
	private JButton restart;
	private JButton exit;
	
	public InterfaceButtons() {
		restart = new JButton("Restart");
		exit = new JButton("Exit");
		
		restart.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		add(restart);
		add(exit);
	}
	
	private void restart() {
		int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to restart?", "Warning", 0);
		
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Game is restarting!");
		}
	}
	
	private void exit() {
		int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit?", "Warning", 0);
		
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);	
		}

	}
}
