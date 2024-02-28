import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.traversal.dfs.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AdditionalTier5Tests {
    @Test
    void DFS() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "C");
        Edge e3 = new Edge("C", "D");
        Edge e4 = new Edge("C", "E");
        Edge e5 = new Edge("D", "F");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1, e2));
        adjacencyLists.put("C", Set.of(e3, e4));
        adjacencyLists.put("D", Set.of(e5));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        DFS dfs = new DFS(graph);
        dfs.traverseFromSource("A");
        System.out.println(dfs.getCurrentTraversal());
    }

    @Test
    void TopologicalSort() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "C");
        Edge e3 = new Edge("C", "D");
        Edge e4 = new Edge("C", "E");
        Edge e5 = new Edge("D", "F");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1, e2));
        adjacencyLists.put("C", Set.of(e3, e4));
        adjacencyLists.put("D", Set.of(e5));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverseFromSource("A");
        List<String> result = List.of("A", "C", "E", "D", "F", "B");
        assertEquals(result, ts.getTraversal());
    }

    @Test
    void CycleDetectionTest() {
        // Cycle Graph
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("B", "C");
        Edge e3 = new Edge("C", "D");
        Edge e4 = new Edge("D", "A");
        Edge e5 = new Edge("B", "F");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1));
        adjacencyLists.put("B", Set.of(e2, e5));
        adjacencyLists.put("C", Set.of(e3));
        adjacencyLists.put("D", Set.of(e4));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        CycleDetection cd = new CycleDetection(graph);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        cd.traverseFromSource("B");
        assertTrue(cd.hasCycle());

        cd.traverseFromSource("F");
        assertTrue(cd.hasCycle());

        // non-cycle graph
        Edge n1 = new Edge("A", "B");
        Edge n2 = new Edge("B", "C");
        Edge n3 = new Edge("C", "D");

        Set<Edge> nedges = new HashSet<>();
        nedges.add(n1);
        nedges.add(n2);
        nedges.add(n3);

        HashMap<String, Set<Edge>> nadjacencyLists = new HashMap<>();
        nadjacencyLists.put("A", Set.of(n1));
        nadjacencyLists.put("B", Set.of(n2));
        nadjacencyLists.put("C", Set.of(n3));

        DirectedGraph ngraph = new DirectedGraph(nadjacencyLists);

        CycleDetection ncd = new CycleDetection(ngraph);
        ncd.traverseFromSource("A");
        assertFalse(ncd.hasCycle());

        ncd.traverseFromSource("B");
        assertFalse(ncd.hasCycle());
    }

    @Test
    void StronglyConnectedTest() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("B", "C");
        Edge e3 = new Edge("C", "D");
        Edge e4 = new Edge("D", "A");
        Edge e5 = new Edge("B", "F");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1));
        adjacencyLists.put("B", Set.of(e2, e5));
        adjacencyLists.put("C", Set.of(e3));
        adjacencyLists.put("D", Set.of(e4));

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.computeSCC();
        assertEquals(List.of("A", "D", "C", "B"), components.get("A"));
        assertEquals(List.of(), components.get("B"));
        assertEquals(List.of(), components.get("C"));
        assertEquals(List.of(), components.get("D"));
        assertEquals(List.of("F"), components.get("F"));
    }

    @Test
    void ReachabilityTest() {
        Edge ad = new Edge("A", "D");
        Edge ab = new Edge("A", "B");
        Edge be = new Edge("B", "E");
        Edge bc = new Edge("B", "C");

        Set<Edge> edges = new HashSet<>();
        edges.add(ad);
        edges.add(ab);
        edges.add(be);
        edges.add(bc);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ad, ab));
        adjacencyLists.put("B", Set.of(be, bc));
        adjacencyLists.put("C", Set.of());
        adjacencyLists.put("D", Set.of());
        adjacencyLists.put("E", Set.of());

        DirectedGraph graph = new DirectedGraph(adjacencyLists);

        Reachability r = new Reachability(graph);
        r.traverseFromSource("A");
        HashMap<String, Set<String>> table = r.getTable();

        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A"), table.get("B"));
        assertEquals(Set.of("A", "B"), table.get("C"));
        assertEquals(Set.of("A"), table.get("D"));
        assertEquals(Set.of("A", "B"), table.get("E"));
    }
}
