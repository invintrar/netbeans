/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

/**
 *
 * @author Darwin
 */
public class Algoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo grafo=new Grafo();
        grafo.datos();
        Metodos met=new Metodos();
        //List<Nodo> nodos=met.cargarGrafo();
        System.out.println("Busqueda");
        met.datoiniciofin("A", "B");
        System.out.printf(met.busquedaTotal());
    }
    
}
