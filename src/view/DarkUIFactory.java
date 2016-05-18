package view;


import javax.swing.*;

public class DarkUIFactory extends AbstractUIFactory {

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
        return new DarkViewCell(row, col, isVisible);
    }
}
