package controller;

import java.util.Scanner;

import model.Coordinate;
import interfaces.CoordinateControllerInterface;

public class CoordinateController implements CoordinateControllerInterface {
//	responsible for getting coordinates
	public Coordinate getCoordinate() {
		String coordinatesinString; 
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Please enter the coordinates of the piece: ");
		coordinatesinString = scanner.next(); 
//		break the string into tokens and pass as integer 
		scanner.close();
		return null;
	}
 
	
}
