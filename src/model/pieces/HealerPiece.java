package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class HealerPiece extends AbstractPiece {
    /* The Healer is ranged, has high hp, no attack and a medium move range */

    public HealerPiece(Player player) {
        super(player);
        name = "Healer";
        icon = resizeIcon(new ImageIcon("Healer.png", "Healer"));
        maxHealth = 100;
        strength = -50;
        cost = 70;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
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
    public List<Coordinate> getAttackRange(Coordinate co) {
        return null;
    }

}
