package pieces;

import model.Coordinate;
import model.Player;
import interfaces.Piece;
import javax.swing.ImageIcon;

public class KingPiece extends Piece{

	public KingPiece(Player player) {
		super(player); 
		cost = 100;
		name = "King";
		icon = resizeIcon(new ImageIcon("King.png", "King"));
		health = 500;
		attack = 50;
		moveCount = 8;
	}

	@Override
	public Coordinate[] getMoves(Coordinate co) {
		Coordinate[] fish = new Coordinate[moveCount];
		
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
