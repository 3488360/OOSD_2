package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class WizardPiece extends AbstractPiece{
	private static final long serialVersionUID = 5199911191880672234L;

	/* The Wizard is ranged, has low hp, high attack and a medium move range */
    public WizardPiece(String playerName) {
        super(playerName);
        name = "Wizard";
        icon = "images/Wizard.png";
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
		List<Coordinate> attacks = new ArrayList<Coordinate>();
		
		for(int i=-2; i <= 2; i++) {
			if (i == -2 || i == 2) {
				for(int j=-2; j <= 2; j++) {
					attacks.add(new Coordinate(co.x + i, co.y + j));
				}
			} else {
				for(int j=-2; j <= 2; j += 4) {
					attacks.add(new Coordinate(co.x + i, co.y + j));
				}
			}
		}
		
		return attacks;
    }
}
