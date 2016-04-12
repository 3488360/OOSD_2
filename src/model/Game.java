package model;

import controller.GameController;
import view.MainUserInterface;

import java.util.List;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private MainUserInterface userInterface;
	private Coordinate currentlySelected = null;
	private Coordinate destinationSelected = null;
	private boolean done = false;
	private Player turn;
	private int timerInt;
	private BoardLayout[] boardLayouts;
	private String layoutName;
	private GameController gameController; 
	 
	public Game(Player player1, Player player2, String layoutName, int timerInt, BoardLayout[] boardLayouts) {
		this.timerInt = timerInt;
		this.boardLayouts = boardLayouts;
		this.layoutName = layoutName;
		this.player1 = player1;
		this.player2 = player2;
		board = new Board();
		turn = player1;
		userInterface = new MainUserInterface(board, player1, player2);
		
		gameController = new GameController(userInterface, this);
		userInterface.addGameController(gameController); 
	}

	public void startGame(){
		boolean gameRunning = true;
		GameTimer timer = new GameTimer(gameController, timerInt);

		/*if (selectAndPlace(player1)) {
			selectAndPlace(player2);
		}*/
		
		setupBoard();
		
		userInterface.hideSelected();
		userInterface.updateBoard();
		
		while (gameRunning) {
			userInterface.updateTurn(turn);
			timer.start();
			
			while(!done) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Can't put main thread to sleep");
					e.printStackTrace();
				}
			}
			
			timer.stop();
			
			if (turn == player1) {
				turn = player2;
			} else {
				turn = player1;
			}
			
			done = false;
			
			if (checkWinning()) {
				gameRunning = false;
			}
		}
	}
	
	private void setupBoard() {
		int b;
		Coordinate co;
		
		for (b = 0; b < boardLayouts.length; b++) {
			if (layoutName.equals(boardLayouts[b].getName())) {
				break;
			}
		}
		
		for (int i = 0; i < 15; i++) {
			for (int a = 0; a < 15; a++) {
				co = new Coordinate(i, a);
				if (boardLayouts[b].getPiece(co) != null) {
					board.setPiece(co, boardLayouts[b].getPiece(co));
				}
			}
		}
	}

	private boolean checkWinning() {
		return false;
	}

	
	//Leave this here for now
	
	/*private boolean selectAndPlace(Player p) {
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
				
					case "Archer": piece = new ArcherPiece(p);
						break;
						
					case "God": piece = new GodPiece(p);
						break;
						
					case "Healer": piece = new HealerPiece(p);
						break;
						
					case "Soldier": piece = new SoldierPiece(p);
						break;
						
					case "Tank": piece = new TankPiece(p);
						break;
						
					case "Wizard": piece = new WizardPiece(p);
						break;
				
					case "Default Layout" : 
						board.setPiece(new Coordinate(7, 14), new TestPiece(player1));
						board.setPiece(new Coordinate(6, 14), new KingPiece(player1));
						board.setPiece(new Coordinate(5, 14), new ArcherPiece(player1));
						board.setPiece(new Coordinate(8, 14), new SoldierPiece(player1));
						board.setPiece(new Coordinate(9, 14), new KingPiece(player1));
						board.setPiece(new Coordinate(9, 0), new KingPiece(player2));
						board.setPiece(new Coordinate(8, 0), new QueenPiece(player2));
						board.setPiece(new Coordinate(7, 0), new TestPiece(player2));
						board.setPiece(new Coordinate(6, 0), new KingPiece(player2));
						board.setPiece(new Coordinate(5, 0), new KingPiece(player2));
						player1.takePoints(500);
						player2.takePoints(500);
						userInterface.updatePoints();
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
					userInterface.updatePoints();
					
					System.out.println("Placing " + piece.getName() + " at " + currentlySelected.x + "-" + currentlySelected.y);
					board.setPiece(currentlySelected, piece);
				}
				
				if (p.getPoints() < 30) {
					isReady = true;
				}
				
				currentlySelected = null;
				buttonPressed = false;
			}
		}
		return true;
	}*/
	

	
	public void addController(GameController gc){
		gameController = gc; 
	}
	
	public void passCoordinates(Coordinate coor){
//		determine if the coordinates selected are valid or not 
		if(currentlySelected == null){ 
			if (board.getPiece(coor)!= null && turn == board.getPiece(coor).getPlayer()){
				currentlySelected = coor;
				gameController.updateSelectedPiece(board.getPiece(currentlySelected).getName());
//			display all the places the piece can possibly move to
				List<Coordinate> co = board.getPiece(currentlySelected).getMoves(currentlySelected);
				Cell[][] cell = board.getAllCells(); 
				for(int i = 0; i < co.size(); i++){
					if(co.get(i).x < board.getWidth() && co.get(i).x >= 0 && co.get(i).y < board.getHeight() && co.get(i).y>= 0){
						if(board.getAllCells()[co.get(i).x][co.get(i).y].getVisible() == true){
							cell[co.get(i).x][co.get(i).y].setCanMoveTo(true);
						}
					}
				}
				gameController.updateBoard();
			} else {
				System.out.println("There is no piece located here or this piece is not yours");
			}
			
			
		}
		else if(sameCoordinates(currentlySelected, coor)){
			currentlySelected = null; 
			destinationSelected = null; 
			gameController.hideSelected();
			turnAllMoveableSquaresOff();
		}
		else{
			destinationSelected = coor; 
			calculateMove(); 
			currentlySelected = null;
			destinationSelected = null;
			gameController.hideSelected();
			turnAllMoveableSquaresOff();

		}
		
		gameController.updateBoard();
	}
	
	private boolean sameCoordinates(Coordinate cur, Coordinate dest){
		if(dest!=null && cur.x == dest.x && cur.y == dest.y) { 
			return true;
		} else {
			return false; 
		}
	}
	private void turnAllMoveableSquaresOff(){
		for(int i = 0; i < board.getHeight(); i++){
			for(int j = 0; j < board.getWidth(); j++){
				board.getAllCells()[i][j].setCanMoveTo(false);
			}
		}
	}	

	private void calculateMove() {
		//List<Coordinate> moves;
		
		if (board.getPiece(currentlySelected) != null) {
			if (board.getPiece(destinationSelected) != null) {
				//If it's an attacking move
				System.out.println("Attacking!");
				if (board.getPiece(destinationSelected).getPlayer().equals(turn)) {
					userInterface.message("You are trying to attack your own piece!");
				} else {
					int attack;
					int health;
					attack = board.getPiece(currentlySelected).getStrength();
					health = board.getPiece(destinationSelected).getCurrentHealth();
					
					if (health > attack) {
						board.getPiece(destinationSelected).takeDamage(attack);
					} else {
						turn.addPoints((board.getPiece(destinationSelected).getCost()/4)*3);
						board.setPiece(destinationSelected, board.getPiece(currentlySelected));
						board.setPiece(currentlySelected, null);
						System.out.println("Successful move!");
						userInterface.updateBoard();
						userInterface.updatePoints();
						currentlySelected = null;
						destinationSelected = null;
						done = true;
					}
				}
				
			} else {
				//If it's moving the piece
				if (turn.equals(board.getPlayer(currentlySelected))) {
					System.out.println("Moving!");
//					moves = board.getMovement(board.getPiece(currentlySelected), currentlySelected);
					if(board.getAllCells()[destinationSelected.x][destinationSelected.y].canMoveTo == true){
						board.setPiece(destinationSelected, board.getPiece(currentlySelected));
						board.setPiece(currentlySelected, null);
						userInterface.updateBoard();
						currentlySelected = null;
						destinationSelected = null;
						done = true;
					}
				} else
					System.out.println("That is not " + turn.getName() + "'s piece!");
			}
		} else {
			//If no piece is under first selection
			currentlySelected = null;
			destinationSelected = null;
		}
	}
	
	public void close(){
		userInterface.getContentPane().removeAll();
		userInterface.setVisible(false);
		userInterface = null;
	}
}