package interfaces;

import java.util.*;

import model.Cell;
import model.Coordinate;
import model.Player;

public abstract class Piece{
//	the piece will have a player attribute which tells which player it belongs to 
	Player player; 
	Coordinate coordinate = new Coordinate(); 
	
	public List<Coordinate> getMoves(){
		return null;
			
	}
	
	
	public  void moveTo(Cell cell){}
	
	public abstract int getCost(); 
	
	public void setPlayer(Player player){
		this.player = player; 
	}
}
	