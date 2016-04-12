package view;

//Had help from http://stackoverflow.com/questions/8637792/how-to-set-jformattedtextfield-so-it-only-allows-2-numbers
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Main;
import model.BoardLayout;

public class StartScreen extends JFrame {
	private static final long serialVersionUID = -7178518888261894209L;

	private JTextField player1Name;
	private JTextField player2Name;
	private JLabel boardLayoutLabel;
	private JComboBox<String> boardLayout;
	private JLabel timerLabel;
	private JFormattedTextField timer;
	private JPanel players;
	private JPanel other;
	private JPanel buttons;
	private JPanel titlePanel;
	private JLabel subtitlePlayers;
	private JLabel subtitleGame;
	private BoardLayout[] layouts;
	
	public StartScreen (BoardLayout[] layouts) {
		this.layouts = layouts;
		
		JLabel title = new JLabel("OO Game Setup");
		
		//Set the window's properties
		setTitle("OO Game Start");
		setSize(500, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titlePanel = new JPanel();
		titlePanel.add(title);
		add(titlePanel, BorderLayout.NORTH);
		
		playerSetup();
		add(players, BorderLayout.WEST);
		
		gameSetup();
		add(other, BorderLayout.EAST);
		
		buttonSetup();
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void playerSetup() {
		JPanel playerTitle = new JPanel();
		JPanel players2 = new JPanel();
		JPanel player1Group = new JPanel();
		JPanel player2Group = new JPanel();
		JLabel player1Label = new JLabel("Player 1 Name:");
		JLabel player2Label = new JLabel("Player 2 Name:");
		Box box = Box.createVerticalBox();
		
		players = new JPanel();
		players.setLayout(new BorderLayout());
		
		subtitlePlayers = new JLabel("Player Settings");
		playerTitle.add(subtitlePlayers, BorderLayout.NORTH);
		players.add(playerTitle, BorderLayout.NORTH);
		
		player1Name = new JTextField("Player1");
		player1Name.setColumns(8);
		player1Group.add(player1Label);
		player1Group.add(player1Name);
		box.add(player1Group);
		
		player2Name = new JTextField("Player2");
		player2Name.setColumns(8);
		player2Group.add(player2Label);
		player2Group.add(player2Name);
		box.add(player2Group);
		
		players2.add(box);
		players.add(players2, BorderLayout.CENTER);
	}

	private void gameSetup() {
		JPanel otherTitle = new JPanel();
		JPanel boardLayoutGroup = new JPanel();
		JPanel timerGroup = new JPanel();
		Box box = Box.createVerticalBox();
		NumberFormat timerFormat = NumberFormat.getNumberInstance();
		
		other = new JPanel();
		other.setLayout(new BorderLayout());
		subtitleGame = new JLabel("Game Settings");
		otherTitle.add(subtitleGame);
		
		boardLayoutLabel = new JLabel("Board Layout:");
		boardLayout = new JComboBox<String>();
		if (layouts != null) {
			for (int i = 0; i < layouts.length; i++) {
				boardLayout.addItem(layouts[i].getName());
			}
		} else {
			boardLayout.addItem("Default Layout");
		}
		
		boardLayoutGroup.add(boardLayoutLabel);
		boardLayoutGroup.add(boardLayout);
		box.add(boardLayoutGroup);
		
		timerLabel = new JLabel("Set Timer:");
		timerFormat.setMaximumIntegerDigits(4);
		timer = new JFormattedTextField(timerFormat);
		timer.setColumns(4);
		timer.setText("60");
		timerGroup.add(timerLabel);
		timerGroup.add(timer);
		box.add(timerGroup);
		
		other.add(otherTitle, BorderLayout.NORTH);
		other.add(box, BorderLayout.CENTER);
	}
	
	private void buttonSetup() {
		JButton start = new JButton("Start");
		JButton defaultBtn = new JButton("Default Settings");
		JButton exit = new JButton("Exit");
		
		buttons = new JPanel();
		
		start.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		defaultBtn.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				defaultSettings();
			}
		});
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		buttons.add(start, BorderLayout.SOUTH);
		buttons.add(defaultBtn, BorderLayout.SOUTH);
		buttons.add(exit, BorderLayout.SOUTH);
	}
	
	private void start() {
		this.dispose();
		Main.startGame(player1Name.getText(), player2Name.getText(), boardLayout.getItemAt(boardLayout.getSelectedIndex()), Integer.parseInt(timer.getText()));
	}
	
	private void defaultSettings() {
		player1Name.setText("Player1");
		player2Name.setText("Player2");
		boardLayout.setSelectedIndex(0);
		timer.setText("60");
	}
	
	private void exit() {
		int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit?", "Warning", 0);
		
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);	
		}
	}
}
