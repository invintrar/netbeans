/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ianodo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Darwin
 */
public class Profundidad implements Busqueda {

    private LinkedList<Nodo> pila = new LinkedList();
    private String camino = "";
    private int numIteraciones = 0;
    private Nodo nodo;
    private boolean encontrado = false;
    private String nodobuscado = "";

    public Profundidad(Nodo nodo, String nodoBuscar) {
        this.nodo = nodo;
        this.nodobuscado = nodoBuscar;
    }

    public void busquedaProfundidad(Nodo nodo) {
        numIteraciones++;
        camino = camino + nodo.getNombre();
        System.out.println("Nodo" + nodo.getNombre());
        if (nodo.nombre.equalsIgnoreCase(nodobuscado)) {
            encontrado = true;//System.out.println("Nodo Encontrado Camino: " + camino);
        }
        if (nodo.nodos != null & nodo.visitado == false & encontrado == false) {
            LinkedList<Nodo> aux = new LinkedList();
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    aux.add(nodop);//Obtencion de los hijos del nodo
                }
            }
            pila.addAll(0, aux);//Agrega los hijos al principio en la pila
            nodo.setVisitado(true);
        }
        if (!pila.isEmpty() & encontrado == false) {
            Nodo n = pila.getFirst(); //Obtencion del primer nodo
            pila.removeFirst();//Eliminacion del primer nodo
            busquedaProfundidad(n);//Se repite el proceso para el nodo eliminado
        }
    }

    @Override
    public String busqueda(Nodo nodo, String buscado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String calcularBusqueda() {
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedaProfundidad(nodo);
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if (encontrado) {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t SI";
        } else {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t NO";
        }
    }
}
