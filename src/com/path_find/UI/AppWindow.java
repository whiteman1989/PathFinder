package com.path_find.UI;

import com.path_find.UI.Components.DownButtonsPanel;
import com.path_find.UI.Components.FieldDrawComponent;
import com.path_find.UI.Components.FieldListener;
import com.path_find.UI.Components.UpButtonsPanel;
import com.path_find.UI.EventListeners.*;
import com.path_find.entities.Inetrface.Node;
import com.path_find.entities.Point2D;
import com.path_find.entities.square.SquareField;
import com.path_find.logic.algorithms.BreadthFirst;
import com.path_find.logic.algorithms.PrimAlgorithm;
import com.path_find.logic.interfaces.PathFinderAlgorithm;

import javax.swing.*;
import java.awt.*;

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
    String message = "It is not possible to build a path. The points are isolated!";


    public AppWindow() {
        super("Path finder PPZ-41");
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
        // init down panel
        DownButtonsPanel downButtonsPanel = new DownButtonsPanel();
        downButtonsPanel.findButton.addActionListener(new FindButtonEventListener(this));
        downButtonsPanel.clearButton.addActionListener(new ClearButtonEventListener(this));
        downButtonsPanel.generateButton.addActionListener(new GenerateButtonEventListener(this));
        panel.add(downButtonsPanel, BorderLayout.PAGE_END);
        //init up panel
        UpButtonsPanel upPanel = new UpButtonsPanel();
        upPanel.saveButton.addActionListener(new SaveButtonEventListener(this));
        upPanel.loadButton.addActionListener(new LoadButtonEventListener(this));
        panel.add(upPanel, BorderLayout.PAGE_START);
        fieldDraw.setBackground(new Color(60,60,60));
        fieldDraw.addMouseListener(listener);
        fieldDraw.SetUnPassed(field.GetWallMap());
        fieldDraw.SetStart(startNode);
        fieldDraw.SetFinish(finishNode);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
    }

    public void Repaint() {
        //field.SetUnPassed();
        fieldDraw.repaint();
    }

    public void FinPath() {
        PathFinderAlgorithm algorithm = new  BreadthFirst(startNode, finishNode);
        if(algorithm.Execute()) {
            Node[] path = algorithm.GetNodesInPath();
            fieldDraw.SetTheWay(path);
            Repaint();
        } else {
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
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

    public void Save() {
        field.SerializeNodes();
    }

    public void Load() {
        field.DeserializeNodes();
        fieldDraw.SetUnPassed(field.GetWallMap());
        Repaint();
    }

    public void Generate() {
        PrimAlgorithm algorithm = new  PrimAlgorithm(_height, _width);
        algorithm.Execute();
        field.WallMapToNodes(algorithm.GetMap());
        fieldDraw.SetUnPassed(algorithm.GetMap());
        Repaint();
    }


}

