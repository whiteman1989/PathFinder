package com.path_find.UI.components;

import javax.swing.*;
import java.awt.*;

public class DownButtonsPanel extends JPanel {
    public JButton findButton;
    public JButton clearButton;
    public JButton generateButton;

    public DownButtonsPanel () {
        findButton = new JButton("FIND PATH");
        generateButton = new JButton("GENERATE FIELD");
        clearButton = new JButton("CLEAR FIELD");

        this.setLayout(new FlowLayout());
        this.add(findButton);
        this.add(clearButton);
        this.add(generateButton);
    }
}
