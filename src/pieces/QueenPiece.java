package pieces;

import javax.swing.ImageIcon;

import interfaces.Piece;
import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class QueenPiece extends AbstractPiece {
	
	public QueenPiece (Player player) {
		super(player);
		name = "Queen";
		cost = 80;
		icon = resizeIcon(new ImageIcon("Queen.png", "Queen"));
		maxHealth = 400;
		strength = 200;
	}

	@Override
	public List<Coordinate> getMoves(Coordinate co) {
		Coordinate[] fish = new Coordinate[8];
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		moves.add(new Coordinate(co.x - 1, co.y - 1));
		moves.add(new Coordinate(co.x, co.y - 1));
		moves.add(new Coordinate(co.x + 1, co.y - 1));
		moves.add(new Coordinate(co.x - 1, co.y));
		moves.add(new Coordinate(co.x + 1, co.y));
		moves.add(new Coordinate(co.x - 1, co.y + 1));
		moves.add(new Coordinate(co.x, co.y + 1));
		moves.add(new Coordinate(co.x + 1, co.y + 1));
		
		return moves;
	}

	@Override
	public List<Coordinate> getAttackRange() {
		return null;
	}
}
