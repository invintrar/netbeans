/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplogs2;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Darwin
 */
public class EjemploGS2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph grafo = new SingleGraph("BA");
        Generator crear = new BarabasiAlbertGenerator(3);
        crear.addSink(grafo);
        crear.begin();

        for (int i = 0; i < 100; i++) {
            crear.nextEvents();

        }
        
        crear.end();
        char letra = 'a';
        int i = 0;
        for (Node n : grafo.getEachNode()) {
            System.out.println(n.getId());
            n.addAttribute("Nombre", letra);
            n.addAttribute("ui.label", i);
            letra++;
            i++;
        }
        
        System.out.println();
        Node n= grafo.getNode(0);
        //n.addAttribute("ui.label", "A");
        char value=n.getAttribute("Nombre");
        System.out.println(value);

        System.out.println();
        for (Edge e : grafo.getEachEdge()) {
            System.out.println(e.getId());
        }

        System.out.println(grafo.getNodeCount());
        System.out.println();
        grafo.display();
        
        String nombre=null;
        String apellido="zhagnay";
        System.out.println(nombre+apellido);
    }

}
