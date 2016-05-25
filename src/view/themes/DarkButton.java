package view.themes;


import javax.swing.*;
import java.awt.*;

public class DarkButton extends JButton {

    public DarkButton(String text) {
        super(text);
        init();
    }

    public DarkButton(String text, Icon icon) {
        super(text, icon);
        init();
    }

    private void init() {
        setBackground(Color.decode("#888888"));
        setForeground(Color.WHITE);
    }
}
