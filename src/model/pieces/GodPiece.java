package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class GodPiece extends AbstractPiece {
    /* The God has medium hp, high attack and a high move range */

    public GodPiece(Player player) {
        super(player);
        name = "God";
        icon = resizeIcon(new ImageIcon("God.png", "God"));
        maxHealth = 200;
        strength = 240;
        cost = 60;
    }

    @Override
    public int getCost() {
        return cost;
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
