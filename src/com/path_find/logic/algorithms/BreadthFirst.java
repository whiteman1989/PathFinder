package com.path_find.logic.algorithms;

import com.path_find.entities.Inetrface.Edge;
import com.path_find.entities.Inetrface.Node;
import com.path_find.logic.interfaces.PathFinderAlgorithm;

import javax.management.Query;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Breadth First search algorithm
 * (Does not include weight in calculations)
 */
public class BreadthFirst implements PathFinderAlgorithm {
    private Node _startNode;
    private Node _endNode;
    private Queue<Route> openList;
    private List<Route> closeList;
    private Route finish;

    BreadthFirst(Node startNode, Node endNode) {
        _startNode = startNode;
        _endNode = endNode;
        openList = new ArrayDeque<>();
        closeList = new ArrayList<>();
    }

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
    public Node[] GetNodesInPath() {
        List<Node> nodes = new ArrayList<>();
        Route currentRoute = finish;
        while (currentRoute != null) {
            nodes.add(currentRoute.GetTarget());
            currentRoute = currentRoute.GetParent();
        }
        return nodes.toArray(new Node[0]);
    }

    @Override
    public Edge[] GetEdgesInPath() {
        return new Edge[0];
    }

    @Override
    public boolean Execute() {
        Init();
        Route currentRoute;
        while ((currentRoute = openList.poll()) != null) {
            if(currentRoute.GetTarget() == _endNode) {
                return true;
            }
            Edge[] edges = currentRoute.GetTarget().GetEdges();
            for (Edge e : edges) {
                TryAddToOpenList(new Route(e, currentRoute));
            }
            closeList.add(currentRoute);
        }
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

    private void Init() {
        openList.add(new Route(_startNode, null));
    }

    /**
     *
     * @param route new candidate
     * @return true if open or close list not contain this route
     */
    private boolean TryAddToOpenList(Route route) {
        if(openList.stream().anyMatch(r -> r.GetTarget() == route.GetTarget()) ||
        closeList.stream().anyMatch(r -> r.GetTarget() == route.GetTarget())) {
            return false;
        } else {
            openList.offer(route);
            return true;
        }
    }

    public class Route implements Edge {

        private int _weight = 10;
        private Node _target;
        private Route _parent;

        public Route(Node target, Route parent) {
            _target = target;
            _parent = parent;
        }

        public Route(Edge edge, Route parent) {
            _weight = edge.GetWeight();
            _target = edge.GetTarget();
            _parent = parent;
        }

        public Route GetParent() {
            return _parent;
        }

        public void SetParent(Route node) {
            _parent = node;
        }

        @Override
        public int GetWeight() {
            return _weight;
        }

        @Override
        public void SetWeight(int weight) {
            _weight = weight;
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
}
