package model.pieces;

import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ArcherPiece extends AbstractPiece {
    /* The Archer is ranged, has medium hp, medium attack and a high move range */

    public ArcherPiece(Player player) {
        super(player);
        name = "Archer";
        icon = resizeIcon(new ImageIcon("Archer2.png", "Archer"));
        maxHealth = 200;
        strength = 90;
        cost = 50;
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
