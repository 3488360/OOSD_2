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
        icon = resizeIcon(new ImageIcon("images/Tank.png", "Tank"));
        maxHealth = 500;
        strength = 90;
        cost = 40;
        currentHealth = maxHealth;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();
		
		for(int i=-1; i <= 1; i+=1) {
			for(int j=-1; j <= 1; j+=2) {
				moves.add(new Coordinate(co.x + i, co.y + j));
			}
		}
		
		return moves;
    }

    @Override
    public List<Coordinate> getAttackRange(Coordinate co) {
        return null;
    }

}
