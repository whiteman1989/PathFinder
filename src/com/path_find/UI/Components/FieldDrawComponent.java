package com.path_find.UI.Components;

import com.path_find.entities.Inetrface.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class FieldDrawComponent extends JPanel {
    private int _height;
    private int _width;
    private int _cellSize;
    private boolean[][] unPassed;
    private Node[] _way;
    private Node start;
    private Node finish;

    public FieldDrawComponent() {
        this(10, 10, 20);
    }

    public FieldDrawComponent (int height, int width, int cellSize) {
        _height = height;
        _width = width;
        _cellSize = cellSize;
        Dimension dimension = new Dimension(_width*_cellSize+1,_height*cellSize+1 );
        setMaximumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
        unPassed = new boolean[_height][_width];
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                Rectangle2D rectangle = new Rectangle2D.Double(j * _cellSize, i * _cellSize, _cellSize, _cellSize);
                g2.setPaint(Color.LIGHT_GRAY);
                g2.fill(rectangle);
                if (unPassed[i][j]) {
                    g2.setPaint(Color.black);
                    g2.fill(rectangle);
                }
                g2.setPaint(Color.black);
                g2.draw(rectangle);
            }
        }
        // Draw start
        if(start != null) {
            Rectangle2D startPoint = new Rectangle2D.Double(start.GetPoint().x * _cellSize, start.GetPoint().y * _cellSize, _cellSize, _cellSize);
            g2.setColor(Color.RED);
            g2.fill(startPoint);
        }
        // Draw finish
        if(finish != null) {
            Rectangle2D startPoint = new Rectangle2D.Double(finish.GetPoint().x * _cellSize, finish.GetPoint().y * _cellSize, _cellSize, _cellSize);
            g2.setColor(Color.RED);
            g2.fill(startPoint);
        }
        // Draw path
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

    public void SetStart(Node node) {
        start = node;
    }

    public void SetFinish(Node node) {
        finish = node;
    }
}
