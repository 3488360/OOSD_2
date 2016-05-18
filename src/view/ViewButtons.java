package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;
import controller.PlayerController;
import controller.SaveController;
import model.Board;

/**
 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
 *
 */
public class ViewButtons extends JPanel {
	private static final long serialVersionUID = 5295137264520068029L;
	private SaveController saveController;
	private GameController gameController;
	private String player1;
	private String player2;
	
	/**
	 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
	 */
	public ViewButtons(Board b, String player1, String player2, GameController game, PlayerController pc, AbstractUIFactory uiFactory) {
		this.player1 = player1;
		this.player2 = player2;
		this.gameController = game;
		saveController = new SaveController(game.getGame(), pc);
		JButton pause = uiFactory.createButton("Pause");
		JButton exit = uiFactory.createButton("Exit");
		JButton save = uiFactory.createButton ("Save");
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				exit();
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
		
		add(pause);
		add(save);
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
	
	private void save() {
		boolean saved = false;
		String saveName = (String)JOptionPane.showInputDialog(null, "Please type in a save name:", "Save Name", 3);
		
		if (saveName == null) {
			return;
		}
		
		saved = saveController.saveGame(saveName, player1, player2);
		
		if (!saved) {
			JOptionPane.showMessageDialog(null, "An error occured when trying to save. Please see console for details.", "Error", 0);
		}
	}
	
	private void pause() {
		gameController.pause();
	}
}
