/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplogs;

import java.util.Collection;
import java.util.Iterator;
import org.graphstream.algorithm.randomWalk.RandomWalk;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 *
 * @author Darwin
 */
public class EjemploGS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph = new SingleGraph("Tutorial 1");
        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

        Node A = graph.getNode("AB");
        Edge AB = graph.getEdge("AB");
        System.out.println();
        System.out.println(AB.getId());
        System.out.println(AB.isDirected() ? 1 : 0);

        //graph.display();

        for (Node n : graph) {
            System.out.println(n.getId());
        }
        for (Edge e : graph.getEachEdge()) {
            System.out.println(e.getId());
        }
        System.out.println();
        Collection<Node> nodes = graph.getNodeSet();
        Collection<Edge> edges = graph.getEdgeSet();

        Iterator< ? extends Node> nodes1;
        nodes1 = graph.getNodeIterator();

        while (nodes1.hasNext()) {
            Node node = nodes1.next();
            System.out.println(node);
            //System.out.println(nodes1.hasNext()?1:2);
        }
        System.out.println();
        for (int i = 0; i < graph.getNodeCount(); i++) {
            Node node = graph.getNode(i);
            System.out.println(node);

        }

        int n = graph.getNodeCount();
        byte adjacencyMatrix[][] = new byte[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = (byte) (graph.getNode(i).hasEdgeBetween(j) ? 1 : 0);
            }
        }
        int i = graph.getNode("A").getIndex();

        System.out.println();
        System.out.println(i);

        String id = graph.getNode(0).getId();
        System.out.println();
        System.out.println(id);

        System.out.println();
        

    }

}
