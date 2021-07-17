package com.path_find.entities.Inetrface.square;

import com.path_find.entities.Inetrface.Node;
import com.path_find.entities.Point2D;

public class SquareField {
    private int height = 10;
    private int width = 10;
    private SquareNode[][] nodes;

    public SquareField() {
        nodes = new SquareNode[height][width];
        FillNodes();
        InitNodes();

    }

    private void FillNodes() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                nodes[i][j] = new SquareNode(new Point2D(j, i), this);
            }
        }
    }

    private void InitNodes() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                nodes[i][j].InitEdges();
            }
        }
    }

    public int GetHeigth() {
        return height;
    }

    public int GetWidth() {
        return width;
    }

    public Node GetNode(Point2D point) {
        return nodes[point.y][point.x];
    }

    public boolean[][] GetWallMap() {
        boolean[][] map = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = nodes[i][j].isWall;
                if (nodes[i][j].isWall) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        return map;
    }

    public boolean TogleWall(Point2D point) {
        return nodes[point.y][point.x].isWall = !nodes[point.y][point.x].isWall;
    }
}
