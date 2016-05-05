package model.pieces;


import model.Coordinate;

import java.util.List;

/*Doubled Attack Piece follows the Decorator pattern.
* It encapsulates a Piece and decorates the getStrength method */
public class DoubledAttackPiece implements PieceInterface {

    private PieceInterface piece;

    public DoubledAttackPiece(PieceInterface piece) {
        this.piece = piece;
    }

    /* Decorated method. Doubles the attack of a piece */
    @Override
    public int getStrength() {
        return piece.getStrength() * 2;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate coordinate) {
        return piece.getMoves(coordinate);
    }

    @Override
    public List<Coordinate> getAttackRange(Coordinate coordinate) {
        return piece.getAttackRange(coordinate);
    }

    @Override
    public int getCurrentHealth() {
        return piece.getCurrentHealth();
    }

    @Override
    public int takeDamage(int amount) {
        return piece.takeDamage(amount);
    }

    @Override
    public int getMaxHealth() {
        return piece.getMaxHealth();
    }

    @Override
    public int getCost() {
        return piece.getCost();
    }

    @Override
    public String getName() {
        return piece.getName();
    }

    @Override
    public String getPlayerName() {
        return piece.getPlayerName();
    }


    @Override
    public String getIcon() {
        return piece.getIcon();
    }
}
