package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

import controller.GameController;
import interfaces.Piece;
import model.Board;
import model.Player;

public class MainUserInterface extends JFrame {
	private static final long serialVersionUID = 4121790745247284131L;
	
	private InterfaceBoard board;
	private JPanel players;
	private JPanel title;
	private InterfaceTimer timer;
	private InterfaceButtons buttons;
	private GameController gameController;
	private JLabel player1Label;
	private JLabel player2Label;
	private JLabel player1Points;
	private JLabel player2Points;
	private Player player1;
	private Player player2;
	private JLabel turn;
	
	public MainUserInterface(Board b, Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		//The properties of the main window/frame
		setTitle("OO Game");
		setSize(700, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		board = new InterfaceBoard(b);
		players = new JPanel();
		title = new JPanel();
		timer = new InterfaceTimer(0);
		buttons = new InterfaceButtons();
		
		initTitle();
		initPlayers();
		
		add (board, BorderLayout.CENTER);
		add (timer, BorderLayout.EAST);
		add (buttons, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void updateBoard() {
		board.updateBoard();
	}
	
	private void initTitle() {
		JLabel gameTitle = new JLabel("OO Game");
		
		gameTitle.setFont(new Font("Sans-Serif", Font.BOLD, 22));
		
		title.add(gameTitle, BorderLayout.CENTER);
		add(title, BorderLayout.NORTH);
	}
	
	private void initPlayers() {
		Font playerNames = new Font("Sans-Serif", Font.BOLD, 20);
		Font points = new Font("Sans-Serif", Font.PLAIN, 17);
		
		player1Label = new JLabel(player1.getName());
		player1Label.setFont(playerNames);
		player2Label = new JLabel(player2.getName());
		player2Label.setFont(playerNames);
		
		player1Points = new JLabel("Points: " + Integer.toString(player1.getPoints()));
		player1Points.setFont(points);
		player2Points = new JLabel("Points: " + Integer.toString(player2.getPoints()));
		player2Points.setFont(points);
		
		turn = new JLabel();
		
		Box box = Box.createVerticalBox();
		
		box.add(player1Label);
		box.add(player1Points);
		box.add(player2Label);
		box.add(player2Points);
		box.add(turn);
		
		players.add(box);
		
		add(players, BorderLayout.WEST);
	}

	public void addGameController(GameController gameController) {
		this.gameController = gameController; 
		board.addGameController(gameController);
	}
	
	public String askPiece(String name) {
		Object[] options = {"Default Layout", "King", "Queen", "Pawn", "Archer", "Wizard", "Scout"};
		String response;
		
		response = (String)JOptionPane.showInputDialog(null, name + ":\n" + "Please select a piece:", "Select Piece", JOptionPane.QUESTION_MESSAGE, null, options, "Default Layout");
		
		return response;
	}

	public void updateSelectedPiece(String selectedPiece) {
		timer.addSelectedPiece(selectedPiece);
	}

	public void updatePoints() {
		player1Points.setText(Integer.toString(player1.getPoints()));
		player2Points.setText(Integer.toString(player2.getPoints()));
	}

	public void hideSelected() {
		timer.hideSelected();
	}

	public void updateTurn(Player p) {
		turn.setText(p.getName() + "'s turn!");
	}
	
	public void message(String m) {
		JOptionPane.showMessageDialog(null, m);
	}
}