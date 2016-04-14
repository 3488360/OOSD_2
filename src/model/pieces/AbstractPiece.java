package model.pieces;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    protected int currentHealth;
    protected String name;
    protected int cost;
    protected int maxHealth;
    protected int strength;
    protected Icon icon;
    private Player player;

    @Override
    public Icon getIcon() {
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

    protected ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(36, 36, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, 36, 36, null);
        g.dispose();
        ImageIcon newIcon = new ImageIcon(bi);
        return newIcon;
    }
}
