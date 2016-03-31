package pieces;

import javax.swing.ImageIcon;

import interfaces.Piece;
import model.Coordinate;
import model.Player;

public class TestPiece extends Piece {

	public TestPiece(Player player) {
		super(player); 
		cost = 0;
		name = "TestPiece";
		icon = resizeIcon(new ImageIcon("K.png", "Test Piece"));
		health = 10000;
		attack = 10000;
		moveCount = 225;
	}

	@Override
	public Coordinate[] getMoves(Coordinate co) {
		Coordinate[] fish = new Coordinate[moveCount];
		int b = 0;
		
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				fish[b] = new Coordinate(i, a);
				b++;
				System.out.println("b is " + b);
			}
		}
		
		return fish;
	}

}
