package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class KingPiece extends AbstractPiece {
	private static final long serialVersionUID = -8222583134405858015L;

	public KingPiece(String playerName) {
		super(playerName); 
		name = "King";
		cost = 100;
		icon = "images/King.png";
		maxHealth = 500;
		strength = 80;
		currentHealth = maxHealth;
	}

	@Override
	public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-1; i <= 1; i++) {
			for(int j=-1; j <= 1; j++) {
				moves.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return moves;
	}

	@Override
	public List<Coordinate> getAttackRange(Coordinate co) {
		return getMoves(co);
	}
}
