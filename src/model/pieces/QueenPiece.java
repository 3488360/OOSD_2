package model.pieces;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class QueenPiece extends AbstractPiece {
	private static final long serialVersionUID = 4038043494671540160L;

	public QueenPiece (String playerName) {
		super(playerName);
		name = "Queen";
		cost = 100;
		icon = "images/Queen.png";
		maxHealth = 400;
		strength = 100;
		defense = 200;
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
