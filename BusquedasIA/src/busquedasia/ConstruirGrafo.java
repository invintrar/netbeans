/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedasia;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Darwin
 */
public class ConstruirGrafo {

    private Graph grafo;

    public Graph getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph grafo) {
        this.grafo = grafo;
    }

    public Graph grafoAleatorio(int grado, int numeroNodos) {
        grafo = new SingleGraph("Random");
        Generator gen = new RandomGenerator(grado);//grado se refiere al numero de vertices conectados a cada grafo
        gen.addSink(grafo);
        gen.begin();
        for (int i = 0; i < numeroNodos; i++) {
            gen.nextEvents();
        }
        gen.end();
        grafo.display();
        
        return grafo;

    }//Fin metodo Aleatorio

    public void grafoBA(int grado, int numeroNodos) {
        grafo = new SingleGraph("BA");
        Generator crear = new BarabasiAlbertGenerator(grado);
        crear.addSink(grafo);
        crear.begin();

        for (int i = 0; i < numeroNodos; i++) {
            crear.nextEvents();
        }
        crear.end();
        grafo.display();
    }
}
