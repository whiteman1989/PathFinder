package com.path_find.UI;

import com.path_find.UI.components.FieldDrawComponent;
import com.path_find.UI.components.FieldListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppWindow extends JFrame {
    JPanel panel = new JPanel();
    FieldDrawComponent field = new FieldDrawComponent();
    FieldEditorComponent editor = new FieldEditorComponent();
    FieldListener listener = new FieldListener(field);

    public AppWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        this.setBounds(100, 100, 250, 650);
        panel.setLayout(new GridLayout(3, 1, 0, 0));
        container.add(panel);
        panel.add(field);
        panel.add(editor);
        JButton repaintButton = new JButton("Repaint");
        repaintButton.addActionListener(new RepaintButtonEventListener(this));
        panel.add(repaintButton);
        field.setBackground(new Color(60,60,60));
        field.addMouseListener(listener);
        this.setVisible(true);
    }

    public void Repaint() {
        field.SetUnPassed(editor.GetUnPassedmatrix());
        field.repaint();
    }
}

class FieldEditorComponent extends JPanel {
    private JCheckBox[][] unPassed = new JCheckBox[10][10];

    public FieldEditorComponent() {
        setLayout(new GridLayout(10,10,0,0));
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                unPassed[i][j] = new JCheckBox(i+":"+j);
            }
        }
        for (JCheckBox[] row: unPassed) {
            for (JCheckBox cb: row) {
                add(cb);
                System.out.print("1 ");
            }
            System.out.println();
        }
        this.setMinimumSize(new Dimension(200, 200));
    }

    public boolean[][] GetUnPassedmatrix() {
        boolean[][] checked = new boolean[10][10];
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                checked[i][j] = unPassed[i][j].isSelected();
            }
        }
        return checked;
    }
}

class RepaintButtonEventListener implements ActionListener {
    private AppWindow _app;

    public RepaintButtonEventListener(AppWindow app) {
        _app = app;
    }
    public void actionPerformed (ActionEvent e) {
        _app.Repaint();
    }
}