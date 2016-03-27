package pieces;

import javax.swing.ImageIcon;

import interfaces.Piece;
import model.Coordinate;
import model.Player;

public class QueenPiece extends Piece {
	
	public QueenPiece (Player player) {
		super(player);
		name = "Queen";
		cost = 80;
		icon = new ImageIcon("Queen.png", "Queen");
		icon = resizeIcon(icon);
		health = 400;
		attack = 200;
	}

	@Override
	public Coordinate[] getMoves(Coordinate co) {
		Coordinate[] fish = new Coordinate[8];
		
		fish[0] = new Coordinate(co.x - 1, co.y - 1);
		fish[1] = new Coordinate(co.x, co.y - 1);
		fish[2] = new Coordinate(co.x + 1, co.y - 1);
		fish[3] = new Coordinate(co.x - 1, co.y);
		fish[4] = new Coordinate(co.x + 1, co.y);
		fish[5] = new Coordinate(co.x - 1, co.y + 1);
		fish[6] = new Coordinate(co.x, co.y + 1);
		fish[7] = new Coordinate(co.x + 1, co.y + 1);
		
		return fish;
	}
}
