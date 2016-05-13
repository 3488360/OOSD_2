package controller;



import view.ViewMain;
import model.Board;
import model.BoardLayout;
import model.Coordinate;
import model.Game;
import model.GameTimer;

public class gameState implements Command {
	private Game game;
	private PlayerController playerController;
	BoardLayout state; 
	Board board;
	private ButtonController buttonController;
	private GameController gameController;
	public gameState(Game game, PlayerController playerController){
		this.game = game; 
		this.playerController = playerController;
		BoardLayout save = new BoardLayout("");
		board = game.getBoard();
		Coordinate co;
		int totalNumberOfPieces = 0;
		
		for (int a = 0; a < board.getWidth(); a++) {
			for (int b = 0; b < board.getHeight(); b++) {
				co = new Coordinate(a, b);
				if (board.getPiece(co) != null) {
					save.addPiece(co, board.getPiece(co));
					totalNumberOfPieces++; 
				}
			}
		}
		System.out.println(totalNumberOfPieces);
		save.setPlayers(playerController.getPlayer1().getName(), playerController.getPlayer2().getName());
		save.setPlayer1Points(playerController.getPlayerPoints("player1"));
		save.setPlayer2Points(playerController.getPlayerPoints("player2"));
		save.setCurrentTime(game.getTime());
//		save.setTurn(game.getTurn().getName());
		save.setTime(game.getInitTime());
		state = save;
	}	
	@Override
	public void execute(){
		new ViewMain(gameController, board, playerController, buttonController, new GameTimer(state.getCurrentTime()));
		game.setupBoard(state);
	}

	@Override
	public void undo() {
		
				
	}

	public void setButtonController(ButtonController buttonController){
		this.buttonController = buttonController;
	}
	
	public void setGameController(GameController gameController){
		this.gameController = gameController; 
	}

}
