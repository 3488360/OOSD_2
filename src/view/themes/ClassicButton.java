package view.themes;

import javax.swing.*;
import java.awt.*;

public class ClassicButton extends JButton {
	private static final long serialVersionUID = 2336995438536949975L;

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
