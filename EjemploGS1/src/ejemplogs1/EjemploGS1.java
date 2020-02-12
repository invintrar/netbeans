/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplogs1;

import java.util.Iterator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 *
 * @author Darwin
 */
public class EjemploGS1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 6; i++) {
            gen.nextEvents();
        }
        gen.end();
        graph.display();
        for (Node n : graph) {
            n.setAttribute("visitado", false);
            n.setAttribute("peso", 12);
            n.addAttribute("ui.label", n);
        }

        Node nodoCuatro = graph.getNode(4);

        boolean visitado = nodoCuatro.getAttribute("visitado");

        if (!visitado) {
            System.out.println("falso");
        }
        //nodoCuatro.setAttribute("visitado", true);
        System.out.println("peso"+nodoCuatro.getAttribute("peso"));
        nodoCuatro.addAttribute("visitado", true);
        nodoCuatro.addAttribute("peso", 13);
        System.out.println("peso"+nodoCuatro.getAttribute("peso"));
        visitado = nodoCuatro.getAttribute("visitado");
        
        if (visitado) {
            System.out.println("verdad");
        }

        //System.out.println(nodoCuatro.getAttributeKeySet());
        System.out.println(" Numero de vertices conectados:" + nodoCuatro.getDegree());
        //System.out.println(nodoCuatro.getClass());
        //System.out.println(nodoCuatro.getEachAttributeKey());

        //System.out.println(nodoCuatro.hasEdgeToward(nodoCuatro));  
        Iterator<Node> it = nodoCuatro.getNeighborNodeIterator();
        while (it.hasNext()) {
            Node padre = it.next();
            System.out.println(padre.getId());
        }
        //String valor = "A";
        int d=3;
        int b=2;
        double cT=Math.pow(b, d);
        int cE=(b*d-d+1);
        int division=d/b;
        //aleatorio =(int) (Math.random() * 100);
        System.out.println();
        System.out.println(cT);
        System.out.println(cE);
        System.out.println(division);
        
    }

}
