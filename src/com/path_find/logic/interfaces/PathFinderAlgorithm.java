package com.path_find.logic.interfaces;

import com.path_find.entities.Inetrface.Edge;
import com.path_find.entities.Inetrface.Node;

public interface PathFinderAlgorithm {
    void SetStartNode(Node node);
    Node GetStartNode();
    Node GetEndNode();
    void SetEndNode(Node node);
    Node[] GetNodesInPath();
    Edge[] GetEdgesInPath();
    boolean Execute();
    int GetPathLength();
    int GetPathWeight();
}
