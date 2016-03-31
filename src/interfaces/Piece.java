package interfaces;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.Coordinate;
import model.Player;

public abstract class Piece{
//	the piece will have a player attribute which tells which player it belongs to 
	protected Player player; 
	protected Coordinate coordinate;
	protected int cost;
	protected String name;
	protected ImageIcon icon;
	protected int health;
	protected int attack;
	protected int moveCount;
	
	public Piece (Player player) {
		this.player = player;
	}
	
	public abstract Coordinate[] getMoves(Coordinate co);
	
	public int getCost() {
		return cost;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setPlayer(Player player){
		this.player = player; 
	}

	public String getName() {
		return name;
	}

	public Icon getIcon() {
		return icon;
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

	public Player getPlayer() {
		return player;
	}

	public void setHealth(int h) {
		health = h;
	}

	public int getMoveCount() {
		return moveCount;
	}
}
	