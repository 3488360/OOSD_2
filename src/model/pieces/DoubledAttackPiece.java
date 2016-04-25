package model.pieces;


import model.Coordinate;
import model.Player;

import java.util.List;

/*Doubled Attack Piece follows the Decorator pattern.
* It encapsulates a Piece and decorates the getStrength method */
public class DoubledAttackPiece implements PieceInterface {

    private PieceInterface decorated;

    public DoubledAttackPiece(PieceInterface decorated) {
        this.decorated = decorated;
    }

    /* Decorated method. Doubles the attack of a piece */
    @Override
    public int getStrength() {
        return decorated.getStrength() * 2;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate coordinate) {
        return decorated.getMoves(coordinate);
    }

    @Override
    public List<Coordinate> getAttackRange(Coordinate coordinate) {
        return decorated.getAttackRange(coordinate);
    }

    @Override
    public int getCurrentHealth() {
        return decorated.getCurrentHealth();
    }

    @Override
    public int takeDamage(int amount) {
        return decorated.takeDamage(amount);
    }

    @Override
    public int getMaxHealth() {
        return decorated.getMaxHealth();
    }

    @Override
    public int getCost() {
        return decorated.getCost();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    public Player getPlayer() {
        return decorated.getPlayer();
    }

    @Override
    public String getIcon() {
        return decorated.getIcon();
    }
}
