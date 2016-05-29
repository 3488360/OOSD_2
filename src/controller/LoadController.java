package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.BoardLayout;

public class LoadController {
	//Loads the .layout files from the program's directory
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
	
	//Loads the BoardLayout from the .save file
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
