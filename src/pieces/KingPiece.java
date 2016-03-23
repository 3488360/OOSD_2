package pieces;


import model.Coordinate;

import java.util.List;

public class KingPiece extends AbstractPiece {
	@Override
	public List<Coordinate> getMoves() {
		return null;
	}

	@Override
	public List<Coordinate> getAttackRange() {
		return null;
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getCost() {
		return 0;
	}
	//	Does the piece contain the instructions on how to draw
	

}
