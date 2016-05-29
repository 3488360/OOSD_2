package controller.commands;

import controller.CommonCode;
import controller.GameController;
import controller.PlayerController;
import model.*;
import model.pieces.PieceInterface;

public class AddPieceCommand implements Command  {
	private Coordinate desiredCoordinate; 
	private boolean undoAbleMove = false; 
	private GameController gameController; 
	private String pieceName; 
	private String currentTurn;
	private int pieceCost = 0;
	private PlayerController playerController = PlayerController.getInstance();
	
	public AddPieceCommand(GameController gameController, String pieceName, Coordinate desiredCoordinate){
		this.gameController = gameController;  
		this.pieceName = pieceName;
		this.desiredCoordinate = desiredCoordinate;
	}
	
	@Override
	public void execute() {
		PieceInterface p = CommonCode.getPiece(pieceName, gameController.getTurn());
		currentTurn = gameController.getTurn();
		
		if(playerController.getPlayerPoints(currentTurn) >= p.getCost()) {
			gameController.getBoard().setPiece(desiredCoordinate, p);
			playerController.setPoints(currentTurn, playerController.getPoints(currentTurn) - p.getCost());
			pieceCost = p.getCost(); 
			gameController.updatePoints();
			undoAbleMove = true; 
			gameController.setDone(true);
		} else {
			CommonCode.message("Not enough points to buy that piece.");
		}
		gameController.updateBoard();
	}

	@Override
	public void undo(){ 
		gameController.getBoard().setPiece(desiredCoordinate, null);
		playerController.setPoints(currentTurn, playerController.getPoints(currentTurn) + pieceCost);
		gameController.updatePoints();
	}

	@Override
	public boolean CanUndo() {
		return undoAbleMove;
	}

}
