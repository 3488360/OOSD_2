package model.pieces;

import model.Coordinate;

import java.util.List;

public abstract class DecoratedPiece implements PieceInterface {
    private PieceInterface piece;

    public DecoratedPiece(PieceInterface piece) {
        this.piece = piece;
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
    public int getStrength() {
        return piece.getStrength();
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

    @Override
    public int getDefense() {
        return piece.getDefense();
    }

    public PieceInterface undecorate() {
        return piece;
    }
}