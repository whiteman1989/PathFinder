package com.path_find.entities.Inetrface;

import com.path_find.entities.*;

public interface Node {
    public Node[] GetNeighbors();
    public int GetRouteWeigth(Node target);
    public String GetName();
    public Point2D GetPoint2D();
    public boolean CheckIfPassable();
    public int GetEvristicDistance(Node target);
}
