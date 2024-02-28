package graph.noweight;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TIER I TODO:
 * <p>
 * This class represents a directed graph. The graph is represented as a
 * collection of nodes and a mapping from each node to its outgoing edges.
 */
public class DirectedGraph {
    protected final Set<String> nodes;
    protected final HashMap<String,Set<Edge>> adjacencyLists;

    public DirectedGraph(HashMap<String,Set<Edge>> adjacencyLists) {
        this.nodes = adjacencyLists.keySet();
        this.adjacencyLists = adjacencyLists;
    }

    public Set<String> getNodes () { return nodes; }
    public HashMap<String,Set<Edge>> getAdjacencyLists () { return adjacencyLists; }

    /**
     * TIER I TODO:
     * Return a set of the outgoing edges from the given node.
     */
    public Set<Edge> outgoingEdges (String node) {
        String startingNode = node;
        Set<Edge> edges = new HashSet<>();
        if (nodes.contains(startingNode)) {
            for (Edge edge : adjacencyLists.get(startingNode)) {
                edges.add(edge);
            }
        }
        return edges;
    }

    /**
     * TIER I TODO:
     * Return a set of the neighbors of the given node.
     */
    public Set<String> neighbors(String node) {
        String startingNode = node;
        Set<String> neighbors = new HashSet<>();
        if (nodes.contains(startingNode)) {
            for (Edge edge : adjacencyLists.get(startingNode)) {
                neighbors.add(edge.destination());
            }
        }
        return neighbors;
    }

    /**
     * TIER I TODO:
     * Remove the given edge from the graph.
     */
    public void removeEdge (Edge edge) {
        adjacencyLists.get(edge.source()).remove(edge);
    }

    /**
     * TIER I TODO:
     * Insert the given edge into the graph.
     */
    public void insertEdge (Edge edge) {
        adjacencyLists.get(edge.source()).add(edge);
    }

    /**
     * TIER I TODO
     * <p>
     * This method should return a new DirectedGraph that is a copy of this one.
     * It is important to create a new copy of the adjacency lists, rather than
     * just returning the existing ones, because otherwise the caller could
     * modify the adjacency lists of the returned graph, which would also modify
     * the original lists.
     */
    public DirectedGraph copy () {
        DirectedGraph copy = new DirectedGraph(adjacencyLists);
        return copy;
    }

    /**
     * TIER I TODO:
     * Return a new DirectedGraph that is the transpose of this graph. In
     * a transpose graph, the direction of every edge is reversed.
     */
    public DirectedGraph transpose () {
        DirectedGraph originalGraph = copy();
        Set<Edge> transposedEdges = new HashSet<>();
        HashMap<String, Set<Edge>> transposedAdjacencyLists = new HashMap<>();

        for (String node : originalGraph.getNodes()) {
            for (Edge edge : originalGraph.outgoingEdges(node)) {
                transposedEdges.add(edge.flip());
            }
        }
        for (Edge item : transposedEdges) {
            if (transposedAdjacencyLists.containsKey(item.source())) {
                transposedAdjacencyLists.get(item.source()).add(item);
            } else {
                transposedAdjacencyLists.put(item.source(), new HashSet<>());
                transposedAdjacencyLists.get(item.source()).add(item);
            }
        }

        return new DirectedGraph(transposedAdjacencyLists);
    }

    /**
     * TIER I TODO:
     * Return a new DirectedGraph that is the bidirectional version of this
     * graph. In a bidirectional graph, for every edge A -> B, there is also
     * an edge B -> A.
     */
    public DirectedGraph bidirectional () {
        DirectedGraph originalGraph = copy();
        DirectedGraph transposedGraph = originalGraph.transpose();
        Set<Edge> bidirectionalEdges = new HashSet<>();
        HashMap<String, Set<Edge>> bidirectionalAdjacencyLists = new HashMap<>();

        for (String node : originalGraph.getNodes()) {
            for (Edge edge : originalGraph.outgoingEdges(node)) {
                bidirectionalEdges.add(edge.flip());
                bidirectionalEdges.add(edge);
            }
        }

        for (Edge item : bidirectionalEdges) {
            if (bidirectionalAdjacencyLists.containsKey(item.source())) {
                bidirectionalAdjacencyLists.get(item.source()).add(item);
            } else {
                bidirectionalAdjacencyLists.put(item.source(), new HashSet<>());
                bidirectionalAdjacencyLists.get(item.source()).add(item);
            }
        }
        return new DirectedGraph(bidirectionalAdjacencyLists);
    }
}
