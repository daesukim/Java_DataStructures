import graph.noweight.Edge;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.AllShortestPaths;
import graph.withweight.traversal.Heap;
import graph.withweight.traversal.MinimumSpanningTree;
import graph.withweight.traversal.Weight;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalTier3Tests {
    @Test
    void MinimumSpanningTree() {
        // bidirectional graph
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "C");
        Edge e3 = new Edge("B", "C");
        Edge e4 = new Edge("B", "D");
        Edge e5 = new Edge("C", "D");
        Edge e6 = new Edge("B", "A");
        Edge e7 = new Edge("C", "A");
        Edge e8 = new Edge("C", "B");
        Edge e9 = new Edge("D", "B");
        Edge e10 = new Edge("D", "C");

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
        edges.add(e5);
        edges.add(e6);
        edges.add(e7);
        edges.add(e8);
        edges.add(e9);
        edges.add(e10);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1, e2));
        adjacencyLists.put("B", Set.of(e3, e4, e6));
        adjacencyLists.put("C", Set.of(e5, e7, e8));
        adjacencyLists.put("D", Set.of(e9, e10));

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(e1, new Weight(1));
        weights.put(e2, new Weight(3));
        weights.put(e3, new Weight(5));
        weights.put(e4, new Weight(2));
        weights.put(e5, new Weight(4));
        weights.put(e6, new Weight(1));
        weights.put(e7, new Weight(3));
        weights.put(e8, new Weight(5));
        weights.put(e9, new Weight(2));
        weights.put(e10, new Weight(4));

        WeightedDirectedGraph graph = new WeightedDirectedGraph(adjacencyLists, weights);

        MinimumSpanningTree mst = new MinimumSpanningTree(graph,"A");
        mst.iterativeTraversal();
        assertEquals(6, mst.getWeight());

        HashMap<String,String> result = mst.getPreviousNodes();
        assertEquals("A", result.get("C"));
        assertEquals("A", result.get("B"));
        assertEquals("B", result.get("D"));

        // second MST
        Edge ab = new Edge("A", "B");
        Edge ad = new Edge("A", "D");
        Edge bc = new Edge("B", "C");
        Edge bd = new Edge("B", "D");
        Edge bg = new Edge("B", "G");
        Edge cg = new Edge("C", "G");
        Edge ce = new Edge("C", "E");
        Edge df = new Edge("D", "F");
        Edge dg = new Edge("D", "G");
        Edge fg = new Edge("F", "G");
        Edge eg = new Edge("E", "G");

        //flipped
        Edge ba = new Edge("B", "A");
        Edge da = new Edge("D", "A");
        Edge cb = new Edge("C", "B");
        Edge db = new Edge("D", "B");
        Edge gb = new Edge("G", "B");
        Edge gc = new Edge("G", "C");
        Edge ec = new Edge("E", "C");
        Edge fd = new Edge("F", "D");
        Edge gd = new Edge("G", "D");
        Edge gf = new Edge("G", "F");
        Edge ge = new Edge("G", "E");

        Set<Edge> mstEdges = new HashSet<>();
        mstEdges.add(ab);
        mstEdges.add(ad);
        mstEdges.add(bc);
        mstEdges.add(bd);
        mstEdges.add(bg);
        mstEdges.add(cg);
        mstEdges.add(ce);
        mstEdges.add(df);
        mstEdges.add(dg);
        mstEdges.add(fg);
        mstEdges.add(eg);
        mstEdges.add(ba);
        mstEdges.add(da);
        mstEdges.add(cb);
        mstEdges.add(db);
        mstEdges.add(gb);
        mstEdges.add(gc);
        mstEdges.add(ec);
        mstEdges.add(fd);
        mstEdges.add(gd);
        mstEdges.add(gf);
        mstEdges.add(ge);

        HashMap<String, Set<Edge>> mstAdjacencyLists = new HashMap<>();
        mstAdjacencyLists.put("A", Set.of(ab, ad));
        mstAdjacencyLists.put("B", Set.of(ba, bc, bd, bg));
        mstAdjacencyLists.put("C", Set.of(cb, cg, ce));
        mstAdjacencyLists.put("D", Set.of(da, db, df, dg));
        mstAdjacencyLists.put("E", Set.of(ec, eg));
        mstAdjacencyLists.put("F", Set.of(fd, fg));
        mstAdjacencyLists.put("G", Set.of(gd, gb, gc, gf, ge));


    }

    @Test
    void ShortestPath() {
        Edge e1 = new Edge("A", "B");
        Edge e4 = new Edge("B", "D");
        Edge e7 = new Edge("C", "A");
        Edge e8 = new Edge("C", "B");
        Edge e10 = new Edge("D", "C");

        Set<String> nodes = new HashSet<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e4);
        edges.add(e7);
        edges.add(e8);
        edges.add(e10);

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(e1));
        adjacencyLists.put("B", Set.of(e4));
        adjacencyLists.put("C", Set.of(e7, e8));
        adjacencyLists.put("D", Set.of(e10));

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(e1, new Weight(1));
        weights.put(e4, new Weight(2));
        weights.put(e7, new Weight(3));
        weights.put(e8, new Weight(5));
        weights.put(e10, new Weight(4));

        WeightedDirectedGraph graph = new WeightedDirectedGraph(adjacencyLists, weights);


        AllShortestPaths sp = new AllShortestPaths(graph, "A");
        sp.iterativeTraversal();

        HashMap<String,String> result = sp.getPreviousNodes();
        assertEquals("A", result.get("B"));
        assertEquals("B", result.get("D"));
        assertEquals("D", result.get("C"));

        System.out.println(sp.getPath("B"));


        // second Shortest path
        Edge sa = new Edge("S", "A");
        Edge sc = new Edge("S", "C");
        Edge as = new Edge("A", "S");
        Edge ac = new Edge("A", "C");
        Edge cd = new Edge("C", "D");
        Edge db = new Edge("D", "B");
        Edge ba = new Edge("B", "A");
        Edge bt = new Edge("B", "T");
        Edge tb = new Edge("T", "B");
        Edge dt = new Edge("D", "T");

        Set<Edge> shortEdges = new HashSet<>();
        shortEdges.add(sa);
        shortEdges.add(sc);
        shortEdges.add(as);
        shortEdges.add(ac);
        shortEdges.add(cd);
        shortEdges.add(db);
        shortEdges.add(ba);
        shortEdges.add(bt);
        shortEdges.add(tb);
        shortEdges.add(dt);

        HashMap<String, Set<Edge>> ShortAdjacencyLists = new HashMap<>();
        ShortAdjacencyLists.put("S", Set.of(sa, sc));
        ShortAdjacencyLists.put("A", Set.of(as, ac));
        ShortAdjacencyLists.put("B", Set.of(ba, bt));
        ShortAdjacencyLists.put("C", Set.of(cd));
        ShortAdjacencyLists.put("D", Set.of(db, dt));
        ShortAdjacencyLists.put("T", Set.of(tb));

        HashMap<Edge, Weight> shortWeights = new HashMap<>();
        shortWeights.put(sa, new Weight(5));
        shortWeights.put(sc, new Weight(8));
        shortWeights.put(as, new Weight(5));
        shortWeights.put(ac, new Weight(2));
        shortWeights.put(cd, new Weight(10));
        shortWeights.put(db, new Weight(8));
        shortWeights.put(ba, new Weight(5));
        shortWeights.put(bt, new Weight(5));
        shortWeights.put(tb, new Weight(2));
        shortWeights.put(dt, new Weight(10));

        WeightedDirectedGraph shortGraph = new WeightedDirectedGraph(ShortAdjacencyLists, shortWeights);

        AllShortestPaths shortSp = new AllShortestPaths(shortGraph, "S");
        shortSp.iterativeTraversal();
        shortSp.getPath("T");
        System.out.println(shortSp.getPath("T"));


    }
}
