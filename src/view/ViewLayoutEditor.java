package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.LayoutEditorController;
import controller.PlayerController;
import model.Board;
import model.BoardShape;

public class ViewLayoutEditor extends JFrame {
	private static final long serialVersionUID = -7057910387033516269L;
	private LayoutEditorController layoutController;
	private Board board;
	private PlayerController playerController;
	private JLabel player1Points;
	private JLabel player2Points;
	private ViewBoard viewBoard;
	private JButton player1Select;
	private JButton player2Select;
	private ViewPieceSelection pieceSelection;
	private AbstractUIFactory uiFactory;
	private final Color attackRange = new Color(255, 92, 92);
	
	public ViewLayoutEditor(BoardShape boardShape, LayoutEditorController layoutController, PlayerController playerController, AbstractUIFactory uiFactory) {
		this.playerController = playerController;
		this.layoutController = layoutController;
		this.uiFactory = uiFactory;
		board = new Board(boardShape);
		viewBoard = new ViewBoard(layoutController, board, uiFactory);
		layoutController.setGameVariables(board, null, null);
		
		ViewButtons buttons = new ViewButtons(board, "Player 1", "Player 2", layoutController, uiFactory);
		
		//The properties of the main window/frame
		setTitle("Layout Editor");
		setSize(850, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initTitle();
		initPlayers();
		add(viewBoard, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void initTitle() {
		JPanel title = uiFactory.createPanel();
		JLabel gameTitle = uiFactory.createLabel("Layout Editor");
		
		gameTitle.setFont(new Font("Sans-Serif", Font.BOLD, 22));
		
		title.add(gameTitle, BorderLayout.CENTER);
		
		add(title, BorderLayout.NORTH);
	}
	
	/**
	 * Initiates the left (west) panel with the players' name, points and turn details.
	 */
	private void initPlayers() {
		JPanel players = uiFactory.createPanel();
		JLabel player1Label = uiFactory.createLabel("Player 1");
		JLabel player2Label = uiFactory.createLabel("Player 2");
		Font playerNames = new Font("Sans-Serif", Font.BOLD, 20);
		Font points = new Font("Sans-Serif", Font.PLAIN, 17);
		Box playersAndButtons = Box.createVerticalBox();
		EmptyBorder playerBorder = new EmptyBorder(3, 3, 0, 50);
		EmptyBorder pointsBorder = new EmptyBorder(0, 3, 3, 10);
		Box playerButtons = Box.createVerticalBox();
		player1Select = uiFactory.createButton("Player 1");
		player2Select = uiFactory.createButton("Player 2");
		JLabel playerSelect = uiFactory.createLabel("Select a player:");
		
		ActionListener playerSelectListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				layoutController.playerSelected(button.getText());
			}
		};
		
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
		
		player1Select.addActionListener(playerSelectListener);
		player2Select.addActionListener(playerSelectListener);
		player1Select.setBackground(Color.YELLOW);
		player2Select.setBackground(Color.GRAY);
		
		playerButtons.add(uiFactory.createLabel(" "));
		playerButtons.add(playerSelect);
		playerButtons.add(player1Select);
		playerButtons.add(player2Select);
		
		Box box = Box.createVerticalBox();
		
		pieceSelection = new ViewPieceSelection(layoutController, uiFactory);
		
		box.add(player1Label);
		box.add(player1Points);
		box.add(player2Label);
		box.add(player2Points);
		box.add(Box.createVerticalStrut(50));
		box.add(pieceSelection);
		box.add(playerButtons);
		
		players.add(box);
		
		playersAndButtons.add(players);
		
		add(playersAndButtons, BorderLayout.WEST);
	}

	public void updateBoard() {
		viewBoard.updateBoard();
	}
	
	public void updateJButtons(String playerName) {
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(2, 14, 2, 14));
		Border border2 = UIManager.getBorder("Button.border");
		
		if (playerName.equals(player1Select.getText())) {
			player1Select.setBorder(border);
			player2Select.setBorder(border2);
		} else {
			player1Select.setBorder(border2);
			player2Select.setBorder(border);
		}
	}
	
	public void pieceSelected(String pieceName) {
		pieceSelection.setJButton(pieceName);
	}
}
