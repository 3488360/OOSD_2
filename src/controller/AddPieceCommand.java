package controller;

import model.*;

public class AddPieceCommand implements Command  {
	
	private Coordinate desiredCoordinate; 
	private Game game; 
	private GameController gc; 
	String pieceName; 
	public AddPieceCommand(Game game, GameController gc, String pieceName, Coordinate desiredCoordinate){
		this.game = game; 
		this.gc = gc; 
		this.pieceName = pieceName;
		this.desiredCoordinate = desiredCoordinate; 
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		game.addPiece(pieceName, desiredCoordinate);
		gc.updateBoard();
	}

	@Override
	public void undo() 
		// TODO Auto-generated method stub
		gc.
	}

}
