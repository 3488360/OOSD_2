package model.pieces;

import javax.swing.ImageIcon;

import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class TestPiece extends AbstractPiece {

	public TestPiece(Player player) {
		super(player); 
		cost = 0;
		name = "TestPiece";
		icon = resizeIcon(new ImageIcon("K.png", "Test Piece"));
		maxHealth = 10000;
		strength = 10000;
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
		return null;
	}

}
