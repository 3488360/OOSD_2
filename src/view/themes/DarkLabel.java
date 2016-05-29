package view.themes;


import javax.swing.*;
import java.awt.*;

public class DarkLabel extends JLabel {
	private static final long serialVersionUID = 8440022433026408226L;

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
