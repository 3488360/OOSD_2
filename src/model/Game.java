package model;

import java.util.Observable;
import java.util.Observer;

import controller.GameController;
import interfaces.*;
import pieces.KingPiece;
import pieces.QueenPiece;
import pieces.TestPiece;
import view.MainUserInterface;

public class Game implements Observer {
	private Player player1;
	private Player player2;
	private Board board;
	private MainUserInterface userInterface;
	private Coordinate currentlySelected = null;
	private Coordinate destinationSelected = null;
	boolean done = false;
	Player turn;
	
	private GameController gameController; 
	 
	public Game() {
//		String p1 = (String) JOptionPane.showInputDialog(null, "Please enter player 1's name:", "Player 1 Name", 3);
//		String p2 = (String) JOptionPane.showInputDialog(null, "Please enter player 2's name:", "Player 2 Name", 3);
		String p1 = "john";
		String p2 = "adi";
		player1 = new Player(p1);
		player2 = new Player(p2);
		board = new Board();
		turn = player1;
		userInterface = new MainUserInterface(board, player1, player2);
		gameController = new GameController(userInterface, this);
		userInterface.addGameController(gameController); 
	}

	public void startGame(){
		boolean gameRunning = true;
//		Before the game begins the players must place their respective pieces 
		if (selectAndPlace(player1)) {
			selectAndPlace(player2);
		}
		gameController.hideSelected();
		gameController.updateBoard();
		while(gameRunning) {
			userInterface.updateTurn(turn);
			while(!done) {
				try {Thread.sleep(500);}
				catch (InterruptedException e) {
					System.out.println("Can't put main thread to sleep");
					e.printStackTrace();
				}
			}
			if (turn == player1)
				turn = player2;
			else 
				turn = player1;
			done = false;
		}
	}
	
	private boolean selectAndPlace(Player p) {
		boolean isReady = false;
		boolean buttonPressed = false;
		Piece piece = null;
		String selectedPiece;
		
		while (!isReady) {
			selectedPiece = userInterface.askPiece(p.getName());
			if (selectedPiece != null) {
				userInterface.updateSelectedPiece(selectedPiece);
				switch (selectedPiece) {
					case "King": piece = new KingPiece(p);
						break;
				
					case "Queen": piece = new QueenPiece(p);
						break;
				
					case "Pawn": 
						break;
						
					case "Wizard":
						break;
						
					case "Archer":
						break;
						
					case "Scout":
						break;
				
					case "Default Layout" : 
						board.setPiece(new Coordinate(7, 14), new TestPiece(player1));
						board.setPiece(new Coordinate(6, 14), new KingPiece(player1));
						board.setPiece(new Coordinate(5, 14), new KingPiece(player1));
						board.setPiece(new Coordinate(8, 14), new KingPiece(player1));
						board.setPiece(new Coordinate(9, 14), new KingPiece(player1));
						board.setPiece(new Coordinate(9, 0), new KingPiece(player2));
						board.setPiece(new Coordinate(8, 0), new KingPiece(player2));
						board.setPiece(new Coordinate(7, 0), new TestPiece(player2));
						board.setPiece(new Coordinate(6, 0), new KingPiece(player2));
						board.setPiece(new Coordinate(5, 0), new KingPiece(player2));
						player1.takePoints(500);
						player2.takePoints(500);
						gameController.updatePoints();
						return false;
				}
				
				while (!buttonPressed) {
					if (currentlySelected != null) {
						buttonPressed = true;
					}
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						System.out.print("Could not put thread to sleep!");
						e.printStackTrace();
					}
				}
				
				if (piece != null) {
					p.takePoints(piece.getCost());
					gameController.updatePoints();
					
					System.out.println("Placing " + piece.getName() + " at " + currentlySelected.x + "-" + currentlySelected.y);
					board.setPiece(currentlySelected, piece);
				}
				
				if (p.getPoints() < 50) {
					isReady = true;
				}
				
				currentlySelected = null;
				buttonPressed = false;
			}
		}
		return true;
	}
	
	public void passCoordinates(Coordinate coor) {
//		determine if the coordinates selected are valid or not 
		if(currentlySelected == null){
			currentlySelected = coor; 
			if (board.getPiece(currentlySelected)!= null) {
				System.out.println("Selected piece belongs to " + board.getPiece(currentlySelected).getName());
				gameController.updateSelectedPiece(board.getPiece(currentlySelected).getName());
//			display all the places the piece can possibly move to			
			} else 
				System.out.println("There is no piece located here");
		}
		else if(sameCoordinates(currentlySelected, coor)){
			currentlySelected = null; 
			destinationSelected = null; 
			gameController.hideSelected();
		}
		else{
			destinationSelected = coor; 
			calculateMove(); 
			currentlySelected = null;
			destinationSelected = null;
			gameController.hideSelected();
		}
	}
	
	private boolean sameCoordinates(Coordinate cur, Coordinate dest){
		if(dest!=null && cur.x == dest.x && cur.y == dest.y) { 
			return true;
		} else {
			return false; 
		}
	}
	
	private void calculateMove() {
		Coordinate[] moves;
		
		if (board.getPiece(currentlySelected) != null) {
			if(board.getPiece(destinationSelected) != null){
				//If it's an attacking move
				System.out.println("Attacking!");
				if (board.getPiece(destinationSelected).getPlayer().equals(turn))
					gameController.message("You are trying to attack your own piece!");
				else 
				{
					int attack;
					int health;
					attack = board.getPiece(currentlySelected).getAttack();
					health = board.getPiece(destinationSelected).getHealth();
					
					if (health > attack) {
						board.getPiece(destinationSelected).setHealth(health-attack);
					} else {
						turn.addPoints((board.getPiece(destinationSelected).getCost()/4)*3);
						board.setPiece(destinationSelected, board.getPiece(currentlySelected));
						board.setPiece(currentlySelected, null);
						System.out.println("Successful move!");
						gameController.updateBoard();
						gameController.updatePoints();
						currentlySelected = null;
						destinationSelected = null;
						done = true;
					}
				}
			}else{
				//If it's moving the piece
				if (turn.equals(board.getPlayer(currentlySelected))) 
				{
					System.out.println("Moving!");
					moves = board.getMovement(board.getPiece(currentlySelected), currentlySelected);
					for (int i = 0; i < board.getPiece(currentlySelected).getMoveCount(); i++){
						if (destinationSelected.x == moves[i].x && destinationSelected.y == moves[i].y)
						{
							board.setPiece(destinationSelected, board.getPiece(currentlySelected));
							board.setPiece(currentlySelected, null);
							System.out.println("Successful move!");
							gameController.updateBoard();
							currentlySelected = null;
							destinationSelected = null;
							done = true;
							break;
						} 
					}
				} 
				else{System.out.println("That is not " + turn.getName() + "'s piece!");}
			}
		} else {
			//If no piece is under first selection
			currentlySelected = null;
			destinationSelected = null;
			System.out.println("Cannot make move");
		}
	}
	
//	userInterface.close(); 

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
