package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ButtonControllerInterface;
import controller.GameController;
import controller.PlayerController;
import model.Board;
import model.Coordinate;
import model.GameTimer;

/**
 * The main user interface JFrame for the game. Contains a title, 
 * a panel with player details and whose turn it is, the board itself, the timer and an exit button.
 */
public class ViewMain extends JFrame {
	private static final long serialVersionUID = 4121790745247284131L;
	private ViewBoard board;
	private ViewTimer timer;
	private JLabel player1Points;
	private JLabel player2Points;
	private PlayerController playerController;
	private ButtonControllerInterface buttonController;
	private JLabel turn;
	private AbstractUIFactory uiFactory;
	
	/**
	 * Creates the main user interface for the game that displays the board, player details and the timer.
	 * 
	 * @param gameController - The controller that will control communication with this interface and the model.
	 * @param boardController - The controller that controls communication between this interface and the board object.
	 * @param playerController - The controller that controls communication with this interface and the player objects.
	 */
	public ViewMain(GameController gameController, Board board, PlayerController playerController, ButtonControllerInterface buttonController, GameTimer gameTimer, AbstractUIFactory uiFactory) {
		this.playerController = playerController;
		this.buttonController = buttonController;
		this.uiFactory = uiFactory;
		ViewButtons buttons = new ViewButtons(board, playerController.getPlayerName("player1"), playerController.getPlayerName("player2"), buttonController, uiFactory);
		
		//The properties of the main window/frame
		setTitle("King vs. Queen");
		setSize(890, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.board = new ViewBoard(buttonController, board, uiFactory);
		timer = new ViewTimer(uiFactory);
		gameTimer.addObserver(timer);
		
		initTitle();
		initPlayers();
		
		add (this.board, BorderLayout.CENTER);
		add (timer, BorderLayout.EAST);
		add (buttons, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	/**
	 * Initiates the title at the top (north).
	 */
	private void initTitle() {
		JPanel title = uiFactory.createPanel();
		JLabel gameTitle = uiFactory.createLabel("King vs. Queen");
		
		gameTitle.setFont(new Font("Sans-Serif", Font.BOLD, 22));
		
		title.add(gameTitle, BorderLayout.CENTER);
		
		add(title, BorderLayout.NORTH);
	}
	
	/**
	 * Initiates the left (west) panel with the players' name, points and turn details.
	 */
	private void initPlayers() {
		JPanel players = uiFactory.createPanel();
		JLabel player1Label = uiFactory.createLabel(playerController.getPlayerName("player1"));
		JLabel player2Label = uiFactory.createLabel(playerController.getPlayerName("player2"));
		Font playerNames = new Font("Sans-Serif", Font.BOLD, 20);
		Font points = new Font("Sans-Serif", Font.PLAIN, 17);
		Box playersAndButtons = Box.createVerticalBox();
		EmptyBorder playerBorder = new EmptyBorder(3, 3, 0, 40);
		EmptyBorder pointsBorder = new EmptyBorder(0, 3, 3, 10);
		
		player1Label.setFont(playerNames);
		player1Label.setBorder(playerBorder);
		player2Label.setFont(playerNames);
		player2Label.setBorder(playerBorder);
		
		player1Points = uiFactory.createLabel("Points: " + Integer.toString(playerController.getPoints("player1")));
		player1Points.setFont(points);
		player1Points.setBorder(pointsBorder);
		player2Points = uiFactory.createLabel("Points: " + Integer.toString(playerController.getPoints("player1")));
		player2Points.setFont(points);
		player2Points.setBorder(pointsBorder);
		
		turn = uiFactory.createLabel();
		
		Box box = Box.createVerticalBox();
		
		box.add(player1Label);
		box.add(player1Points);
		box.add(player2Label);
		box.add(player2Points);
		box.add(turn);
		box.add(Box.createVerticalStrut(50));
		box.add(new ViewPieceSelection(buttonController, uiFactory));
		
		players.add(box);
		
		playersAndButtons.add(players);
		
		add(playersAndButtons, BorderLayout.WEST);
	}
	
	/**
	 * Updates the game board.
	 */
	public void updateBoard() {
		board.updateBoard();
	}

	public void updateSelectedPiece(String name, String currentHealth, String currentStrength) {
		timer.addSelectedPiece(name, currentHealth, currentStrength);
	}
	
	public void updateMoves(List<Coordinate> list) {
		if(list != null)
			board.updateCells(list);
	}
	
	public void updateAttackRange(List<Coordinate> attackRange) {
		if(attackRange != null)
			board.updateAttackRange(attackRange);
	}

	/**
	 * Updates the players' points on the user interface.
	 */
	public void updatePoints() {
		player1Points.setText(Integer.toString(playerController.getPoints("player1")));
		player2Points.setText(Integer.toString(playerController.getPoints("player2")));
	}

	/**
	 * Hides the selected piece's details on the user interface.
	 */
	public void hideSelected() {
		timer.hideSelected();
	}

	/**
	 * Updates the playersTurn JLabel.
	 * 
	 * @param name - The current player's name.
	 */
	public void updateTurn(String name) {
		turn.setText(name + "'s turn!");
	}
	
	/**
	 * A function to output a simple message dialog box to the user.
	 * 
	 * @param message - The message to be displayed.
	 */
	public void message(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void pause() {
		board.setVisible(false);
	}
	
	public void resume() {
		board.setVisible(true);
	}
}