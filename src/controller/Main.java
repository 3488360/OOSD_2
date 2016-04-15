package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import model.*;
import model.pieces.*;
import view.ViewStart;

public class Main {
	private static Game game;
	private static boolean waiting;
	private static Player player1;
	private static Player player2;
	private static String boardLayout;
	private static int timer;
	private static BoardLayout[] boardLayouts;
	
	public static void main(String[] args) {
		boardLayouts = getBoardLayouts();
		new ViewStart(boardLayouts);
		waiting = true;
		
		while (waiting) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Cannot put main thread to sleep");
				e.printStackTrace();
			}
		}
		
		game = Game.getInstance();
		game.startGame(player1, player2, boardLayout, timer, boardLayouts);
	}

	public static void startGame(String player1Name, String player2Name, String boardLayoutName, int timerNum) {
		player1.setPlayerName(player1Name);
		player2.setPlayerName(player2Name);
		boardLayout = boardLayoutName;
		timer = timerNum;
		waiting = false;
	}
	
	private static BoardLayout[] getBoardLayouts() {
		player1 = new Player("player1");
		player2 = new Player("player2");
		Player p;
		String currentLine;
		BoardLayout[] layouts = null;
		FileReader file;
		BufferedReader reader;
		StringTokenizer tokenizer;
		StringTokenizer tokenizer2;
		int i = 0;
		int x;
		int y;
		int amount;
		String pieceName;
		
		try {
			file = new FileReader("Layouts.txt");
			reader = new BufferedReader(file);
			
			if ((currentLine = reader.readLine()) != null) {
				layouts = new BoardLayout[Integer.parseInt(currentLine)];
				
				while ((currentLine = reader.readLine()) != null && i < layouts.length) {
					layouts[i] = new BoardLayout(currentLine);
					currentLine = reader.readLine();
					amount = Integer.parseInt(currentLine);
					
					currentLine = reader.readLine();
					player1.setPoints(Integer.parseInt(currentLine));
					currentLine = reader.readLine();
					player2.setPoints(Integer.parseInt(currentLine));
					
					for (int a = 0; a < amount; a++) {
						currentLine = reader.readLine();
						
						if (currentLine.equals("player1")) {
							p = player1;
						} else {
							p = player2;
						}
						
						currentLine = reader.readLine();
						
						tokenizer = new StringTokenizer(currentLine, ":");
						tokenizer2 = new StringTokenizer(tokenizer.nextToken(), ",");
						
						x = Integer.parseInt(tokenizer2.nextToken());
						y = Integer.parseInt(tokenizer2.nextToken());
						pieceName = tokenizer.nextToken();
						
						layouts[i].addPiece(new Coordinate(x, y), getPiece(pieceName, p));
					}
					i++;
				}
				
			} else {
				reader.close();
				return null;
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return layouts;
	}

	private static PieceInterface getPiece(String name, Player p) {
		switch (name) {
			case "King": return new KingPiece(p);
			
			case "Archer": return new ArcherPiece(p);
			
			case "God": return new GodPiece(p);
			
			case "Healer": return new HealerPiece(p);
			
			case "Queen": return new QueenPiece(p);
			
			case "Soldier": return new SoldierPiece(p);
			
			case "Tank": return new TankPiece(p);
			
			case "Test": return new TestPiece(p);
			
			case "Wizard": return new WizardPiece(p);
		}
		
		return null;
	}
}
