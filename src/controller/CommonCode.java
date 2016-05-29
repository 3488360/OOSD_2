package controller;

import javax.swing.JOptionPane;

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

public class CommonCode {
	/**
	 * A function to output a simple message dialog box to the user.
	 * 
	 * @param message - The message to be displayed.
	 */
	public static void message(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static PieceInterface getPiece(String name, String p) {
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
