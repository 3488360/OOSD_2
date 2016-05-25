package view.themes;

import model.State;
import view.ViewCell;

import javax.swing.*;
import java.awt.*;

public class DarkViewCell extends ViewCell {
    private static final Color ATTACK = Color.decode("#570000");
    private static final Color PLAYER1 = Color.LIGHT_GRAY;
    private static final Color PLAYER2 = Color.DARK_GRAY;
    private static final Color MOVE = Color.decode("#005700");
    private static final Color NORMAL = Color.decode("#000057");
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
                color = PLAYER1;
                break;

            case PLAYER2:
                color = PLAYER2;
                break;

            case ATTACK:
                color = ATTACK;
                break;

            case MOVE:
                color = MOVE;
                break;

            case NORMAL:
            default:
                color = NORMAL;
                break;
        }

        setBackground(color);
    }
}
