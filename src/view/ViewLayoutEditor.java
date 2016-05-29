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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.CommonCode;
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
	private JTextField player1PointsInput;
	private JTextField player2PointsInput;
	private JFrame setPoints;
	
	public ViewLayoutEditor(BoardShape boardShape, LayoutEditorController layoutController, AbstractUIFactory uiFactory) {
		playerController = PlayerController.getInstance();
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
		JButton setPoints = uiFactory.createButton("Set Starting Points");
		player1Select = uiFactory.createButton("Player 1");
		player2Select = uiFactory.createButton("Player 2");
		JLabel playerSelect = uiFactory.createLabel("Select a player:");
		JPanel setPointsPanel = uiFactory.createPanel();
		
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
		
		setPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setPoints();
			}
		});
		
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
		setPointsPanel.add(setPoints);
		box.add(setPointsPanel);
		
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
	
	private void setPoints() {
		setPoints = new JFrame();
		player1PointsInput = new JTextField("100");
		player2PointsInput = new JTextField("100");
		JButton set = uiFactory.createButton("Set");
		JButton cancel = uiFactory.createButton("Cancel");
		JLabel player1 = uiFactory.createLabel("Player 1 Points:");
		JLabel player2 = uiFactory.createLabel("Player 2 Points:");
		JPanel player1Panel = uiFactory.createPanel();
		JPanel player2Panel = uiFactory.createPanel();
		Box box = Box.createVerticalBox();
		JPanel buttons = uiFactory.createPanel();
		
		setPoints.setTitle("Set Starting Points");
		setPoints.setSize(200, 120);
		setPoints.setResizable(false);
		setPoints.setLocationRelativeTo(this);
		setPoints.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		
		player1Panel.add(player1);
		player1Panel.add(player1PointsInput);
		player2Panel.add(player2);
		player2Panel.add(player2PointsInput);
		box.add(player1Panel);
		box.add(player2Panel);
		buttons.add(set);
		buttons.add(cancel);
		
		setPoints.add(box, BorderLayout.CENTER);
		setPoints.add(buttons, BorderLayout.SOUTH);
		
		setPoints.setVisible(true);
	}
	
	public void pieceSelected(String pieceName) {
		pieceSelection.setJButton(pieceName);
	}
	
	private void set() {
		String input = player1PointsInput.getText();
		String input2 = player2PointsInput.getText();
		
		if (input.equals("") || input2.equals("")) {
			CommonCode.message("Please enter points for both Player 1 and Player2");
			return;
		}
		
		if (CommonCode.isPositiveInteger(input)) {
			player1Points.setText("Points: " + input);
			playerController.setPoints("player1", Integer.parseInt(input));
		} else {
			CommonCode.message("Player 1's points are not a positive number.");
			return;
		}
		
		if (CommonCode.isPositiveInteger(input2)) {
			player2Points.setText("Points: " + input2);
			playerController.setPoints("player2", Integer.parseInt(input));
		} else {
			CommonCode.message("Player 2's points are not a positive number.");
			return;
		}
		
		setPoints.dispose();
	}
	
	private void cancel() {
		setPoints.dispose();
	}
}
