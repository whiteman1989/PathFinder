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
    private int  _cellSize = 20;
    JPanel panel = new JPanel();
    FieldDrawComponent fieldDraw = new FieldDrawComponent(_height, _width, _cellSize);
    FieldListener listener = new FieldListener(this);
    SquareField field;
    Node startNode;
    Node finishNode;


    public AppWindow() {
        field = new SquareField(_height, _width);
        startNode = field.GetNode(new Point2D(0,0));
        finishNode = field.GetNode(new Point2D(_width-1, _height-1));
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
        downButtonsPanel.clearButton.addActionListener(new ClearButtonEventListener(this));
        panel.add(downButtonsPanel, BorderLayout.PAGE_END);
        fieldDraw.setBackground(new Color(60,60,60));
        fieldDraw.addMouseListener(listener);
        fieldDraw.SetUnPassed(field.GetWallMap());
        fieldDraw.SetStart(startNode);
        fieldDraw.SetFinish(finishNode);
        this.setVisible(true);
        this.pack();
    }

    public void Repaint() {
        //field.SetUnPassed();
        fieldDraw.repaint();
    }

    public void FinPath() {
        PathFinderAlgorithm algorithm = new  BreadthFirst(startNode, finishNode);
        algorithm.Execute();
        Node[] path = algorithm.GetNodesInPath();
        fieldDraw.SetTheWay(path);
        Repaint();
    }

    public void ClearField() {
        field.ClearField();
        fieldDraw.SetUnPassed(field.GetWallMap());
        fieldDraw.SetTheWay(null);
        Repaint();
    }

    public void ClickOnField(int x, int y) {
        field.ToggleWall(new Point2D(x/_cellSize,y/_cellSize));
        fieldDraw.SetUnPassed(field.GetWallMap());
        Repaint();
    }

    public void RightClickOnField(int x, int y) {
        startNode = finishNode;
        finishNode = field.GetNode(new Point2D( x/_cellSize, y/_cellSize));
        fieldDraw.SetStart(startNode);
        fieldDraw.SetFinish(finishNode);
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
    public void actionPerformed(ActionEvent e) {
        _app.ClearField();
    }
}