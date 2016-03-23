package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;
import model.Board;
import model.Player;

public class MainUserInterface extends JFrame {
	private static final long serialVersionUID = 4121790745247284131L;
	
	private DrawBoard board;
	private JPanel players;
	private JPanel title;
	private InterfaceTimer timer;
	private InterfaceButtons buttons;
	private GameController gameController; 
	public MainUserInterface(Board b, Player player1, Player player2) {
		//The properties of the main window/frame
		setTitle("OO Game");
		setSize(700, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		board = new DrawBoard(b);
		players = new JPanel();
		title = new JPanel();
		timer = new InterfaceTimer(0);
		buttons = new InterfaceButtons();
		
		initTitle();
		initPlayers(player1, player2);
		
		add (board, BorderLayout.CENTER);
		add (timer, BorderLayout.EAST);
		add (buttons, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void updateBoard() {
		board.updateBoard();
//		board.repaint();
	}
	
	private void initTitle() {
		JLabel gameTitle = new JLabel("OO Game");
		
		gameTitle.setFont(new Font("Sans-Serif", Font.BOLD, 22));
		
		title.add(gameTitle, BorderLayout.CENTER);
		add(title, BorderLayout.NORTH);
	}
	
	private void initPlayers(Player player1, Player player2) {
		Font playerNames = new Font("Sans-Serif", Font.BOLD, 20);
		Font points = new Font("Sans-Serif", Font.PLAIN, 17);
		
		JLabel player1Label = new JLabel(player1.getName());
		player1Label.setFont(playerNames);
		JLabel player2Label = new JLabel(player2.getName());
		player2Label.setFont(playerNames);
		
		JLabel player1Points = new JLabel("Points: " + Integer.toString(player1.getPoints()));
		player1Points.setFont(points);
		JLabel player2Points = new JLabel("Points: " + Integer.toString(player2.getPoints()));
		player2Points.setFont(points);
		Box box = Box.createVerticalBox();
		
		box.add(player1Label);
		box.add(player1Points);
		box.add(player2Label);
		box.add(player2Points);
		
		players.add(box);
		
		add(players, BorderLayout.WEST);
	}

	public void addGameController(GameController gameController) {
		this.gameController = gameController; 
		board.addGameController(gameController);
	}
}