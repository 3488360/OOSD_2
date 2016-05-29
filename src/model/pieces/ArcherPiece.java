package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ArcherPiece extends AbstractPiece {
    /* The Archer is ranged, has medium hp, medium-to-low attack and a high move range */
	private static final long serialVersionUID = 6632606647302103884L;

	public ArcherPiece(String playerName) {
    	super(playerName);
        name = "Archer";
        icon = "images/Archer2.png";
        maxHealth = 300;
        strength = 100;
		defense = 50;
        cost = 60;
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
		List<Coordinate> attacks = new ArrayList<Coordinate>();
		
		for(int i=-3; i <= 3; i++) {
			if (i == -3 || i == 3) {
				for(int j=-3; j <= 3; j++) {
					attacks.add(new Coordinate(co.x + i, co.y + j));
				}
			} else {
				for(int j=-3; j <= 3; j += 6) {
					attacks.add(new Coordinate(co.x + i, co.y + j));
				}
			}
		}
		
		return attacks;
    }
}
