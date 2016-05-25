package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Stores and returns the icons for each piece. It only needs to draw the icons when they are called upon.
 */
public class ViewPiece {
	private Icon archer;
	private Icon god;
	private Icon healer;
	private Icon king;
	private Icon queen;
	private Icon soldier;
	private Icon tank;
	private Icon test;
	private Icon wizard;
	
    /**
     * Resizes the icons stored in the files to the correct size needed to display on the board.
     * 
     * @param icon - The icon that needs to be resized.
     * @return The correct sized icon that was given.
     */
    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(36, 36, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, 32, 32, null);
        g.dispose();
        ImageIcon newIcon = new ImageIcon(bi);
        return newIcon;
    }

	/**
	 * Returns the icon that belongs to the given piece. If the icon hasn't been requested yet, it will create the icon.
	 * 
	 * @param name - The name of the piece.
	 * @param loc - The location of the piece's icon.
	 * @return The icon for that piece.
	 */
	public Icon getIcon(String name, String loc) {
		Icon icon = null;
		
		//Makes sure that it stores the image for each piece only once
		switch (name) {
			case "Archer":
				if (archer == null) {
					archer = resizeIcon(new ImageIcon(loc));
				}
				icon = archer;
				break;
			case "Healer":
				if (healer == null) {
					healer = resizeIcon(new ImageIcon(loc));
				}
				icon = healer;
				break;
			case "God":
				if (god == null) {
					god = resizeIcon(new ImageIcon(loc));
				}
				icon = god;
				break;
			case "King":
				if (king == null) {
					king = resizeIcon(new ImageIcon(loc));
				}
				icon = king;
				break;
			case "Queen":
				if (queen == null) {
					queen = resizeIcon(new ImageIcon(loc));
				}
				icon = queen;
				break;
			case "Soldier":
				if (soldier == null) {
					soldier = resizeIcon(new ImageIcon(loc));
				}
				icon = soldier;
				break;
			case "Tank":
				if (tank == null) {
					tank = resizeIcon(new ImageIcon(loc));
				}
				icon = tank;
				break;
			case "Test":
				if (test == null) {
					test = resizeIcon(new ImageIcon(loc));
				}
				icon = test;
				break;
			case "Wizard":
				if (wizard == null) {
					wizard = resizeIcon(new ImageIcon(loc));
				}
				icon = wizard;
				break;
		}
		
		return icon;
	}
}
