package view;


import javax.swing.*;

public class ClassicUIFactory extends AbstractUIFactory {
    @Override
    public JPanel createPanel() {
        return null;
    }

    @Override
    public JButton createButton() {
        return null;
    }

    @Override
    public ViewCell createCell(int row, int col, boolean isVisible) {
        return new ClassicViewCell(row, col, isVisible);
    }
}
