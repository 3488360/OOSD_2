package model.pieces;

import model.Player;

public abstract class AbstractPiece implements PieceInterface {

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
    protected String icon;
    private Player player;

    @Override
    public String getIcon() {
        return icon;
    }

    public AbstractPiece(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
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
    public String getName() {
        return name;
    }
}
