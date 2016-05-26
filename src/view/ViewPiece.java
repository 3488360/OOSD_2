package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Stores and returns the icons for each piece. It only needs to draw the icons when they are called upon.
 */
//Flyweight Pattern
public class ViewPiece {
	private HashMap<String, Icon> icons = new HashMap<String, Icon>();
	
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
		
		if (icons.containsKey(name)) {
			return icons.get(name);
		}
		
		icon = resizeIcon(new ImageIcon(loc));
		icons.put(name, icon);
		return icon;
	}
}
