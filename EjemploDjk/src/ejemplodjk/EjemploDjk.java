/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplodjk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Darwin
 */
public class EjemploDjk {
    //        B---9--E
    //       /|      |
    //      / |      |
    //     /  |      |
    //    14  2      6
    //   /    |      |
    //  /     |      |
    // A---9--C--11--F
    //  \     |     /
    //   \    |    /
    //    7  10   15
    //     \  |  /
    //      \ | /
    //       \|/
    //        D      G

    public static Graph exampleGraph() {
        Graph g = new SingleGraph("example");
        g.addNode("A").addAttribute("xy", 0, 1);
        g.addNode("B").addAttribute("xy", 1, 2);
        g.addNode("C").addAttribute("xy", 1, 1);
        g.addNode("D").addAttribute("xy", 1, 0);
        g.addNode("E").addAttribute("xy", 2, 2);
        g.addNode("F").addAttribute("xy", 2, 1);
        g.addNode("G").addAttribute("xy", 2, 0);
        g.addEdge("AB", "A", "B").addAttribute("length", 14);
        g.addEdge("AC", "A", "C").addAttribute("length", 9);
        g.addEdge("AD", "A", "D").addAttribute("length", 7);
        g.addEdge("BC", "B", "C").addAttribute("length", 2);
        g.addEdge("CD", "C", "D").addAttribute("length", 10);
        g.addEdge("BE", "B", "E").addAttribute("length", 9);
        g.addEdge("CF", "C", "F").addAttribute("length", 11);
        g.addEdge("DF", "D", "F").addAttribute("length", 15);
        g.addEdge("EF", "E", "F").addAttribute("length", 6);
        for (Node n : g) {
            n.addAttribute("label", n.getId());
        }
        for (Edge e : g.getEachEdge()) {
            e.addAttribute("label", "" + (int) e.getNumber("length"));
        }
        return g;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // TODO code application logic here
        Graph g = exampleGraph();
        g.display(false);

        // Edge lengths are stored in an attribute called "length"
        // The length of a path is the sum of the lengths of its edges
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

        // Compute the shortest paths in g from A to all nodes
        dijkstra.init(g);
        dijkstra.setSource(g.getNode("A"));
        dijkstra.compute();

        // Print the lengths of all the shortest paths
        for (Node node : g) {
            System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
                    dijkstra.getPathLength(node));
        }
        // Color in blue all the nodes on the shortest path form A to B
        for (Node node : dijkstra.getPathNodes(g.getNode("B"))) {
            node.addAttribute("ui.style", "fill-color: blue;");
        }

        // Color in red all the edges in the shortest path tree
        for (Edge edge : dijkstra.getTreeEdges()) {
            edge.addAttribute("ui.style", "fill-color: red;");
        }

        // Print the shortest path from A to B
        System.out.println(dijkstra.getPath(g.getNode("B")));

        // Build a list containing the nodes in the shortest path from A to B
        // Note that nodes are added at the beginning of the list
        // because the iterator traverses them in reverse order, from B to A
        List<Node> list1 = new ArrayList<Node>();
        for (Node node : dijkstra.getPathNodes(g.getNode("B"))) {
            list1.add(0, node);
        }

        // A shorter but less efficient way to do the same thing
        List<Node> list2 = dijkstra.getPath(g.getNode("B")).getNodePath();

        // cleanup to save memory if solutions are no longer needed
        dijkstra.clear();

        // Now compute the shortest path from A to all the other nodes
        // but taking the number of nodes in the path as its length
        //dijkstra  = new Dijkstra(Dijkstra.Element.NODE, null, null);
        dijkstra.init(g);

        dijkstra.setSource(g.getNode("A"));
        dijkstra.compute();

        // Print the lengths of the new shortest paths
        for (Node node : g) {
            System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
                    dijkstra.getPathLength(node));
        }

        // Print all the shortest paths between A and F
        Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(g.getNode("B"));

        while (pathIterator.hasNext()) {
            System.out.println(pathIterator.next());
        }

    }

}
