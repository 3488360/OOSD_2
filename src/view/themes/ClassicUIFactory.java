package view.themes;


import view.AbstractUIFactory;
import view.ViewCell;

import javax.swing.*;

public class ClassicUIFactory extends AbstractUIFactory {

    @Override
    public JLabel createLabel() {
        return new ClassicLabel();
    }

    @Override
    public JLabel createLabel(String text) {
        return new ClassicLabel(text);
    }

    @Override
    public JButton createButton(String text) {
        return new ClassicButton(text);
    }

    @Override
    public JButton createButton(String text, ImageIcon icon) {
        return new ClassicButton(text, icon);
    }

    @Override
    public ViewCell createCell(int row, int col, boolean isVisible) {
        return new ClassicViewCell(row, col, isVisible);
    }
}
