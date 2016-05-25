package view.themes;

import javax.swing.*;
import java.awt.*;

public class ClassicButton extends JButton {

    public ClassicButton(String text, Icon icon) {
        super(text, icon);
        init();
    }

    public ClassicButton(String text) {
        super(text);
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
    }
}
