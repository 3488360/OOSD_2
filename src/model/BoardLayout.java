package model;

import java.util.HashMap;

import model.pieces.PieceInterface;

//Information Expert. Stores the layout for different board layouts in a coordinate/piece hashmap.
public class BoardLayout {
	private String name;
	private HashMap<Coordinate, PieceInterface> layout;
	
	public BoardLayout (String name) {
		this.name = name;
		layout = new HashMap<Coordinate, PieceInterface>();
	}
	
	public void addPiece(Coordinate co, PieceInterface p) {
		layout.put(co, p);
	}
	
	public PieceInterface getPiece(Coordinate co) {
		return layout.get(co);
	}
	
	public String getName() {
		return name;
	}
}
