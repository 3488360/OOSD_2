package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class SoldierPiece extends AbstractPiece {
	private static final long serialVersionUID = 1805844297535185000L;

	/* The Soldier has medium hp-to-high, low attack and a medium move range */
    public SoldierPiece(String playerName) {
        super(playerName);
        name = "Soldier";
        icon = "images/Soldier2.png";
        maxHealth = 350;
        strength = 100;
		defense = 100;
        cost = 40;
        currentHealth = maxHealth;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-2; i <= 2; i++) {
			for(int j=-2; j <= 2; j++) {
				moves.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return moves;
    }

    @Override
    public List<Coordinate> getAttackRange(Coordinate co) {
		List<Coordinate> attacks = new ArrayList<Coordinate>();
		
		for(int i=-1; i <= 1; i++) {
			for(int j=-1; j <= 1; j++) {
				attacks.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return attacks;
    }

}
