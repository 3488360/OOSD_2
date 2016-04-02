package pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

public class SoldierPiece extends AbstractPiece {
    /* The Soldier has medium hp, medium attack and a medium move range */
    public SoldierPiece(Player player) {
        super(player);
        maxHealth = 200;
        strength = 120;
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
