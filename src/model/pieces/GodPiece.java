package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class GodPiece extends AbstractPiece {
    /* The God has medium hp, high attack and a high move range */

    public GodPiece(Player player) {
        super(player);
        name = "God";
        icon = "images/God.png";
        maxHealth = 200;
        strength = 240;
        cost = 60;
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
        return null;
    }

}
