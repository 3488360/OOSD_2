package model.pieces;

import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class ArcherPiece extends AbstractPiece {
    /* The Archer is ranged, has medium hp, medium attack and a high move range */

    public ArcherPiece(Player player) {
    	super(player);
        name = "Archer";
        icon = "images/Archer2.png";
        maxHealth = 200;
        strength = 90;
        cost = 50;
        currentHealth = maxHealth;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-4; i <= 4; i++) {
			for(int j=-2; j <= 2; j++) {
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
