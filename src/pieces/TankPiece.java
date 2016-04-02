package pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

public class TankPiece extends AbstractPiece {
    /* The Tank has high hp, medium attack and a low move range */


    public TankPiece(Player player) {
        super(player);
        maxHealth = 500;
        strength = 90;
        cost = 10;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate coordinate) {
        return null;
    }

    @Override
    public List<Coordinate> getAttackRange() {
        return null;
    }

}
