package com.path_find.entities.Inetrface;

import com.path_find.entities.*;

/**
 * Interface representing a node of a graph
 */
public interface Node {
    Point2D GetPoint();
    void SetPoint(Point2D point);
    Edge[] GetEdges();
    void SetEdges(Edge[] edges);
    boolean TryAddEdge(Edge edge);
}
