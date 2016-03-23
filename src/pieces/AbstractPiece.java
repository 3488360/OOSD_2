package pieces;


import interfaces.Piece;

public abstract class AbstractPiece implements Piece {

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
    private int currentHealth;

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public int takeDamage(int amount) {
        currentHealth -= amount;
        return currentHealth;
    }
}
