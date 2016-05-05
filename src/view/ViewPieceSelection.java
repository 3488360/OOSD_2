package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ButtonController;

//Not implemented yet. It will add a panel of JButtons that the players can use to add more pieces to the board.
public class ViewPieceSelection extends JPanel {
	private static final long serialVersionUID = -7645116281798164489L;
	
	private ButtonController buttonController;
	
	public ViewPieceSelection(ButtonController bc) {
		this.buttonController = bc;
		add(pieces());
	}
	
	private JPanel pieces() {
		JPanel pieces = new JPanel();
		pieces.setLayout(new GridLayout(4, 2));
		ActionListener btnSelect = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				selectAction(e);
			}
		};
		
		JButton king = new JButton("King", resizeIcon(new ImageIcon("images/King.png")));
		king.addActionListener(btnSelect);
		king.setBackground(Color.LIGHT_GRAY);
		JButton archer = new JButton("Archer", resizeIcon(new ImageIcon("images/Archer2.png")));
		archer.addActionListener(btnSelect);
		archer.setBackground(Color.LIGHT_GRAY);
		JButton god = new JButton("God", resizeIcon(new ImageIcon("images/God.png")));
		god.addActionListener(btnSelect);
		god.setBackground(Color.LIGHT_GRAY);
		JButton healer = new JButton("Healer", resizeIcon(new ImageIcon("images/Healer.png")));
		healer.addActionListener(btnSelect);
		healer.setBackground(Color.LIGHT_GRAY);
		JButton queen = new JButton("Queen", resizeIcon(new ImageIcon("images/Queen.png")));
		queen.addActionListener(btnSelect);
		queen.setBackground(Color.LIGHT_GRAY);
		JButton soldier = new JButton("Soldier", resizeIcon(new ImageIcon("images/Soldier2.png")));
		soldier.addActionListener(btnSelect);
		soldier.setBackground(Color.LIGHT_GRAY);
		JButton tank = new JButton("Tank", resizeIcon(new ImageIcon("images/Tank.png")));
		tank.addActionListener(btnSelect);
		tank.setBackground(Color.LIGHT_GRAY);
		JButton wizard = new JButton("Wizard", resizeIcon(new ImageIcon("images/Wizard.png")));
		wizard.addActionListener(btnSelect);
		wizard.setBackground(Color.LIGHT_GRAY);
		
		pieces.add(king);
		pieces.add(archer);
		pieces.add(god);
		pieces.add(healer);
		pieces.add(queen);
		pieces.add(soldier);
		pieces.add(tank);
		pieces.add(wizard);
		
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
}
