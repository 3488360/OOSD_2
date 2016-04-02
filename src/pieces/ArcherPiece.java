package pieces;

import model.Coordinate;
import model.Player;

import java.util.List;

public class ArcherPiece extends AbstractPiece {
    /* The Archer is ranged, has medium hp, medium attack and a high move range */

    public ArcherPiece(Player player) {
        super(player);
        maxHealth = 200;
        strength = 90;
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
