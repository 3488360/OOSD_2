package model.pieces;


import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class HealerPiece extends AbstractPiece {
    /* The Healer is ranged, has high hp, no attack and a medium move range */
	private static final long serialVersionUID = -4800393400413421387L;

	public HealerPiece(String playerName) {
        super(playerName);
        name = "Healer";
        icon = "images/Healer.png";
        maxHealth = 100;
        strength = -50;
        cost = 70;
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
