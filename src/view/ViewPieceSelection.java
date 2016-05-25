package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
	private JButton king;
	private JButton archer;
	private JButton god;
	private JButton healer;
	private JButton queen;
	private JButton soldier;
	private JButton tank;
	private JButton wizard;
	private JButton delete;
	private AbstractUIFactory uiFactory;
	
	public ViewPieceSelection(ButtonControllerInterface bc, AbstractUIFactory uiFactory) {
		this.buttonController = bc;
		this.uiFactory = uiFactory;
		add(pieces());
	}
	
	private JPanel pieces() {
		JPanel pieces = uiFactory.createPanel();
		pieces.setLayout(new GridLayout(4, 2));
		ActionListener btnSelect = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				selectAction(e);
			}
		};
		
		king = uiFactory.createButton("King", resizeIcon(new ImageIcon("images/King.png")));
		king.addActionListener(btnSelect);
		king.setBackground(Color.LIGHT_GRAY);
		archer = uiFactory.createButton("Archer", resizeIcon(new ImageIcon("images/Archer2.png")));
		archer.addActionListener(btnSelect);
		archer.setBackground(Color.LIGHT_GRAY);
		god = uiFactory.createButton("God", resizeIcon(new ImageIcon("images/God.png")));
		god.addActionListener(btnSelect);
		god.setBackground(Color.LIGHT_GRAY);
		healer = uiFactory.createButton("Healer", resizeIcon(new ImageIcon("images/Healer.png")));
		healer.addActionListener(btnSelect);
		healer.setBackground(Color.LIGHT_GRAY);
		queen = uiFactory.createButton("Queen", resizeIcon(new ImageIcon("images/Queen.png")));
		queen.addActionListener(btnSelect);
		queen.setBackground(Color.LIGHT_GRAY);
		soldier = uiFactory.createButton("Soldier", resizeIcon(new ImageIcon("images/Soldier2.png")));
		soldier.addActionListener(btnSelect);
		soldier.setBackground(Color.LIGHT_GRAY);
		tank = uiFactory.createButton("Tank", resizeIcon(new ImageIcon("images/Tank.png")));
		tank.addActionListener(btnSelect);
		tank.setBackground(Color.LIGHT_GRAY);
		wizard = uiFactory.createButton("Wizard", resizeIcon(new ImageIcon("images/Wizard.png")));
		wizard.addActionListener(btnSelect);
		wizard.setBackground(Color.LIGHT_GRAY);
		delete = uiFactory.createButton("Delete");
		delete.addActionListener(btnSelect);
		delete.setBackground(Color.LIGHT_GRAY);
		
		pieces.add(king);
		pieces.add(queen);
		pieces.add(soldier);
		pieces.add(god);
		pieces.add(tank);
		pieces.add(healer);
		pieces.add(archer);
		pieces.add(wizard);
		//pieces.add(delete);
		
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
    	
		king.setBorder(border2);
		archer.setBorder(border2);
		god.setBorder(border2);
		healer.setBorder(border2);
		queen.setBorder(border2);
		soldier.setBorder(border2);
		tank.setBorder(border2);
		wizard.setBorder(border2);
		delete.setBorder(border2);
    	
		//System.out.println("Piece " + pieceName);
		
    	switch (pieceName) {
	    	case "King": king.setBorder(border);
	    		break;
	    	
	    	case "Archer": archer.setBorder(border);
	    		break;
	    	
	    	case "God": god.setBorder(border);
	    		break;
	    	
	    	case "Healer": healer.setBorder(border);
	    		break;
	    	
	    	case "Queen": queen.setBorder(border);
	    		break;
	    	
	    	case "Soldier": soldier.setBorder(border);
	    		break;
	    	
	    	case "Tank": tank.setBorder(border);
	    		break;
	    	
	    	case "Wizard": wizard.setBorder(border);
	    		break;
	    		
	    	case "Delete": delete.setBorder(border);
    			break;
    	}
    }
}
