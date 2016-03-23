package pieces;


import model.Coordinate;

import java.util.List;

public class HealerPiece extends AbstractPiece {
    /* The Healer is ranged, has high hp, no attack and a medium move range */
    private static final int MAX_HEALTH = 100;
    private static final int STRENGTH = -50;
    private static final int COST = 10;
    @Override
    public List<Coordinate> getMoves() {
        return null;
    }

    @Override
    public List<Coordinate> getAttackRange() {
        return null;
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getCost() {
        return COST;
    }
}
