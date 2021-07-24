package com.path_find.UI;

import com.path_find.UI.components.DownButtonsPanel;
import com.path_find.UI.components.FieldDrawComponent;
import com.path_find.UI.components.FieldListener;
import com.path_find.entities.Inetrface.Node;
import com.path_find.entities.Point2D;
import com.path_find.entities.square.SquareField;
import com.path_find.logic.algorithms.BreadthFirst;
import com.path_find.logic.interfaces.PathFinderAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppWindow extends JFrame {
    private int _width = 20;
    private int _height = 20;
    JPanel panel = new JPanel();
    FieldDrawComponent fieldDraw = new FieldDrawComponent(_height, _width, 20);
    FieldListener listener = new FieldListener(this);
    SquareField field;


    public AppWindow() {
        field = new SquareField(_height, _width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        this.setBounds(100, 100, 250, 400);
        panel.setLayout(new BorderLayout(1,1));
        container.add(panel);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        panel.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(fieldDraw);
        fieldDraw.setAlignmentX(Component.CENTER_ALIGNMENT);
        DownButtonsPanel downButtonsPanel = new DownButtonsPanel();
        downButtonsPanel.findButton.addActionListener(new FindButtonEventListener(this));
        panel.add(downButtonsPanel, BorderLayout.PAGE_END);
        fieldDraw.setBackground(new Color(60,60,60));
        fieldDraw.addMouseListener(listener);
        fieldDraw.SetUnPassed(field.GetWallMap());
        this.setVisible(true);
        this.pack();
    }

    public void Repaint() {
        //field.SetUnPassed();
        fieldDraw.repaint();
    }

    public void FinPath() {
        PathFinderAlgorithm algorithm = new  BreadthFirst(field.GetNode(new Point2D(0,0)),
                field.GetNode(new Point2D(9,9)));
        algorithm.Execute();
        Node[] path = algorithm.GetNodesInPath();
        fieldDraw.SetTheWay(path);
        Repaint();
    }

    public void ClickOnField(int x, int y) {
        field.ToggleWall(new Point2D(x/20,y/20));
        fieldDraw.SetUnPassed(field.GetWallMap());
        Repaint();
    }
}

class FindButtonEventListener implements ActionListener {
    private AppWindow _app;

    public FindButtonEventListener(AppWindow app) {
        _app = app;
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        _app.FinPath();
    }
}

class ClearButtonEventListener implements ActionListener {
    private AppWindow _app;

    public ClearButtonEventListener(AppWindow app) {_app = app;}
    @Override
    public void actionPerformed(ActionEvent e) { }
}