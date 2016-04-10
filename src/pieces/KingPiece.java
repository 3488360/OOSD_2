package pieces;


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
		//Coordinate[] fish = new Coordinate[moveCount];
		
		ArrayList<Coordinate> fish = new ArrayList<>();
		fish.add(new Coordinate(co.x - 1, co.y - 1));
		
		fish.add(new Coordinate(co.x - 1, co.y - 1));
		fish.add(new Coordinate(co.x, co.y - 1));
		fish.add(new Coordinate(co.x + 1, co.y - 1));
		fish.add(new Coordinate(co.x - 1, co.y));
		fish.add(new Coordinate(co.x + 1, co.y));
		fish.add(new Coordinate(co.x - 1, co.y + 1));
		fish.add(new Coordinate(co.x, co.y + 1));
		fish.add(new Coordinate(co.x + 1, co.y + 1));
		
		return fish;
	}

	@Override
	public List<Coordinate> getAttackRange() {
		return null;
	}

}
