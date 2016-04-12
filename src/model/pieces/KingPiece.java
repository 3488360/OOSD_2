package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class KingPiece extends AbstractPiece {
	public KingPiece(Player player) {
		super(player); 
		cost = 100;
		name = "King";
		icon = resizeIcon(new ImageIcon("King.png", "King"));
		maxHealth = 500;
		strength = 50;
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
