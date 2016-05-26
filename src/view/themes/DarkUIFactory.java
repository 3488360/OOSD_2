package view.themes;


import view.AbstractUIFactory;
import view.ViewCell;

import javax.swing.*;

public class DarkUIFactory extends AbstractUIFactory {

    @Override
    public JLabel createLabel() {
        return new DarkLabel();
    }

    @Override
    public JLabel createLabel(String text) {
        return new DarkLabel(text);
    }

    @Override
    public JButton createButton(String text) {
        return new DarkButton(text);
    }

    @Override
    public JButton createButton(String text, ImageIcon icon) {
        return new DarkButton(text, icon);
    }

    @Override
    public ViewCell createCell(int row, int col, boolean isVisible) {
        return new DarkViewCell(row, col, isVisible);
    }

    @Override
    public JPanel createPanel() {
        return new DarkPanel();
    }
}
