package com.path_find.UI.components;

import com.path_find.entities.Inetrface.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class FieldDrawComponent extends JPanel {
    private int _height;
    private int _width;
    private int _cellSize;
    private boolean[][] unPassed;
    private Node[] _way;

    public FieldDrawComponent() {
        this(10, 10, 20);
    }

    public FieldDrawComponent (int height, int width, int cellSize) {
        _height = height;
        _width = width;
        _cellSize = cellSize;
        setMaximumSize(new Dimension(201,201));
        setMaximumSize(new Dimension(201,201));
        setPreferredSize(new Dimension(201,201));
        unPassed = new boolean[_height][_width];
    }

    public void SetPath(Node[] way) {
        _way = way;
    }

    public void AddWall(int x, int y) {
        unPassed[x][y] = !unPassed[x][y];
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                Rectangle2D rectangle = new Rectangle2D.Double(j * _cellSize, i * _cellSize, _cellSize, _cellSize);
                g2.setPaint(Color.CYAN);
                g2.fill(rectangle);
                if (unPassed[i][j]) {
                    g2.setPaint(Color.black);
                    g2.fill(rectangle);
                }
                g2.setPaint(Color.black);
                g2.draw(rectangle);
            }
        }
        if(_way != null) {
            for (Node n : _way) {
                g2.setColor(Color.GREEN);
                Ellipse2D ellipse = new Ellipse2D.Double(n.GetPoint().x * _cellSize, n.GetPoint().y * _cellSize, _cellSize, _cellSize);
                g2.fill(ellipse);
            }
        }
    }

    public void SetUnPassed(boolean[][] unPassed) {
        this.unPassed = unPassed;
    }

    public void SetTheWay(Node[] theWay) {
        this._way = theWay;
    }
}
