package com.path_find.UI.Components;

import javax.swing.*;
import java.awt.*;

public class UpButtonsPanel extends JPanel {
    public JButton saveButton;
    public JButton loadButton;

    public UpButtonsPanel() {
        saveButton = new JButton("SAVE");
        loadButton = new JButton("LOAD");

        this.setLayout(new FlowLayout());
        this.add(saveButton);
        this.add(loadButton);
    }
}
