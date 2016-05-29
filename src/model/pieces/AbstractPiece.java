package model.pieces;

import java.io.Serializable;

public abstract class AbstractPiece implements PieceInterface, Serializable {
	private static final long serialVersionUID = -6084960025448091318L;
	/*
    * Side 1:
    *  - Tank
    *  - Wizard
    *  - Soldier
    *
    * Side 2:
    *  - Archer
    *  - God
    *  - Healer
    *  */
    protected int currentHealth;
    protected String name;
    protected int cost;
    protected int maxHealth;
    protected int strength;
    protected int defense;
    protected String icon;
    protected String playerName;

    @Override
    public String getIcon() {
        return icon;
    }

    public AbstractPiece(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public int takeDamage(int amount) {
        currentHealth -= amount;
        return currentHealth;
    }
    
    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public String getName() {
        return name;
    }
}
