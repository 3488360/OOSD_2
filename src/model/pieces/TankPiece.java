package model.pieces;

import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class TankPiece extends AbstractPiece {
    /* The Tank has high hp, medium attack and a low move range */


    public TankPiece(Player player) {
        super(player);
        name = "Tank";
        icon = resizeIcon(new ImageIcon("Tank.png", "Tank"));
        maxHealth = 500;
        strength = 90;
        cost = 40;
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
