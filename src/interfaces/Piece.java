package interfaces;

import model.Coordinate;
import java.util.List;

public interface Piece {

	public List<Coordinate> getMoves();
	public List<Coordinate> getAttackRange();

	public int getCurrentHealth();
	public int takeDamage(int amount);
	public int getMaxHealth();
	public int getStrength();
	public int getCost();

}
	