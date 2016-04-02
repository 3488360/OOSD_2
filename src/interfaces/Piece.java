package interfaces;

import model.Coordinate;
import model.Player;

import javax.swing.*;
import java.util.List;

public interface Piece {

	public List<Coordinate> getMoves(Coordinate coordinate);
	public List<Coordinate> getAttackRange();

	public int getCurrentHealth();
	public int takeDamage(int amount);
	public int getMaxHealth();
	public int getStrength();
	public int getCost();
	public String getName();

	public Player getPlayer();

	public Icon getIcon();


}
	