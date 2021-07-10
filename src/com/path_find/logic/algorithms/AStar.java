package com.path_find.logic.algorithms;

import com.path_find.entities.Inetrface.Node;
import com.path_find.logic.interfaces.PathFinderAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AStar implements PathFinderAlgorithm {
    private Node _startNode;
    private Node _endNode;
    private ArrayList<Route> routesOpenList;
    private ArrayList<Route> routesCloseList;

    @Override
    public void SetStartNode(Node node) {
        _startNode = node;
    }

    @Override
    public Node GetStartNode() {
        return _startNode;
    }

    @Override
    public Node GetEndNode() {
        return _endNode;
    }

    @Override
    public void SetEndNode(Node node) {
        _endNode = node;
    }

    @Override
    public Node[] GetPath() {
        return new Node[0];
    }

    @Override
    public boolean Execute() {
        return false;
    }

    @Override
    public int GetPathLength() {
        return 0;
    }

    @Override
    public int GetPathWeight() {
        return 0;
    }

    private void Step() {
        Route activeRoute = GetMinimumWeigthRoute(routesOpenList);
        routesCloseList.add(activeRoute);
        routesOpenList.remove(activeRoute);
        Node[] conextedNodes = activeRoute.curentNode.GetNeighbors();
        for (Node n: conextedNodes) {
            Route newRoute = new Route(n, activeRoute.curentNode);
            newRoute.H = newRoute.curentNode.GetEvristicDistance(_endNode);
            routesOpenList.add(newRoute);
        }
    }

    private Route GetMinimumWeigthRoute(ArrayList<Route> routes) {
        Route minRoute = Collections.min(routes, Comparator.comparingInt(a -> a.F));
        return minRoute;
    }
}

class Route {
    public Node curentNode;
    public Node parrentNode;
    public int F;
    public int H;
    public int G;

    public Route(Node curentNode, Node parrentNode) {
        this.curentNode = curentNode;
        this.parrentNode = parrentNode;
        F = Integer.MAX_VALUE;
        H = Integer.MAX_VALUE;
        G = 0;
    }
}
