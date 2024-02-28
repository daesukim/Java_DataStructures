import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.traversal.dfs.DFS;
import graph.noweight.traversal.iter.BFS;
import graph.noweight.traversal.iter.DFSiterative;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.Heap;
import graph.withweight.traversal.Weight;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalTier2Tests {
    @Test
    public void WeightGraphTest () {
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

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(e1, new Weight(1));
        weights.put(e2, new Weight(2));
        weights.put(e3, new Weight(3));
        weights.put(e4, new Weight(4));

        WeightedDirectedGraph graph = new WeightedDirectedGraph(adjacencyLists, weights);

        graph.subtractEdgeWeight(e2, new Weight(1));

        System.out.println("After substracting edge e2 and weight 1 to e2");
        for (Edge key : weights.keySet()) {
            System.out.println(key + " " + weights.get(key));
        }

        System.out.println("Testing WeightedDirectedGraph / copy()");
        System.out.println(graph.outgoingEdges("A"));
        System.out.println(graph.outgoingEdges("B"));
        System.out.println(graph.outgoingEdges("C"));
        System.out.println(graph.outgoingEdges("D"));

        System.out.println("Testing WeightedDirectedGraph / copy()");
        WeightedDirectedGraph copy = graph.copy();
        System.out.println(copy.outgoingEdges("A"));
        System.out.println(copy.outgoingEdges("B"));
        System.out.println(copy.outgoingEdges("C"));
        System.out.println(copy.outgoingEdges("D"));
    }

    @Test
    public void IterBFS(){
        // setting up the graph
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "C");
        Edge e3 = new Edge("B", "D");
        Edge e4 = new Edge("B", "E");
        Edge e5 = new Edge("D", "F");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1, e2));
        adjacencyLists.put("B", Set.of(e3, e4));
        adjacencyLists.put("D", Set.of(e5));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        System.out.println("Testing IterBFS");
        BFS bfs = new BFS(graph, "A");
        bfs.iterativeTraversal();
        List<String> result = bfs.getTraversal();
        for (String node : result) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();


        // another graph in final-fall2022 Question6
        Edge f1 = new Edge("0", "1");
        Edge f2 = new Edge("0", "3");
        Edge f3 = new Edge("1", "2");
        Edge f4 = new Edge("1", "3");
        Edge f5 = new Edge("2", "4");
        Edge f6 = new Edge("3", "2");
        Edge f7 = new Edge("4", "0");
        Edge f8 = new Edge("4", "1");
        Edge f9 = new Edge("4", "5");

        Set<Edge> f_edges = new HashSet<>();
        f_edges.add(f1);
        f_edges.add(f2);
        f_edges.add(f3);
        f_edges.add(f4);
        f_edges.add(f5);
        f_edges.add(f6);
        f_edges.add(f7);
        f_edges.add(f8);
        f_edges.add(f9);

        HashMap<String, Set<Edge>> f_adjacencyLists = new HashMap<>();
        f_adjacencyLists.put("0", Set.of(f1, f2));
        f_adjacencyLists.put("1", Set.of(f3, f4));
        f_adjacencyLists.put("2", Set.of(f5));
        f_adjacencyLists.put("3", Set.of(f6));
        f_adjacencyLists.put("4", Set.of(f7, f8, f9));

        DirectedGraph f_graph = new DirectedGraph(f_adjacencyLists);

        System.out.println("Testing IterBFS for Final20222 Question6");
        BFS f_bfs = new BFS(f_graph, "0");
        f_bfs.iterativeTraversal();
        List<String> f_result = f_bfs.getTraversal();
        for (String node : f_result) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Test
    public void IterDFS(){
        // setting up the graph
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "C");
        Edge e3 = new Edge("B", "D");
        Edge e4 = new Edge("B", "E");
        Edge e5 = new Edge("D", "F");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1, e2));
        adjacencyLists.put("B", Set.of(e3, e4));
        adjacencyLists.put("D", Set.of(e5));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        System.out.println("Testing IterDFS");
        DFSiterative dfs = new DFSiterative(graph, "A");
        dfs.iterativeTraversal();
        List<String> result = dfs.getTraversal();
        for (String node : result) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();

        // another graph in final-fall2022 Question6
        Edge f1 = new Edge("0", "1");
        Edge f2 = new Edge("0", "3");
        Edge f3 = new Edge("1", "2");
        Edge f4 = new Edge("1", "3");
        Edge f5 = new Edge("2", "4");
        Edge f6 = new Edge("3", "2");
        Edge f7 = new Edge("4", "0");
        Edge f8 = new Edge("4", "1");
        Edge f9 = new Edge("4", "5");

        Set<Edge> f_edges = new HashSet<>();
        f_edges.add(f1);
        f_edges.add(f2);
        f_edges.add(f3);
        f_edges.add(f4);
        f_edges.add(f5);
        f_edges.add(f6);
        f_edges.add(f7);
        f_edges.add(f8);
        f_edges.add(f9);

        HashMap<String, Set<Edge>> f_adjacencyLists = new HashMap<>();
        f_adjacencyLists.put("0", Set.of(f1, f2));
        f_adjacencyLists.put("1", Set.of(f3, f4));
        f_adjacencyLists.put("2", Set.of(f5));
        f_adjacencyLists.put("3", Set.of(f6));
        f_adjacencyLists.put("4", Set.of(f7, f8, f9));

        DirectedGraph f_graph = new DirectedGraph(f_adjacencyLists);

        System.out.println("Testing IterDFS for Final20222 Question6");
        DFSiterative f_dfs = new DFSiterative(f_graph, "0");
        f_dfs.iterativeTraversal();
        List<String> f_result = f_dfs.getTraversal();
        for (String node : f_result) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }


    @Test
    public void HeapTest () {
        Set<String> set = Set.of("A", "B", "C", "D", "E", "F", "G");
        Heap heap = new Heap(set);

        heap.setWeight("A", new Weight(3));
        heap.setWeight("B", new Weight(10));
        heap.setWeight("C", new Weight(4));
        heap.setWeight("D", new Weight(5));
        heap.setWeight("E", new Weight(7));
        heap.setWeight("F", new Weight(8));
        heap.setWeight("G", new Weight(9));

        assertEquals("A", heap.extractMin());
    }
}
