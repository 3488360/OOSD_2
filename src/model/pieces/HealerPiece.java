package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class HealerPiece extends AbstractPiece {
    /* The Healer is ranged, has high hp, no attack and a medium move range */

    public HealerPiece(Player player) {
        super(player);
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
	List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-2; i <= 2; i++) {
			for(int j=-2; j <= 2; j++) {
				moves.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return moves;
    }

}
