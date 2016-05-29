package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class GodPiece extends AbstractPiece {
    /* The God has medium-to-low hp, high attack and a high move range */
	private static final long serialVersionUID = 6150525204578147335L;

	public GodPiece(String playerName) {
        super(playerName);
        name = "God";
        icon = "images/God.png";
        maxHealth = 200;
        strength = 200;
		defense = 100;
        cost = 70;
        currentHealth = maxHealth;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-3; i <= 3; i+=2) {
			for(int j=-3; j <= 3; j+=2) {
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
