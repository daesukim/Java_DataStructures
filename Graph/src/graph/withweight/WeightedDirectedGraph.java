package graph.withweight;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.withweight.traversal.Weight;

import java.util.*;

/**
 * This class extended DirectedGraph with information about the weights of
 * the edges. The weights are maintained separately from the adjacency lists
 * in a HashMap.
 */
public class WeightedDirectedGraph extends DirectedGraph {
    private final HashMap<Edge,Weight> weights;
    public WeightedDirectedGraph(HashMap<String,Set<Edge>> adjacencyLists, HashMap<Edge,Weight> weights) {
        super(adjacencyLists);
        this.weights = weights;
    }

    public HashMap<Edge,Weight> getWeights() {
        return weights;
    }

    /**
     * TIER II TODO
     * <p>
     * Subtract the given weight from the weight of the given edge. If the result is zero,
     * remove the edge from the graph.
     */
    public void subtractEdgeWeight (Edge edge, Weight diff) {
        if (weights.containsKey(edge)) {
            Weight newWeight = weights.get(edge).subtract(diff);
            if (newWeight.compareTo(new Weight(0)) <= 0) {
                weights.remove(edge);
                adjacencyLists.get(edge.source()).remove(edge);
            } else {
                weights.put(edge, newWeight);
            }
        }
    }

    /**
     * TIER II TODO
     * <p>
     * Add the given weight to the weight of the given edge. If the edge is not in the graph,
     * add it.
     */
    public void insertEdge (Edge edge, Weight weight) {
        super.insertEdge(edge);
        if (weights.containsKey(edge)) {
            Weight newWeight = weights.get(edge).add(weight);
            weights.put(edge, newWeight);
        }
        else{
            weights.put(edge, weight);
        }
    }
    /**
     * TIER II TODO
     * <p>
     * This method should return a new WeightedDirectedGraph that is a copy of this one.
     * It is important to create a new copy of the adjacency lists and weights, rather than
     * just returning the existing ones, because otherwise the caller could modify the
     * adjacency lists or weights of the returned graph, which would also modify
     * the original graph.
     */
    public WeightedDirectedGraph copy () {
        HashMap<String,Set<Edge>> copyAdjacencyLists = new HashMap<>();
        HashMap<Edge,Weight> copyWeights = new HashMap<>();
        for (String key : adjacencyLists.keySet()) {
            Set<Edge> copyEdges = new HashSet<>();
            for (Edge edge : adjacencyLists.get(key)) {
                copyEdges.add(edge);
            }
            copyAdjacencyLists.put(key, copyEdges);
        }
        for (Edge key : weights.keySet()) {
            copyWeights.put(key, weights.get(key));
        }
        return new WeightedDirectedGraph(copyAdjacencyLists, copyWeights);
    }

    public boolean equals (Object o) {
        if (o instanceof WeightedDirectedGraph other) {
            return adjacencyLists.equals(other.adjacencyLists) && weights.equals(other.weights);
        }
        return false;
    }
}
