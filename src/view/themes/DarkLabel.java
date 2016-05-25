package view.themes;


import javax.swing.*;
import java.awt.*;

public class DarkLabel extends JLabel {

    public DarkLabel() {
        super();
        init();
    }

    public DarkLabel(String text) {
        super(text);
        init();
    }

    private void init() {
        setForeground(Color.WHITE);
    }
}
