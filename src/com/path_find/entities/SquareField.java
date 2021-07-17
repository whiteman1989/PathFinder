package com.path_find.entities;

import com.path_find.entities.Inetrface.Edge;
import com.path_find.entities.Inetrface.Node;

import java.util.ArrayList;
import java.util.List;

public class SquareField {
    private int height = 10;
    private int width = 10;
    private SquereNode[][] nodes;

    public SquareField() {
        nodes = new SquereNode[height][width];
        FillNodes();
        InitNodes();

    }

    private void FillNodes() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                nodes[i][j] = new SquereNode(new Point2D(j, i), this);
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
                if(nodes[i][j].isWall) {
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

class SquereNode implements Node {
    private SquareField _field;
    private Point2D _point;
    List<Edge> _edges;
    public int weight = 10;
    public boolean isWall = false;

    public SquereNode(Point2D point, SquareField field) {
        _point = point;
        _field = field;
        _edges = new ArrayList<Edge>();
    }

    private boolean AddEdge(Point2D point) {
        if(point.x < _field.GetWidth() && point.y < _field.GetHeigth() && point.x >= 0 && point.y >= 0) {
            _edges.add(new Arch((SquereNode) _field.GetNode(point)));
            return true;
        } else {
            return false;
        }
    }

    public void InitEdges() {
        AddEdge(new Point2D(_point.x, _point.y-1));
        AddEdge(new Point2D(_point.x, _point.y+1));
        AddEdge(new Point2D(_point.x-1, _point.y));
        AddEdge(new Point2D(_point.x+1, _point.y));
    }

    @Override
    public Point2D GetPoint() {
        return _point;
    }

    @Override
    public void SetPoint(Point2D point) {
        System.out.println("this Node not movable");
    }

    @Override
    public Edge[] GetEdges() {
        List<Edge> edges = new ArrayList<Edge>();
        for (Edge e: _edges) {
            SquereNode n = (SquereNode) e.GetTarget();
            if(!n.isWall) {
                edges.add(e);
            }
        }
        return edges.toArray(new Edge[0]);
    }

    @Override
    public void SetEdges(Edge[] edges) {

    }

    @Override
    public boolean TryAddEdge(Edge edge) {
        return false;
    }
}

class Arch implements Edge {
    private Node _target;

    public Arch(SquereNode target) {
        _target = target;
    }

    @Override
    public int GetWeight() {
        return 10;
    }

    @Override
    public void SetWeight(int weight) {
    }

    @Override
    public Node GetTarget() {
        return _target;
    }

    @Override
    public void SetTarget(Node target) {
        _target = target;
    }
}
