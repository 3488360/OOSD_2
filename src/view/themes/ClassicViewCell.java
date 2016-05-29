package view.themes;

import model.State;
import view.ViewCell;

import javax.swing.*;
import java.awt.*;

public class ClassicViewCell extends ViewCell {
	private static final long serialVersionUID = 2738875572918280069L;

	public ClassicViewCell(int row, int col, boolean isVisible) {
        super(row, col, isVisible);
    }

    public void resetBorder() {
        setBorder(UIManager.getBorder("Button.border"));
    }

    public void raiseBorder() {
        setBorder(BorderFactory.createLoweredBevelBorder());
    }


    public void setState(State state) {
        Color color;
        switch(state) {

            case PLAYER1:
                color = Color.YELLOW;
                break;

            case PLAYER2:
                color = Color.GRAY;
                break;

            case ATTACK:
                color = Color.RED;
                break;

            case MOVE:
                color = Color.GREEN;
                break;

            case NORMAL:
            default:
                color = Color.ORANGE;
                break;
        }

        setBackground(color);
    }
}
