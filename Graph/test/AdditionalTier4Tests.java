import graph.noweight.Edge;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.MaximumFlow;
import graph.withweight.traversal.Weight;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalTier4Tests {
    @Test
    void MaximumTest() {
        Edge ac = new Edge("A", "C");
        Edge ba = new Edge("B", "A");
        Edge cd = new Edge("C", "D");
        Edge db = new Edge("D", "B");
        Edge ce = new Edge("C", "E");
        Edge de = new Edge("D", "E");

        Set<Edge> edges = new HashSet<>();
        edges.add(ac);
        edges.add(ba);
        edges.add(cd);
        edges.add(db);
        edges.add(ce);
        edges.add(de);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ac));
        adjacencyLists.put("B", Set.of(ba));
        adjacencyLists.put("C", Set.of(cd, ce));
        adjacencyLists.put("D", Set.of(db, de));
        adjacencyLists.put("E", Set.of());

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(ac, new Weight(5));
        weights.put(ba, new Weight(3));
        weights.put(cd, new Weight(2));
        weights.put(db, new Weight(3));
        weights.put(ce, new Weight(2));
        weights.put(de, new Weight(10));

        WeightedDirectedGraph graph = new WeightedDirectedGraph(adjacencyLists, weights);

        MaximumFlow mf = new MaximumFlow(graph);
        int flow = mf.run("A", "E");
        assertEquals(4, flow);
    }
}
