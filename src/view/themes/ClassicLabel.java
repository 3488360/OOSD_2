package view.themes;


import javax.swing.*;
import java.awt.*;

public class ClassicLabel extends JLabel {

    public ClassicLabel() {
        super();
        init();
    }
    public ClassicLabel(String text) {
        super(text);
        init();
    }

    private void init() {
        setForeground(Color.BLACK);
    }
}
