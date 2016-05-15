package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class TankPiece extends AbstractPiece {
    /* The Tank has high hp, medium attack and a low move range */
	private static final long serialVersionUID = -5626954918577234965L;

	public TankPiece(String playerName) {
        super(playerName);
        name = "Tank";
        icon = "images/Tank.png";
        maxHealth = 500;
        strength = 90;
        cost = 50;
        currentHealth = maxHealth;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-1; i <= 1; i+=1) {
			for(int j=-1; j <= 1; j+=2) {
				moves.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return moves;
    }

    @Override
    public List<Coordinate> getAttackRange(Coordinate co) {
		List<Coordinate> attacks = new ArrayList<Coordinate>();
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				attacks.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return attacks;
    }

}
