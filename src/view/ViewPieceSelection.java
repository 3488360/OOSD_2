package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import controller.ButtonControllerInterface;

//Not implemented yet. It will add a panel of JButtons that the players can use to add more pieces to the board.
public class ViewPieceSelection extends JPanel {
	private static final long serialVersionUID = -7645116281798164489L;
	private ButtonControllerInterface buttonController;
	private HashMap<String, JButton> buttons = new HashMap<String, JButton>();
	private AbstractUIFactory uiFactory;
	
	public ViewPieceSelection(ButtonControllerInterface bc, AbstractUIFactory uiFactory) {
		this.buttonController = bc;
		this.uiFactory = uiFactory;
		add(pieces());
	}
	
	private JPanel pieces() {
		JPanel pieces = uiFactory.createPanel();
		JButton button;
		pieces.setLayout(new GridLayout(5, 2));
		ActionListener btnSelect = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				selectAction(e);
			}
		};
		
		
		button = uiFactory.createButton("King", resizeIcon(new ImageIcon("images/King.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("king", button);
		
		button = uiFactory.createButton("Archer", resizeIcon(new ImageIcon("images/Archer2.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("archer", button);
		
		button = uiFactory.createButton("God", resizeIcon(new ImageIcon("images/God.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("god", button);
		
		button = uiFactory.createButton("Healer", resizeIcon(new ImageIcon("images/Healer.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("healer", button);
		
		button = uiFactory.createButton("Queen", resizeIcon(new ImageIcon("images/Queen.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("queen", button);
		
		button = uiFactory.createButton("Soldier", resizeIcon(new ImageIcon("images/Soldier2.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("soldier", button);
		
		button = uiFactory.createButton("Tank", resizeIcon(new ImageIcon("images/Tank.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("tank", button);
		
		button = uiFactory.createButton("Wizard", resizeIcon(new ImageIcon("images/Wizard.png")));
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("wizard", button);
		
		button = uiFactory.createButton("Delete");
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("delete", button);
		
		button = uiFactory.createButton("Test");
		button.addActionListener(btnSelect);
		button.setBackground(Color.LIGHT_GRAY);
		buttons.put("test", button);
		
		for(JButton button2 : buttons.values()) {
			pieces.add(button2);
		}
		
		return pieces;
	}
	
	private void selectAction(ActionEvent e) {
		buttonController.addPiece(((JButton) e.getSource()).getText());
	}
	
    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, 20, 20, null);
        g.dispose();
        ImageIcon newIcon = new ImageIcon(bi);
        return newIcon;
    }
    
    public void setJButton(String pieceName) {
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(2, 14, 2, 14));
		Border border2 = UIManager.getBorder("Button.border");
		
		for(JButton button : buttons.values()) {
			button.setBorder(border2);
		}
    	
		//System.out.println("Piece " + pieceName);
		
		pieceName = pieceName.toLowerCase();
		buttons.get(pieceName).setBorder(border);
		
    }
}
