package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ButtonController;
import controller.LoadController;
import controller.Main;
import model.BoardLayout;

/**
 * The start screen to get all inputs from the user that the game needs before it is created.
 */
public class ViewStart extends JFrame {
	private static final long serialVersionUID = -7178518888261894209L;

	private JTextField player1Name;
	private JTextField player2Name;
	private JComboBox<String> boardLayout;
	private JTextField initTimer;
	private BoardLayout[] layouts;
	private BoardLayout selectedLayout = null;
	private Font subtitle;
	private ButtonController buttonController;
	private LoadController loadController;
	private AbstractUIFactory uiFactory;
	
	/**
	 * Creates and displays the settings screen for the game.
	 * 
	 * @param layouts - An array of BoardLayouts so the drop-down menu can contain a list of all their names.
	 */
	public ViewStart (BoardLayout[] layouts, ButtonController buttonController, LoadController loadController, AbstractUIFactory uiFactory) {
		this.layouts = layouts;
		this.buttonController = buttonController;
		this.loadController = loadController;
		this.uiFactory = uiFactory;
		
		JLabel title = uiFactory.createLabel("<HTML><U>King vs. Queen Settings</U></HTML>");
		title.setFont(new Font("Sans-Serif", Font.BOLD, 20));
		JPanel titlePanel = new JPanel();
		
		subtitle = new Font("Sans-Serif", Font.BOLD, 18);
		
		//Set the window's properties
		setTitle("King vs. Queen Settings");
		setSize(420, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titlePanel.add(title);
		add(titlePanel, BorderLayout.NORTH);
		
		add(playerSetup(), BorderLayout.WEST);
		
		add(gameSetup(), BorderLayout.EAST);
		
		add(buttonSetup(), BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	/**
	 * Creates a JPanel with the player details input. Contains input boxes for their names.
	 * 
	 * @return A JPanel that contains the settings for the players.
	 */
	private JPanel playerSetup() {
		JLabel subtitlePlayers;
		JPanel playerTitle = new JPanel();
		JPanel players2 = new JPanel();
		JPanel player1Group = new JPanel();
		JPanel player2Group = new JPanel();
		JLabel player1Label = uiFactory.createLabel("Player 1 Name:");
		JLabel player2Label = uiFactory.createLabel("Player 2 Name:");
		Box box = Box.createVerticalBox();
		JPanel players = new JPanel();

		players.setLayout(new BorderLayout());
		
		subtitlePlayers = uiFactory.createLabel("Player Settings");
		subtitlePlayers.setFont(subtitle);
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
		
		return players;
	}

	/**
	 * Creates the JPanel with all the game settings. Contains the board layout selection and timer length.
	 * 
	 * @return A JPanel that contains the settings for the game itself.
	 */
	private JPanel gameSetup() {
		JLabel boardLayoutLabel = uiFactory.createLabel("Board Layout:");
		JLabel timerLabel = uiFactory.createLabel("Set Timer:");
		JLabel subtitleGame = uiFactory.createLabel("Game Settings");
		JPanel otherTitle = new JPanel();
		JPanel boardLayoutGroup = new JPanel();
		JPanel timerGroup = new JPanel();
		JPanel other = new JPanel();
		Box box = Box.createVerticalBox();
		
		other.setLayout(new BorderLayout());
		subtitleGame.setFont(subtitle);
		otherTitle.add(subtitleGame);
		
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
		
		initTimer = new JTextField();
		initTimer.setColumns(4);
		initTimer.setText("60");
		timerGroup.add(timerLabel);
		timerGroup.add(initTimer);
		box.add(timerGroup);
		
		other.add(otherTitle, BorderLayout.NORTH);
		other.add(box, BorderLayout.CENTER);
		
		return other;
	}
	
	/**
	 * Initiates the buttons at the bottom of the interface.
	 * 
	 * @return A JPanel containing the buttons.
	 */
	private JPanel buttonSetup() {
		JButton start = uiFactory.createButton("Start");
		JButton defaultBtn = uiFactory.createButton("Default Settings");
		JButton load = uiFactory.createButton("Load");
		JButton exit = uiFactory.createButton("Exit");
		JPanel buttons = new JPanel();
		
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
		
		load.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				buttonController.exit();
			}
		});
		
		buttons.add(start, BorderLayout.SOUTH);
		buttons.add(defaultBtn, BorderLayout.SOUTH);
		buttons.add(load, BorderLayout.SOUTH);
		buttons.add(exit, BorderLayout.SOUTH);
		
		return buttons;
	}
	
	/**
	 * Closes this interface and then calls startGame in main.
	 */
	private void start() {
		this.dispose();
		if (selectedLayout == null) {
			selectedLayout = layouts[boardLayout.getSelectedIndex()];
		}
		Main.startGame(player1Name.getText(), player2Name.getText(), selectedLayout, Integer.parseInt(initTimer.getText()));
	}
	
	/**
	 * Sets all input fields to the default settings.
	 */
	private void defaultSettings() {
		player1Name.setText("Player1");
		player2Name.setText("Player2");
		boardLayout.setSelectedIndex(0);
		initTimer.setText("60");
	}
	
	private void load() {
		List<Object> list = null;
		
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save Files", "save", "save");
		fc.setFileFilter(filter);
		
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			list = loadController.loadGame(fc.getSelectedFile());
			if (list == null) {
				JOptionPane.showMessageDialog(null, "An error occured when trying to load the file. Please see console for details.", "Error", 0);
				return;
			}
			player1Name.setText((String) list.get(0));
			player2Name.setText((String) list.get(1));
			initTimer.setText(((Integer) list.get(2)).toString());
			selectedLayout = (BoardLayout) list.get(3);
			start();
		}
	}
}
