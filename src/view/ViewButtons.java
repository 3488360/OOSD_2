package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ButtonController;
import controller.PlayerController;
import controller.SaveController;
import model.Board;
import model.Game;

/**
 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
 *
 */
public class ViewButtons extends JPanel {
	private static final long serialVersionUID = 5295137264520068029L;
	private SaveController saveController;
	private ButtonController buttonController;
	private String player1;
	private String player2;
	private JButton pause;
	
	/**
	 * Creates a new JPanel that contains the buttons located at the bottom of the screen.
	 */
	public ViewButtons(Board b, String player1, String player2, ButtonController bc, PlayerController pc, Game game) {
		this.player1 = player1;
		this.player2 = player2;
		this.buttonController = bc;
		saveController = new SaveController(game, pc);
		pause = new JButton("Pause");
		JButton exit = new JButton("Exit");
		JButton save = new JButton ("Save");
		
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
		buttonController.pause();
		
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save Files", "save", "save");
		fc.setFileFilter(filter);
		
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (!saveController.saveGame(fc.getSelectedFile(), player1, player2)) {
				JOptionPane.showMessageDialog(null, "An error occured when trying to save the file. Please see console for details.", "Error", 0);
				return;
			}
		}

		buttonController.pause();
	}
	
	private void pause() {
		if (pause.getText().equals("Pause"))
			pause.setText("Resume");
		else
			pause.setText("Pause");
		
		buttonController.pause();
	}
}
