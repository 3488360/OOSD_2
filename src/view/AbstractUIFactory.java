package view;


import model.Board;

import javax.swing.*;

public abstract class AbstractUIFactory {

    public abstract JPanel createPanel();
    public abstract JButton createButton();
    public abstract ViewCell createCell(int row, int col, boolean isVisible);

    public static AbstractUIFactory getFactory(String theme) {
        switch(theme) {
            case "dark":
                return new DarkUIFactory();

            case "classic":
            default:
                return new ClassicUIFactory();
        }
    }
}
