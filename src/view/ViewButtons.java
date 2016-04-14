package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
 *
 */
public class ViewButtons extends JPanel {
	private static final long serialVersionUID = 5295137264520068029L;
	
	/**
	 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
	 */
	public ViewButtons() {
		JButton exit = new JButton("Exit");
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		add(exit);
	}
	
	/**
	 * Asks for confirmation and then exits the program.
	 */
	private void exit() {
		int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit?", "Warning", 0);
		
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);	
		}
	}
}
