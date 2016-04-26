package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class TestPiece extends AbstractPiece {
	//A piece that is meant for testing with, not meant for regular gameplay
	public TestPiece(Player player) {
		super(player); 
		cost = 0;
		name = "Test";
		icon = "images/K.png";
		maxHealth = 10000;
		strength = 10000;
		currentHealth = maxHealth;
	}

	@Override
	public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				moves.add(new Coordinate(i, a));
			}
		}
		return moves;
	}

	@Override
	public List<Coordinate> getAttackRange(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				moves.add(new Coordinate(i, a));
			}
		}
		return moves;
	}

}
