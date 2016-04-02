package pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

public class GodPiece extends AbstractPiece {
    /* The God has medium hp, high attack and a high move range */

    public GodPiece(Player player) {
        super(player);
        maxHealth = 200;
        strength = 240;
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
