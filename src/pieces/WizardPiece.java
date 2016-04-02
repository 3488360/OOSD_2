package pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

public class WizardPiece extends AbstractPiece{
    /* The Wizard is ranged, has low hp, high attack and a medium move range */
    public WizardPiece(Player player) {
        super(player);
        maxHealth = 100;
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
