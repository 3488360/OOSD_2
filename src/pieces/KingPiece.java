package pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

public class KingPiece extends AbstractPiece {
	public KingPiece(Player player) {
		super(player);
		maxHealth = 100;
		strength = 0;
		cost = 0;
	}

	@Override
	public List<Coordinate> getMoves(Coordinate coordinate) {
		return null;
	}

	@Override
	public List<Coordinate> getAttackRange() {
		return null;
	}

}
