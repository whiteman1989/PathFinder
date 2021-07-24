package com.path_find.entities.square;

import com.path_find.entities.Inetrface.Edge;
import com.path_find.entities.Inetrface.Node;
import com.path_find.entities.Point2D;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SquareNode implements Node, Serializable {
    private SquareField _field;
    private Point2D _point;
    List<Edge> _edges;
    public int weight = 10;
    public boolean isWall = false;

    public SquareNode(Point2D point, SquareField field) {
        _point = point;
        _field = field;
        _edges = new ArrayList<Edge>();
    }

    private boolean AddEdge(Point2D point) {
        if(point.x < _field.GetWidth() && point.y < _field.GetHeight() && point.x >= 0 && point.y >= 0) {
            _edges.add(new Arch((SquareNode) _field.GetNode(point)));
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
            SquareNode n = (SquareNode) e.GetTarget();
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
