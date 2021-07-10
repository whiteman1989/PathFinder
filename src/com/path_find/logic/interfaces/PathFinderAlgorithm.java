package com.path_find.logic.interfaces;

import com.path_find.entities.Inetrface.Node;

public interface PathFinderAlgorithm {
    public void SetStartNode(Node node);
    public Node GetStartNode();
    public Node GetEndNode();
    public void SetEndNode(Node node);
    public Node[] GetPath();
    public boolean Execute();
    public int GetPathLength();
    public int GetPathWeight();
}
