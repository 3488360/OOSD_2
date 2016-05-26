package view.themes;


import javax.swing.*;
import java.awt.*;

public class ClassicLabel extends JLabel {
	private static final long serialVersionUID = -8983864566492127253L;

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
