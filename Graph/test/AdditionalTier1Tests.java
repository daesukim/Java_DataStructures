import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.Path;
import graph.withweight.traversal.Weight;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AdditionalTier1Tests {
    @Test
    public void DirectedGraph () {
        // setting up the graph
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "C");
        Edge e3 = new Edge("B", "C");
        Edge e4 = new Edge("B", "D");

        Set<String> nodes = new HashSet<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1, e2));
        adjacencyLists.put("B", Set.of(e3, e4));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        // -- end of setting ---

        System.out.println("Testing DirectedGraph / outgoingEdges from A");
        System.out.println(graph.outgoingEdges("A"));


        System.out.println("Testing DirectedGraph / Neighbors from A");
        System.out.println(graph.neighbors("B"));

        System.out.println("Testing DirectedGraph / copy()");
        DirectedGraph copy = graph.copy();
        System.out.println(copy.outgoingEdges("A"));
        System.out.println(copy.outgoingEdges("B"));
        System.out.println(copy.outgoingEdges("C"));
        System.out.println(copy.outgoingEdges("D"));

        System.out.println("Testing DirectedGraph / transpose()");
        DirectedGraph transpose = graph.transpose();
        System.out.println(transpose.outgoingEdges("A"));
        System.out.println(transpose.outgoingEdges("B"));
        System.out.println(transpose.outgoingEdges("C"));
        System.out.println(transpose.outgoingEdges("D"));


        System.out.println("Testing DirectedGraph / bidirectional()");
        graph = graph.bidirectional();
        System.out.println(graph.outgoingEdges("A"));
        System.out.println(graph.outgoingEdges("B"));
        System.out.println(graph.outgoingEdges("C"));
        System.out.println(graph.outgoingEdges("D"));

        Edge e5 = new Edge("A", "D");
        graph.insertEdge(e5);
        System.out.println(graph.outgoingEdges("A"));


        System.out.println("Testing DirectedGraph / removeEdge()");
        graph.removeEdge(e5);
        graph.removeEdge(e5);
        Edge e6 = new Edge("A", "C");
        graph.removeEdge(e6);
        System.out.println(graph.outgoingEdges("A"));

        System.out.println("Path Test");
        Path p = new Path();
        Edge p_edge = new Edge("A", "B");
        Weight p_edge_weight = new Weight(1);

        Edge p_edge2 = new Edge("B", "C");
        Weight p_edge_weight2 = new Weight(2);

        Edge p_edge3 = new Edge("C", "D");
        Weight p_edge_weight3 = new Weight(0);

        p.add(p_edge, p_edge_weight);
        p.add(p_edge2, p_edge_weight2);
        p.add(p_edge3, p_edge_weight3);

        System.out.println(p.minFlow());

        for (Edge edge : p.edges()) {
            System.out.println(edge);
        }

        System.out.println("After Reverse");
        p.reverse();

        // view order of edges
        for (Edge edge : p.edges()) {
            System.out.println(edge);
        }
    }

    @Test
    public void WeightTest () {
        Weight w1 = new Weight(1);
        Weight w2 = new Weight(2);

        Weight addedWeight = w1.add(w2);
        System.out.println(addedWeight.value());

        System.out.println(w2.compareTo(w1));
        Weight min = Weight.min(w1, w2);
        System.out.println(min.value());
    }
}
