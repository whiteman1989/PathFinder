package com.path_find.entities.Inetrface.square;

import com.path_find.entities.Inetrface.Edge;
import com.path_find.entities.Inetrface.Node;

public class Arch implements Edge {
    private Node _target;

    public Arch(SquareNode target) {
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
