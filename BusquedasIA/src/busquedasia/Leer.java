/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedasia;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Darwin
 */
public class Leer {

    private Graph grafo = new SingleGraph("GrafoL");
    //private Graph grafo = new MultiGraph("GrafoL");

    public Graph getGrafo() {
        return grafo;
    }
    private static Scanner input;
    //Abrir  archivo.txt

    public void abrirArchivo() {
        try {
            input = new Scanner(Paths.get("C:\\Users\\Darwin\\Documents\\archivo.txt"));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }//fin metodo abrir archivo

    //read record from file
    public void leerArchivo() {
        try {
            while (input.hasNext()) {
                int nivel = 0;
                String linea = input.next();
                String[] valor = linea.split(",");
                String nodoAnterior = null;
                String nodoPadre = null;
                String bordeAnterior = null;
                String union = null;
                int contador = 0;
                //Recorre cada valor de la linea
                for (String nodo : valor) {
                    if (contador % 2 == 0) {//es para
                        if (grafo.getNode(nodo) == null && !buscarNodo(nodo)) {
                            grafo.addNode(nodo);
                            Node n = grafo.getNode(nodo);
                            n.addAttribute("visitado", false);
                            

                        }
                    }
                    if (contador == 0) {
                        nodoPadre = nodo;

                    }
                    if (contador == 1) {
                        Node n = grafo.getNode(nodoAnterior);
                        n.setAttribute("heuristica", Double.parseDouble(nodo));
                        double h = n.getAttribute("heuristica");
                        int nh = (int) h;
                        n.addAttribute("ui.label", n.getId()+":"+nh);

                    }

                    if (contador % 2 == 0 && contador > 1) {//es par
                        union = nodoPadre + nodo;
                        if (grafo.getEdge(union) == null) {
                            grafo.addEdge(union, nodoPadre, nodo);
                            Node nodoP = grafo.getNode(nodoPadre);
                            bordeAnterior = union;
                        }
                    } else {//impar
                        if (contador > 2) {
                            Edge borde = grafo.getEdge(bordeAnterior);
                            borde.addAttribute("peso", Double.parseDouble(nodo));
                            borde.addAttribute("ui.label", Double.parseDouble(nodo));
                        }
                    }
                    nodoAnterior = nodo;
                    contador++;
                }
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
        }
    }//End method readRecords

    public boolean buscarNodo(String nodoBuscar) {
        boolean existe = false;
        for (Node n : grafo.getEachNode()) {
            if (n.getId().equalsIgnoreCase(nodoBuscar)) {
                existe = true;
            }
        }
        return existe;
    }

    //close file and terminate aplication
    public void cerrarArchivo() {
        if (input != null) {
            input.close();
        }
    }//end closeFile

}
