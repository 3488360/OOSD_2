package model.pieces;

import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class SoldierPiece extends AbstractPiece {
    /* The Soldier has medium hp, medium attack and a medium move range */
    public SoldierPiece(Player player) {
        super(player);
        name = "Soldier";
        icon = resizeIcon(new ImageIcon("Soldier2.png", "Soldier"));
        maxHealth = 200;
        strength = 120;
        cost = 30;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate co) {
		List<Coordinate> moves = new ArrayList<Coordinate>();

        for(int i=-2; i <= 2; i++) {
            for(int j=-2; j <= 2; j++) {
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
