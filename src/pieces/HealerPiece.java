package pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

public class HealerPiece extends AbstractPiece {
    /* The Healer is ranged, has high hp, no attack and a medium move range */

    public HealerPiece(Player player) {
        super(player);
        maxHealth = 100;
        strength = -50;
        cost = 10;
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
