package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import interfaces.Piece;

public class BoardLayout {
	private String name;
	private HashMap<Coordinate, Piece> layout;
	
	public BoardLayout (String name) {
		this.name = name;
		layout = new HashMap<Coordinate, Piece>();
	}
	
	public void addPiece(Coordinate co, Piece p) {
		layout.put(co, p);
	}
	
	public Piece getPiece(Coordinate co) {
		return layout.get(co);
	}
	
	public String getName() {
		return name;
	}
	
	public void printLayout() {
	    @SuppressWarnings("rawtypes")
		Iterator it = layout.entrySet().iterator();
	    while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			HashMap.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " " + ((Coordinate) pair.getKey()).y + " = " + ((Piece) pair.getValue()).getName());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	public boolean saveLayout() {
		File file = new File("Layouts.txt");
		FileWriter fw;
		
		try {
		
			if (!file.exists()) {
				file.createNewFile();
			}
			
			fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			writer.write(name);
			writer.write(layout.size());
			
			for (int i = 0; i < 15; i++) {
				for (int a = 0; a < 15; a++) {
					writer.write(i + "," + a + ": " + layout.get(new Coordinate(i, a)).getName());
				}
			}
			
			writer.close();
			
			System.out.println("Saved Layout");
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
}
