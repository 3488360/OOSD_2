package model.pieces;

import model.Coordinate;

import java.util.List;

public interface PieceInterface {
	public List<Coordinate> getMoves(Coordinate coordinate);
	public List<Coordinate> getAttackRange(Coordinate coordinate);

	public int getCurrentHealth();
	public int takeDamage(int amount);
	public int getMaxHealth();
	public int getStrength();
	public int getCost();
	public String getName();
	public String getPlayerName();
	public String getIcon();
}
	