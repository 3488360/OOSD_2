package view;


import view.themes.ClassicUIFactory;
import view.themes.DarkUIFactory;

import javax.swing.*;

public abstract class AbstractUIFactory {

    public abstract JLabel createLabel();
    public abstract JLabel createLabel(String text);
    public abstract JButton createButton(String text);
    public abstract JButton createButton(String text, ImageIcon icon);
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
