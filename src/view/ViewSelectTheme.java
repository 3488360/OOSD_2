package view;


import controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSelectTheme extends JFrame {
	private static final long serialVersionUID = -4327327675225533114L;
	private JComboBox<String> comboBox;
    private static final String[] availableFactories = {"classic", "dark"};

    public ViewSelectTheme() {
        initialise();
    }

    private void initialise() {
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Select a Theme");

        comboBox = new JComboBox<>(availableFactories);
        add(comboBox, BorderLayout.NORTH);

        JButton button = new JButton("Go");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewSelectTheme.this.setVisible(false);
                Main.start(availableFactories[comboBox.getSelectedIndex()]);

            }
        });
        add(button, BorderLayout.SOUTH);
    }

}
