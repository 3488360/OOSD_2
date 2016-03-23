package pieces;


import model.Coordinate;

import java.util.List;

public class TankPiece extends AbstractPiece {
    /* The Tank has high hp, medium attack and a low move range */
    private static final int MAX_HEALTH = 500;
    private static final int STRENGTH = 90;

    private static final int COST = 10;

    @Override
    public int getCost() {
        return 0;
    }

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
}
