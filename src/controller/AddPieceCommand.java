package controller;

import model.*;
import model.pieces.PieceInterface;

public class AddPieceCommand implements Command  {
	
	private Coordinate desiredCoordinate; 
	private Game game; 
	private GameController gc; 
	private String pieceName; 
	private Player currentTurn;
	private int pieceCost = 0; 
	public AddPieceCommand(Game game, GameController gc, String pieceName, Coordinate desiredCoordinate){
		this.game = game; 
		this.gc = gc; 
		this.pieceName = pieceName;
		this.desiredCoordinate = desiredCoordinate; 
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		PieceInterface p = game.addPiece(pieceName, desiredCoordinate);
		String turn = game.getTurn();
		 
		if(turn.equals(game.getPlayer1().getName())){
			 currentTurn = game.getPlayer1(); 
		}
		else{
			currentTurn = game.getPlayer2(); 
		}
		if(currentTurn.getPoints() >= p.getCost()) {
			game.getBoard().setPiece(desiredCoordinate, p);
			currentTurn.setPoints(currentTurn.getPoints() - p.getCost());
			pieceCost = p.getCost(); 
			gc.updatePoints();
		} else {
			gc.message("Not enough points to buy that piece.");
		}
		gc.updateBoard();
	}

	@Override
	public void undo(){ 
		// TODO Auto-generated method stub
		game.getBoard().setPiece(desiredCoordinate, null);
		currentTurn.setPoints(currentTurn.getPoints() + pieceCost);
		gc.updatePoints();
	}

}
