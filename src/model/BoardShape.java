package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoardShape implements Serializable {
	private static final long serialVersionUID = -5073314603711105062L;
	private String name;
	private int x;
	private int y;
	private Cell cells[][];
	
	public BoardShape(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.cells = new Cell[x][y];
		
		if (name.equals("Plus")) {
			createPlus();
		} else if (name.equals("Rectangle")) {
			createRectangle();
		} else {
			return;
		}
	}
	
	public BoardShape(String name, int r) {
		if (!name.equals("Circle"))
			return;
		
		this.name = name;
		this.x = (2*r) + 1;
		this.y = (2*r) + 1;
		this.cells = new Cell[x][y];
		
		createCircle();
	}
	
	private void createPlus() {
		int x2 = 0;
		int y2 = 0;
		int isOverX = 0;
		int isOverY = 0;
		int x3 = 0;
		int y3 = 0;
		
		if (x % 3 == 0) {
			x2 = x/3;
		} else if (x % 3 == 1) {
			x2 = (x-1)/3;
			isOverX = 1;
		} else if (x % 3 == 2) {
			x2 = (x-2)/3;
			isOverX = 2;
		}
		
		if (y % 3 == 0) {
			y2 = y/3;
		} else if (y % 3 == 1) {
			y2 = (y-1)/3;
			isOverY = 1;
		} else if (y % 3 == 2) {
			y2 = (y-2)/3;
			isOverY = 2;
		}
		
		//System.out.println("x is = " + x + " y is = " + y + " x2 = " + x2 + "     y2 = " + y2 + "   isOverX = " + isOverX + " isOverY = " + isOverY);
		for (int i = 0; i < x; i++) {
			for (int a = 0; a < y; a++) {
				cells[i][a] = new Cell(i, a, true);
			}
		}
		
		List<boolean[][]> layout = new ArrayList<boolean[][]>();
		
		if (isOverX == 0 && isOverY == 0) {
			for (int i = 0; i < 9; i++) {
				layout.add(new boolean[x2][y2]);
			}
		} else if (isOverX == 0 && isOverY == 1) {
			y3 = 5;
			for (int i = 0; i < 9; i++) {
				if (i == 3 || i == 4 || i == 5) {
					layout.add(new boolean[x2][y2 + 1]);
				} else {
					layout.add(new boolean[x2][y2]);
				}
			}
		} else if (isOverX == 0 && isOverY == 2) {
			//System.out.println("0, 2");
			y3 = 1;
			for (int i = 0; i < 9; i++) {
				if (i == 1 || i == 4 || i == 7) {
					layout.add(new boolean[x2][y2]);
					//System.out.println("i is " + i + " layout is " + layout.get(i)[0].length);
				} else {
					layout.add(new boolean[x2][y2 + 1]);
					//System.out.println("i is " + i + " layout is " + layout.get(i)[0].length);
				}
			}
		} else if (isOverX == 1 && isOverY == 0) {
			x3 = 5;
			for (int i = 0; i < 9; i++) {
				if (i == 1 || i == 4 || i == 7) {
					layout.add(new boolean[x2 + 1][y2]);
				} else {
					layout.add(new boolean[x2][y2]);
				}
			}
		} else if (isOverX == 1 && isOverY == 1) {
			x3 = 5;
			y3 = 5;
			for (int i = 0; i < 9; i++) {
				if (i == 0 || i == 2 || i == 6 || i == 8) {
					layout.add(new boolean[x2][y2]);
				} else if (i == 1 || i == 7){
					layout.add(new boolean[x2][y2 + 1]);
				} else if (i == 3 || i == 5) {
					layout.add(new boolean[x2 + 1][y2]);
				} else {
					layout.add(new boolean[x2 + 1][y2 + 1]);
				}
			}
		} else if (isOverX == 1 && isOverY == 2) {
			y3++;
			x3 = 5;
			for (int i = 0; i < 9; i++) {
				if (i == 1 || i == 7) {
					layout.add(new boolean[x2 + 1][y2 + 1]);
				} else if (i == 0 || i == 2  || i == 6 || i == 8){
					layout.add(new boolean[x2][y2 + 1]);
				} else if (i == 4) {
					layout.add(new boolean[x2 + 1][y2]);
				} else {
					layout.add(new boolean[x2][y2]);
				}
			}
		} else if (isOverX == 2 && isOverY == 0) {
			//System.out.println("I'm here");
			x3 = 1;
			for (int i = 0; i < 9; i++) {
				if (i == 1 || i == 4 || i == 7) {
					layout.add(new boolean[x2][y2]);
					//System.out.println("i is " + i + " layout is " + layout.get(i).length);
				} else {
					layout.add(new boolean[x2+1][y2]);
					//System.out.println("i is " + i + " layout is " + layout.get(i).length);
				}
			}
		} else if (isOverX == 2 && isOverY == 1) {
			x3++;
			y3 = 5;
			for (int i = 0; i < 9; i++) {
				if (i == 0 || i == 2 || i == 6 || i == 8) {
					layout.add(new boolean[x2 + 1][y2]);
				} else if (i == 1 || i == 7) {
					layout.add(new boolean[x2][y2]);
				} else if (i == 3 || i == 5) {
					layout.add(new boolean[x2 + 1][y2 + 1]);
				} else {
					layout.add(new boolean[x2][y2 + 1]);
				}
			}
		} else {
			x3++;
			y3++;
			for (int i = 0; i < 9; i++) {
				if (i == 4) {
					layout.add(new boolean[x2][y2]);
				} else if (i == 1 || i == 7) {
					layout.add(new boolean[x2][y2 + 1]);
				} else if (i == 3 || i == 5) {
					layout.add(new boolean[x2 + 1][y2]);
				} else {
					layout.add(new boolean[x2 + 1][y2 + 1]);
				}
			}
		}
		
		for (int i = 0; i < layout.size(); i++) {
			for (int a = 0; a < layout.get(i).length; a++) {
				for (int b = 0; b < layout.get(i)[a].length; b++) {
					if (i == 0 || i == 2 || i == 6 || i == 8) {
						layout.get(i)[a][b] = false;
						//System.out.println("x is " + a + " and y is " + b + " which are not visible");
					} else {
						layout.get(i)[a][b] = true;
						//System.out.println("x is " + a + " and y is " + b + " which are visible");
					}
				}
			}
		}
		
		cells = addArray(cells, layout.get(0), 0, 0);
		
		
		

		if (x3 == 5 && y3 == 5) {
			//System.out.println("I'm here1");
			y3 = 0;
			x3 = 0;
			cells = addArray(cells, layout.get(1), x2 + x3, 0);
			x3 = 1;
			cells = addArray(cells, layout.get(2), x2*2 + x3, 0);
			x3 = 0;
			cells = addArray(cells, layout.get(3), 0, y2 + y3);
			cells = addArray(cells, layout.get(4), x2 + x3, y2 + y3);
			cells = addArray(cells, layout.get(5), x2*2 + x3, y2 + y3);
			y3 = 1;
			cells = addArray(cells, layout.get(6), 0, y2*2 + y3);
			y3 = 0;
			cells = addArray(cells, layout.get(7), x2 + x3, y2*2 + y3);
			x3 = 1;
			y3 = 1;
			cells = addArray(cells, layout.get(8), x2*2 + x3, y2*2 + y3);
		} else if (x3 == 5) {
			//System.out.println("I'm here2");
			x3 = 0;
			cells = addArray(cells, layout.get(1), x2 + x3, 0);
			x3 = 1;
			cells = addArray(cells, layout.get(2), x2*2 + x3, 0);
			x3 = 0;
			cells = addArray(cells, layout.get(3), 0, y2 + y3);
			cells = addArray(cells, layout.get(4), x2 + x3, y2 + y3);
			x3 = 1;
			cells = addArray(cells, layout.get(5), x2*2 + x3, y2 + y3);
			x3 = 0;
			cells = addArray(cells, layout.get(6), 0, y2*2 + y3);
			cells = addArray(cells, layout.get(7), x2 + x3, y2*2 + y3);
			x3 = 1;
			cells = addArray(cells, layout.get(8), x2*2 + x3, y2*2 + y3);
		} else if (y3 == 5) {
			//System.out.println("I'm here3");
			y3 = 1;
			cells = addArray(cells, layout.get(2), x2*2 + x3, 0);
			cells = addArray(cells, layout.get(3), 0, y2 + y3);
			cells = addArray(cells, layout.get(4), x2 + x3, y2 + y3);
			cells = addArray(cells, layout.get(5), x2*2 + x3, y2 + y3);
			cells = addArray(cells, layout.get(6), 0, y2*2 + y3);
			cells = addArray(cells, layout.get(7), x2 + x3, y2*2 + y3);
			cells = addArray(cells, layout.get(8), x2*2 + x3, y2*2 + y3);
			y3 = 0;
		} else {
			//System.out.println("I'm here4");
			cells = addArray(cells, layout.get(2), x2*2 + x3, 0);
			cells = addArray(cells, layout.get(3), 0, y2 + y3);
			cells = addArray(cells, layout.get(4), x2 + x3, y2 + y3);
			cells = addArray(cells, layout.get(5), x2*2 + x3, y2 + y3);
			cells = addArray(cells, layout.get(6), 0, y2*2 + y3);
			cells = addArray(cells, layout.get(7), x2 + x3, y2*2 + y3);
			cells = addArray(cells, layout.get(8), x2*2 + x3, y2*2 + y3);
		}
		
		//for (int a = 0; a < x; a++) {
			//for (int b = 0; b < y; b++) {
				//System.out.println("a " + a + "    b " + b + "     " + cells[a][b].getVisible());
			//}
		//} 
	
	}
	
	private Cell[][] addArray(Cell cells[][], boolean cells2[][], int x, int y) {
		//System.out.println("First length is " + cells2.length + " and second length is " + cells2[0].length);
		for (int a = 0; a < cells2.length; a++) {
			for (int b = 0; b < cells2[a].length; b++) {
				//System.out.println("cells["+ a + " + " + x + "][" + b + " + " + y + "].setVisible(" + cells2[a][b] + "))");
				cells[a + x][b + y].setVisible(cells2[a][b]);
			}
		}
		return cells;
	}
	
	private void createRectangle() {
		for (int a = 0; a < x; a++) {
			for (int b = 0; b < y; b++) {
				cells[a][b] = new Cell(a, b, true);
			}
		}
	}
	
	private void createCircle() {
		int centreX = x/2;
		int centreY = y/2;
		int radius = x/2;
		
		int d = (5 - radius * 4)/4;
		int x2 = 0;
		int y2 = radius;
		
		for (int i = 0; i < x; i++) {
			for (int a = 0; a < y; a++) {
				cells[i][a] = new Cell(i, a, false);
			}
		}
		
		do {
			cells[centreX + x2][centreY + y2].setVisible(true);
			cells[centreX + x2][centreY - y2].setVisible(true);
			cells[centreX - x2][centreY + y2].setVisible(true);
			cells[centreX - x2][centreY - y2].setVisible(true);
			cells[centreX + y2][centreY + x2].setVisible(true);
			cells[centreX + y2][centreY - x2].setVisible(true);
			cells[centreX - y2][centreY + x2].setVisible(true);
			cells[centreX - y2][centreY - x2].setVisible(true);
			
			if (d < 0) {
				d += 2 * x2 + 1;
			} else {
				d += 2 * (x2 - y2) + 1;
				y2--;
			}
			x2++;
		} while (x2 <= y2);
		
		int temp[][] = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int a = 0; a < y; a++) {
				if (checkHorizontal(i, a) && checkVertical(i, a)) {
					temp[i][a] = 1;
				} else {
					temp[i][a] = 0;
				}
			}
		}
		
		for (int i = 0; i < x; i++) {
			for (int a = 0; a < y; a++) {
				if (temp[i][a] == 1) {
					cells[i][a].setVisible(true);
				}
			}
		}
	}
	
	private boolean checkVertical(int x, int y) {
		boolean up = false;
		boolean down = false;
		
		for (int i = y; i < this.y; i++) {
			if (cells[x][i].getVisible() == true) {
				down = true;
			}
		}
		
		for (int i = y; i >= 0; i--) {
			if (cells[x][i].getVisible() == true) {
				up = true;
			}
		}
		
		return up && down;
	}
	
	private boolean checkHorizontal(int x, int y) {
		boolean left = false;
		boolean right = false;
		
		for (int i = x; i < this.x; i++) {
			if (cells[i][y].getVisible() == true) {
				right = true;
			}
		}
		
		for (int i = x; i >= 0; i--) {
			if (cells[i][y].getVisible() == true) {
				left = true;
			}
		}
		
		return left && right;
	}
	
	public int getWidth() {
		return x;
	}
	
	public int getHeight() {
		return y;
	}
	
	public Cell[][] getLayout() {
		return cells;
	}
	
	public String getName() {
		return name;
	}
}
