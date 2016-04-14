package model.pieces;

import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class WizardPiece extends AbstractPiece{
    /* The Wizard is ranged, has low hp, high attack and a medium move range */
    public WizardPiece(Player player) {
        super(player);
        name = "Wizard";
        icon = resizeIcon(new ImageIcon("images/Wizard.png", "Wizard"));
        maxHealth = 100;
        strength = 240;
        cost = 50;
        currentHealth = maxHealth;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-1; i <= 1; i++) {
			for(int j=-4; j <= 4; j++) {
				moves.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return moves;
    }

    @Override
    public List<Coordinate> getAttackRange(Coordinate co) {
        return null;
    }

}
