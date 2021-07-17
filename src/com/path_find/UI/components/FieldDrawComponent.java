package com.path_find.UI.components;

import com.path_find.entities.Inetrface.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class FieldDrawComponent extends JPanel {
    private int cellSize = 20;
    private boolean[][] unPassed = new boolean[10][10];
    private Node[] _way;

    public FieldDrawComponent () {
        setMaximumSize(new Dimension(201,201));
        setMaximumSize(new Dimension(201,201));
        setPreferredSize(new Dimension(201,201));
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
        List<Rectangle2D> rectangleS = new ArrayList<Rectangle2D>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle2D rectangle = new Rectangle2D.Double(j * cellSize, i * cellSize, cellSize, cellSize);
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
                Ellipse2D ellipse = new Ellipse2D.Double(n.GetPoint().x * cellSize, n.GetPoint().y * cellSize, cellSize, cellSize);
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
