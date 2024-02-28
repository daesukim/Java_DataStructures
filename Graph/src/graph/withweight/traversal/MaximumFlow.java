package graph.withweight.traversal;


import graph.noweight.Edge;
import graph.noweight.Path;
import graph.withweight.WeightedDirectedGraph;

import java.util.HashMap;
import java.util.Iterator;

/**
 * TIER IV TODO
 * <p>
 * Imagine pouring water into a graph. The water flows from the source to the
 * destination. Obviously the amount of water going into a node must equal the
 * amount of water going out of a node. The edges have a maximum capacity of
 * water that can flow through them. The maximum flow is the maximum amount of
 * water that can flow from the source to the destination.
 * <p>
 * The Ford-Fulkerson algorithm (see the method run below) will compute two results:
 * the method returns an int which is the maximum amount that can flow through the graph
 * and updates the instance variable flow which the amount flowing along each edge.
 */
public class MaximumFlow {
    private final WeightedDirectedGraph graph;
    private final HashMap<Edge, Weight> flow;

    public MaximumFlow(WeightedDirectedGraph graph) {
        this.graph = graph;
        this.flow = new HashMap<>();
        for (Edge e : graph.getWeights().keySet()) {
            flow.put(e, Weight.ZERO);
        }
    }

    public HashMap<Edge, Weight> getFlow() {
        return flow;
    }

    /**
     * TIER IV TODO
     * <p>
     * We are given a path and a certain amount currentFlow. The method
     * updates the instance variable flow by adding currentFlow to the weight
     * of each edge in the given path.
     */
    public void augmentFlow (HashMap<Edge,Weight> flow, Path path, Weight currentFlow) {
        for (Edge e : path) {
            Weight w = flow.get(e);
            flow.put(e, w.add(currentFlow));
        }
    }

    /**
     * TIER IV TODO
     * <p>
     * We are given a path and a certain amount currentFlow. The method
     * updates the residual graph in two ways: it subtracts currentFlow from the
     * weight of every edge in the path, and for every edge in the given path,
     * it inserts a flipped version with weight currentFlow.
     */
    public void updateResidual (WeightedDirectedGraph residual, Path path, Weight currentFlow) {
        for (Edge e : path) {
            residual.subtractEdgeWeight(e, currentFlow);
            Edge flipped = new Edge(e.destination(), e.source());
            residual.insertEdge(flipped, currentFlow);
        }
//        for (Edge e : path) {
//            Weight previousWeight = residual.getWeights().get(e);
//            Weight newWeight = previousWeight.subtract(currentFlow);
//            graph.getWeights().put(e, newWeight);
//            Edge flipped = new Edge(e.destination(), e.source());
//            graph.insertEdge(flipped, newWeight);
//        }
    }

    /**
     * TIER IV TODO
     * <p>
     * Computes the maximum flow from source to destination. The algorithm
     * works by iterating over the shortest path algorithm.
     * <p>
     * We start with a residual graph which is a copy of the current graph.
     * We compute the shortest path from the source to the destination in that
     * residual graph. The "current flow" is the minimum weight along that
     * path.
     * <p>
     * We then update the hashmap flow by augmenting the edges along the
     * path with the current flow. We then update the residual graph
     * by subtracting the current flow from the weight of each edge in the path
     * and adding a flipped edge with weight current flow.
     * <p>
     * We repeat until there is no possible path in the residual graph.
     */
    public int run (String source, String destination) {
        Weight totalFlow = Weight.ZERO;
        WeightedDirectedGraph residual = graph.copy();

        while (true) {
            AllShortestPaths shortestPaths = new AllShortestPaths(residual, source);
            shortestPaths.iterativeTraversal();
            Path path = shortestPaths.getPath(destination);

            if (path.isEmpty()) {
                return totalFlow.value();
            }

            Weight currentFlow = path.minFlow();
            augmentFlow(flow, path, currentFlow);
            updateResidual(residual, path, currentFlow);
            totalFlow = totalFlow.add(currentFlow);
        }
    }
}
