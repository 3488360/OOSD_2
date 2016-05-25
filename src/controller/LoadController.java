package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.BoardLayout;
import model.Coordinate;
import model.pieces.ArcherPiece;
import model.pieces.GodPiece;
import model.pieces.HealerPiece;
import model.pieces.KingPiece;
import model.pieces.PieceInterface;
import model.pieces.QueenPiece;
import model.pieces.SoldierPiece;
import model.pieces.TankPiece;
import model.pieces.TestPiece;
import model.pieces.WizardPiece;

public class LoadController {
	public BoardLayout[] loadLayouts2() {
		final String FILE_DIR = System.getProperty("user.dir");
		List<BoardLayout> layouts = new ArrayList<BoardLayout>();
		BoardLayout[] layouts2;
		File directory = new File(FILE_DIR);
		ObjectInputStream objectStream = null;
		FileInputStream fileStream;
		BoardLayout newLayout;
		
		for (File file : directory.listFiles()) {
			if (file.isFile() && (file.getName().endsWith(".layout"))) {
				try {
					fileStream = new FileInputStream(file);
					objectStream = new ObjectInputStream(fileStream);
					newLayout = (BoardLayout) objectStream.readObject();
					layouts.add(newLayout);
				} catch (IOException | ClassNotFoundException e) {
					System.out.print("Issue with loading layouts from dir " + FILE_DIR);
					e.printStackTrace();
					return null;
				} finally {
					try {
						objectStream.close();
					} catch (IOException e) {
						System.out.println("Could not close object stream");
					}
				}

			}
		}
		
		layouts2 = new BoardLayout[layouts.size()];
		layouts2 = layouts.toArray(layouts2);
		
		return layouts2;
	}
	
	public BoardLayout[] loadLayouts() {
		String p;
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
//					player1.setPoints(Integer.parseInt(currentLine));
					currentLine = reader.readLine();
//					player2.setPoints(Integer.parseInt(currentLine));
					
					for (int a = 0; a < amount; a++) {
						currentLine = reader.readLine();
						
						if (currentLine.equals("player1")) {
							p = "player1";
						} else {
							p = "player2";
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

	private static PieceInterface getPiece(String name, String p) {
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
	
	public List<Object> loadGame(File file) {
		FileInputStream input;
		ObjectInputStream input2 = null;
		BoardLayout layout;
		List<Object> list = new ArrayList<Object>();
		
		try {
			input = new FileInputStream(file);
			input2 = new ObjectInputStream(input);
			layout = (BoardLayout) input2.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				input2.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		list.add(layout.getPlayer1Name());
		list.add(layout.getPlayer2Name());
		list.add(layout.getTime());
		list.add(layout);
		
		return list;
	}
}
