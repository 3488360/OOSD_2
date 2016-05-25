package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ButtonControllerInterface;
import model.Board;

/**
 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
 *
 */
public class ViewButtons extends JPanel {
	private static final long serialVersionUID = 5295137264520068029L;
	private ButtonControllerInterface buttonController;
	private String player1;
	private String player2;
	private JButton pause;
	private JButton load;
	
	/**
	 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
	 */
	public ViewButtons(Board b, String player1, String player2, ButtonControllerInterface bc) {
		this.player1 = player1;
		this.player2 = player2;
		this.buttonController = bc;
		pause = new JButton("Pause");
		JButton exit = new JButton("Exit");
		JButton save = new JButton ("Save");
		load = new JButton("Load");
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				buttonController.exit();
			}
		});
		
		save.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		pause.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		if (buttonController.getClass().getName().equals("controller.LayoutEditorController")) {
			add(load);
		} else {
			add(pause);
		}
		
		add(save);
		add(exit);
	}
	
	private void save() {
		buttonController.saveGame(player1, player2);
	}
	
	private void pause() {
		if (pause.getText().equals("Pause"))
			pause.setText("Resume");
		else
			pause.setText("Pause");
		
		buttonController.pause();
	}
}
