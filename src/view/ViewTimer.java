package view;

import controller.ButtonController;
import model.Coordinate;
import model.pieces.AggressiveStanceDecoratedPiece;
import model.pieces.DecoratedPiece;
import model.pieces.DefensiveStanceDecoratedPiece;
import model.pieces.PieceInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This is the panel located on the right (east) side of the main interface and is responsible for showing the timer and the selected piece's details.
 *
 */
public class ViewTimer extends JPanel implements Observer {
    private static final long serialVersionUID = -2178376943831400894L; 
    private JLabel timer;
    private JLabel selectedPieceName;
    private JLabel health;
    private JLabel strength;
    private JLabel defense;

	private JButton aggressiveStance;
	private JButton defensiveStance;
	private JButton neutralStance;

	private ActionListener aggressiveStanceListener = null;
	private ActionListener defensiveStanceListener = null;
	private ActionListener neutralStanceListener = null;

    /**
     * Creates a new timer JPanel
     */
    public ViewTimer(AbstractUIFactory uiFactory) {
    	Box box = Box.createVerticalBox();
    	timer = uiFactory.createLabel("Time: 00");
    	timer.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
    	timer.setBorder(new EmptyBorder(0, 0, 0, 60));
		health = uiFactory.createLabel();
		strength = uiFactory.createLabel();
		defense = uiFactory.createLabel();
		selectedPieceName = uiFactory.createLabel();

		aggressiveStance = uiFactory.createButton("Aggressive");
		defensiveStance = uiFactory.createButton("Defensive");
		neutralStance = uiFactory.createButton("Neutral");

		aggressiveStance.setVisible(false);
		defensiveStance.setVisible(false);
		neutralStance.setVisible(false);

		box.add(timer);
		box.add(selectedPieceName);
		box.add(health);
		box.add(strength);
		box.add(defense);
		box.add(aggressiveStance);
		box.add(defensiveStance);
		box.add(neutralStance);

		add(box);
    }

	/**
	 * Displays the selected piece's name, health and strength.
	 * 
	 * @param piece - The piece
	 */
	public void addSelectedPiece(ButtonController buttonController, PieceInterface piece) {
		selectedPieceName.setText(piece.getName());
		health.setText("Health: " + piece.getCurrentHealth());
		strength.setText("Strength: " + piece.getStrength());
		defense.setText("Defense: " + piece.getDefense());
		selectedPieceName.setVisible(true);
		health.setVisible(true);
		strength.setVisible(true);

		aggressiveStance.setVisible(true);
		defensiveStance.setVisible(true);
		neutralStance.setVisible(true);

		setupStances(buttonController, piece);
	}

	private void setupStances(ButtonController buttonController, PieceInterface piece) {
		aggressiveStance.setEnabled(true);
		defensiveStance.setEnabled(true);
		neutralStance.setEnabled(true);

		if(piece instanceof AggressiveStanceDecoratedPiece) {
			aggressiveStance.setEnabled(false);
		}
		else if (piece instanceof DefensiveStanceDecoratedPiece) {
			defensiveStance.setEnabled(false);
		}
		else {
			neutralStance.setEnabled(false);
		}

		if(aggressiveStanceListener != null)
			aggressiveStance.removeActionListener(aggressiveStanceListener);

		if(defensiveStanceListener != null)
			defensiveStance.removeActionListener(defensiveStanceListener);

		if(neutralStanceListener != null)
			neutralStance.removeActionListener(neutralStanceListener);

		aggressiveStanceListener = e -> {
			PieceInterface newPiece = piece;
			if(piece instanceof DecoratedPiece)
				newPiece = ((DecoratedPiece) piece).undecorate();
			newPiece = new AggressiveStanceDecoratedPiece(newPiece);
			buttonController.setStance(newPiece);

			// redraw
			addSelectedPiece(buttonController, newPiece);
		};
		defensiveStanceListener = e -> {
			PieceInterface newPiece = piece;
			if(piece instanceof DecoratedPiece)
				newPiece = ((DecoratedPiece) piece).undecorate();
			newPiece = new DefensiveStanceDecoratedPiece(newPiece);
			buttonController.setStance(newPiece);

			// redraw
			addSelectedPiece(buttonController, newPiece);
		};
		neutralStanceListener = e -> {
			PieceInterface newPiece = piece;
			if(piece instanceof DecoratedPiece)
				newPiece = ((DecoratedPiece) piece).undecorate();
			buttonController.setStance(newPiece);

			// redraw
			addSelectedPiece(buttonController, newPiece);
		};

		aggressiveStance.addActionListener(aggressiveStanceListener);
		defensiveStance.addActionListener(defensiveStanceListener);
		neutralStance.addActionListener(neutralStanceListener);
	}

	/**
	 * Hides the JLabels responsible for showing the selected piece's details
	 */
	public void hideSelected() {
		selectedPieceName.setVisible(false);
		health.setVisible(false);
		strength.setVisible(false);
		aggressiveStance.setVisible(false);
		defensiveStance.setVisible(false);
		neutralStance.setVisible(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		timer.setText("Time: " + (int)arg);
		
	}
}


	