/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedasia;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 *
 * @author Darwin
 */
public class BusquedasIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        boolean control = true;
        Scanner input = new Scanner(System.in);
        ConstruirGrafo construirGrafo = new ConstruirGrafo();
        Graph grafo = null;
        Leer leer = new Leer();

        int cas1 = 0;
        int cas2 = 0;
        while (control) {
            try {
                System.out.printf("%n%s%n"
                        + "%36s%n"
                        + "%25s%n"
                        + "%19s%n"
                        + "%18s%n",
                        "Opciones:",
                        "1. Generar Grafo Aleatorio",
                        "2. Cargar Grafo",
                        "3. Buscar",
                        "4. Salir");
                opcion = input.nextInt();

                switch (opcion) {
                    //Generar Grafo aleatorio
                    case 1:
                        cas1 = 1;
                        System.out.println("Ingres numero de Nodos:");
                        int numeroNodos = input.nextInt();
                        System.out.println("Ingrese el Grado promedio para los nodos:");
                        int numeroGrados = input.nextInt();
                        grafo = construirGrafo.grafoAleatorio(numeroGrados, numeroNodos);
                        int i = 0;
                        for (Node n : grafo.getEachNode()) {
                            int heuristica = (int) (Math.random() * 100);
                            n.addAttribute("heuristica", heuristica);
                            n.addAttribute("ui.label", i+":"+heuristica);
                            i++;
                        }
                        for(Edge e:grafo.getEachEdge()){
                            int peso=(int)(Math.random()*100);
                            e.addAttribute("peso", peso);
                            e.addAttribute("ui.label", peso);
                        }
                        break;
                    //Cargar grafo de archivo de texto
                    case 2:
                        cas2 = 2;
                        leer.abrirArchivo();
                        leer.leerArchivo();
                        leer.cerrarArchivo();
                        grafo = leer.getGrafo();               
                        grafo.display();
                        break;
                    //Buscar 
                    case 3:
                        if (cas1 == 1 || cas2 == 2) {
                            Calcular calcular = new Calcular(grafo);
                            //System.out.printf("\t%s\t%s\t\t%s\t%s\t\t%s\t%s","Tiempo(ns)","Ruta","Numero Iteraciones","Solucion","C.Temporal","C.Espacial" );
                            calcular.amplitud();
                            calcular.profundidad();
                            calcular.iterativa();
                            calcular.bidireccional();
                            calcular.costoUniforme();
                            calcular.ascensoColina();
                            calcular.primeroMejor();
                            break;
                        }
                        System.out.println("Debe Generar o Crear un grafo primero.\n");
                        break;
                    //Salir
                    case 4:
                        control = false;
                        break;
                    default:
                        System.out.println("Ingrese una Opcion");

                }
            } catch (InputMismatchException e) {
                System.out.printf("Exception: %s%n%n", e.getMessage());
                input.nextLine();
                System.out.println("Debe introducir enteros. Intente de nuevo.\n");
            }
        }

    }

}
