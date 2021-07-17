package com.path_find.UI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class FieldDrawComponent extends JPanel {
    private int cellSize = 20;
    private boolean[][] unPassed = new boolean[10][10];
    private boolean[][] theWay = new boolean[10][10];
    private FieldListener listener;

    public FieldDrawComponent () {
        listener = new FieldListener(this);
    }

    public void AddPathPoint(int x, int y) {

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
                if (i % 2 == 0 ^ j % 2 == 0) {
                    g2.setPaint(Color.CYAN);
                    g2.fill(rectangle);
                }
                if (unPassed[i][j]) {
                    g2.setPaint(Color.black);
                    g2.fill(rectangle);
                }
                g2.setPaint(Color.black);
                g2.draw(rectangle);
            }
        }
    }

    public void SetUnPassed(boolean[][] unPassed) {
        this.unPassed = unPassed;
    }

    public void SetTheWay(boolean[][] theWay) {
        this.theWay = theWay;
    }
}
