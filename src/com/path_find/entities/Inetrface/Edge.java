package com.path_find.entities.Inetrface;

/**
 * Interface representing an arc of a graph
 */
public interface Edge {
    int GetWeight();
    void SetWeight(int weight);
    Node GetTarget();
    void SetTarget(Node target);
}
