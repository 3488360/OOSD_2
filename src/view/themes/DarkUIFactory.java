package view.themes;


import view.AbstractUIFactory;
import view.ViewCell;

import javax.swing.*;

public class DarkUIFactory extends AbstractUIFactory {

    @Override
    public JLabel createLabel() {
        return null;
    }

    @Override
    public JLabel createLabel(String text) {
        return null;
    }

    @Override
    public JButton createButton(String text) {
        return null;
    }

    @Override
    public JButton createButton(String text, ImageIcon icon) {
        return null;
    }

    @Override
    public ViewCell createCell(int row, int col, boolean isVisible) {
        return new DarkViewCell(row, col, isVisible);
    }
}
