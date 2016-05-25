package model;

import java.io.Serializable;
import java.util.HashMap;

import model.pieces.PieceInterface;

//Information Expert
public class BoardLayout implements Serializable {
	private static final long serialVersionUID = -3659951589416418116L;
	private String name;
	private HashMap<Coordinate, PieceInterface> layout;
	private String player1Name = null;
	private String player2Name = null;
	private String turn = null;
	private int currentTime = -1;
	private int player1Points = -1;
	private int player2Points = -1;
	private int time = -1;
	private BoardShape boardShape;
	
	public BoardLayout (String name) {
		this.name = name;
		layout = new HashMap<Coordinate, PieceInterface>();
	}
	
	public void addPiece(Coordinate co, PieceInterface p) {
//		System.out.println(name + " Added " + p.getName() + " that belongs to " + p.getPlayerName());
		layout.put(co, p);
	}
	
	public PieceInterface getPiece(Coordinate co) {
//		PieceInterface p = layout.get(co);
//		if (p != null)
//			System.out.println(name + " Loaded " + p.getName() + " that belongs to " + p.getPlayerName());
		return layout.get(co);
	}
	
	public String getName() {
		return name;
	}
	
	public void setPlayers(String player1, String player2) {
		this.player1Name = player1;
		this.player2Name = player2;
	}
	
	public String getPlayer1Name() {
		return player1Name;
	}
	
	public String getPlayer2Name() {
		return player2Name;
	}
	
	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	public String getTurn() {
		return turn;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public int getPlayer1Points() {
		return player1Points;
	}

	public void setPlayer1Points(int player1Points) {
		this.player1Points = player1Points;
	}

	public int getPlayer2Points() {
		return player2Points;
	}

	public void setPlayer2Points(int player2Points) {
		this.player2Points = player2Points;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public BoardShape getBoardShape() {
		return boardShape;
	}

	public void setBoardShape(BoardShape boardShape) {
		this.boardShape = boardShape;
	}

}
