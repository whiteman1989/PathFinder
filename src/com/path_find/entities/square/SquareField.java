package com.path_find.entities.square;

import com.path_find.entities.Inetrface.Node;
import com.path_find.entities.Point2D;

public class SquareField {
    private int _height;
    private int _width;
    private SquareNode[][] nodes;

    public SquareField() {
        this(10, 10);
    }

    public SquareField(int height, int width) {
        _height = height;
        _width = width;
        nodes = new SquareNode[_height][_width];
        FillNodes();
        InitNodes();

    }

    private void FillNodes() {
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                nodes[i][j] = new SquareNode(new Point2D(j, i), this);
            }
        }
    }

    private void InitNodes() {
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                nodes[i][j].InitEdges();
            }
        }
    }

    public int GetHeight() {
        return _height;
    }

    public int GetWidth() {
        return _width;
    }

    public Node GetNode(Point2D point) {
        return nodes[point.y][point.x];
    }

    public boolean[][] GetWallMap() {
        boolean[][] map = new boolean[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                map[i][j] = nodes[i][j].isWall;
            }
        }
        return map;
    }

    public boolean ToggleWall(Point2D point) {
        return nodes[point.y][point.x].isWall = !nodes[point.y][point.x].isWall;
    }

    public void ClearField() {
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                nodes[i][j].isWall = false;
            }
        }
    }
}
