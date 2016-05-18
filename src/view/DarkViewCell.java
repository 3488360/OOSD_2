package view;

import model.State;

import javax.swing.*;
import java.awt.*;

public class DarkViewCell extends ViewCell {
    public DarkViewCell(int row, int col, boolean isVisible) {
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
                color = Color.DARK_GRAY;
                break;

            case PLAYER2:
                color = Color.LIGHT_GRAY;
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
