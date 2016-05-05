package view;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This is the panel located on the right (east) side of the main interface and is responsible for showing the timer and the selected piece's details.
 *
 */
public class ViewTimer extends JPanel {
    private static final long serialVersionUID = -2178376943831400894L;
    
    private JLabel timer;
    private JLabel selectedPieceName;
    private JLabel health;
    private JLabel strength;
    
    /**
     * Creates a new timer JPanel
     */
    public ViewTimer() {
    	Box box = Box.createVerticalBox();
    	timer = new JLabel("Time: 00");
    	timer.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
    	timer.setBorder(new EmptyBorder(0, 0, 0, 60));
		health = new JLabel();
		strength = new JLabel();
		selectedPieceName = new JLabel();
		box.add(timer);
		box.add(selectedPieceName);
		box.add(health);
		box.add(strength);
		add(box);
    }
	
	/**
	 * Updates the timer's time with the given int.
	 * 
	 * @param time - The current time on the timer.
	 */
	public void setInterfaceTimer(int time) {
		timer.setText("Time: " + time);
	}

	/**
	 * Displays the selected piece's name, health and strength.
	 * 
	 * @param name - The piece's name.
	 * @param currentHealth - The piece's current health.
	 * @param currentStrength - The piece's strength.
	 */
	public void addSelectedPiece(String name, String currentHealth, String currentStrength) {
		selectedPieceName.setText(name);
		health.setText("Health: " + currentHealth);
		strength.setText("Strength: " + currentStrength);
		selectedPieceName.setVisible(true);
		health.setVisible(true);
		strength.setVisible(true);
	}

	/**
	 * Hides the JLabels responsible for showing the selected piece's details
	 */
	public void hideSelected() {
		selectedPieceName.setVisible(false);
		health.setVisible(false);
		strength.setVisible(false);
	}
}


	