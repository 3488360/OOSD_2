package controller.commands;

import controller.CommonCode;
import controller.PlayerController;
import model.*;
import model.pieces.PieceInterface;

public class AddPieceCommand implements Command  {
	private Coordinate desiredCoordinate; 
	private boolean undoAbleMove = false; 
	private Game game; 
	private String pieceName; 
	private String currentTurn;
	private int pieceCost = 0;
	private PlayerController playerController = PlayerController.getInstance();
	
	public AddPieceCommand(Game game, String pieceName, Coordinate desiredCoordinate){
		this.game = game;  
		this.pieceName = pieceName;
		this.desiredCoordinate = desiredCoordinate; 
	}
	
	@Override
	public void execute() {
		PieceInterface p = CommonCode.getPiece(pieceName, game.getTurn());
		 
		if(playerController.getPlayerPoints(currentTurn) >= p.getCost()) {
			game.getBoard().setPiece(desiredCoordinate, p);
			playerController.setPoints(currentTurn, playerController.getPoints(currentTurn) - p.getCost());
			pieceCost = p.getCost(); 
			game.updatePoints();
			undoAbleMove = true; 
		} else {
			CommonCode.message("Not enough points to buy that piece.");
		}
		game.updateBoard();
	}

	@Override
	public void undo(){ 
		game.getBoard().setPiece(desiredCoordinate, null);
		playerController.setPoints(currentTurn, playerController.getPoints(currentTurn) + pieceCost);
		game.updatePoints();
	}

	@Override
	public boolean CanUndo() {
		return undoAbleMove;
	}

}
