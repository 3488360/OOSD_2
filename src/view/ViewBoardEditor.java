package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.LayoutEditorController;

public class ViewBoardEditor extends JFrame {
	private static final long serialVersionUID = -5609287059506714010L;
	private JTextField boardX;
	private JTextField boardY;
	private LayoutEditorController editorController;
	private Font subtitle;
	private JComboBox<String> shapes;
	private JLabel xLabel;
	private AbstractUIFactory uiFactory;
	
	public ViewBoardEditor(LayoutEditorController editorController, AbstractUIFactory uiFactory) {
		this.editorController = editorController;
		this.uiFactory = uiFactory;
		xLabel = uiFactory.createLabel("Width: ");
		
		JLabel title = uiFactory.createLabel("<HTML><U>Board Settings</U></HTML>");
		title.setFont(new Font("Sans-Serif", Font.BOLD, 20));
		JPanel titlePanel = uiFactory.createPanel();
		
		subtitle = new Font("Sans-Serif", Font.BOLD, 18);
		
		//Set the window's properties
		setTitle("Board Settings");
		setSize(300, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titlePanel.add(title);
		add(titlePanel, BorderLayout.NORTH);
		
		add(BoardShapes(), BorderLayout.WEST);
		
		add(BoardCoordinates(), BorderLayout.EAST);
		
		buttonSetup();
		
		setVisible(true);
		//System.out.println("I'm here");
	}
	
	private Box BoardShapes() {
		Box boardShapes = Box.createVerticalBox();
		JPanel panel = uiFactory.createPanel();
		JLabel boardShapesLabel = uiFactory.createLabel("Board Shapes");
		shapes = new JComboBox<String>();
		JPanel panel2 = uiFactory.createPanel();
		
		shapes.addItem("Plus");
		shapes.addItem("Circle");
		shapes.addItem("Rectangle");
		
		shapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeChanged();
			}
		});
		
		boardShapesLabel.setFont(subtitle);
		
		panel.add(shapes);
		panel2.add(boardShapesLabel);
		boardShapes.add(panel2);
		boardShapes.add(panel);
		
		return boardShapes;
	}
	
	private Box BoardCoordinates() {
		JPanel x = uiFactory.createPanel();
		JPanel y = uiFactory.createPanel();
		JLabel yLabel = uiFactory.createLabel("Height: ");
		Box box = Box.createVerticalBox();
		boardX = new JTextField("13");
		boardY = new JTextField("13");
		Font font = new Font("Sans-Serif", Font.BOLD, 16);
		
		
		boardX.setPreferredSize(new Dimension(35, 20));
		boardY.setPreferredSize(new Dimension(35, 20));
		yLabel.setFont(font);
		xLabel.setFont(font);
		
		x.add(xLabel);
		x.add(boardX);
		y.add(yLabel);
		y.add(boardY);
		
		box.add(x);
		box.add(y);

		return box;
	}
	
	private void buttonSetup() {
		JButton exit = uiFactory.createButton("Exit");
		JButton next = uiFactory.createButton("Continue");
		JPanel panel = uiFactory.createPanel();
		
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				editorController.exit();
			}
		});
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		
		panel.add(next);
		panel.add(exit);
		
		add(panel, BorderLayout.SOUTH);

	}
	
	private void next() {
		String shape = (String) shapes.getSelectedItem();
		int x = 0;
		int y = 0;
		
		if (boardX.getText().equals("") || boardY.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter width and height of the board.");
			return;
		}
		
		if (isPositiveInteger(boardX.getText())) {
			x = Integer.parseInt(boardX.getText());
		} else {
			JOptionPane.showMessageDialog(null, "X is not a digit.");
			return;
		}
		
		if (isPositiveInteger(boardY.getText())) {
			y = Integer.parseInt(boardY.getText());
		} else {
			JOptionPane.showMessageDialog(null, "Y is not a digit.");
			return;
		}
		
		if (xLabel.getText().equals("Circle: ")) {
			if (x > 8 || x < 3 ) {
				JOptionPane.showMessageDialog(null, "Please keep radius between 3 and 8.");
				return;
			}
		} else {
			if (x > 18 || y > 18 || x < 8 || y < 8) {
				JOptionPane.showMessageDialog(null, "Please keep board coordinates between 8 and 18.");
				return;
			}
		}
		
		editorController.next(shape, x, y);
	}
	
	private boolean isPositiveInteger(String s) {
		if (s.isEmpty()) {
			return true;
		}
		
		for (int i = 0; i < s.length(); i++) {
			if (Character.digit(s.charAt(i), 10) < 0)
					return false;
		}
		
		return true;
	}
	
	private void shapeChanged() {
		if (shapes.getSelectedItem().equals("Circle")) {
			xLabel.setText("Radius: ");
			boardY.setEnabled(false);
			boardX.setText("8");
		} else {
			xLabel.setText("Width: ");
			boardY.setEnabled(true);
		}
	}
}
