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
public class PrimeroelMejor implements Busqueda {

    private LinkedList<Nodo> pila = new LinkedList();
    private String camino = "";
    private boolean finalizar = false;
    int pos = 0;
    private int numIteraciones = 0;
    private boolean encontrado = false;
    private Nodo nodo;
    private String nodobuscado = "";
    Nodo origen = null;

    public PrimeroelMejor(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
        this.nodobuscado = nodoABuscar;
    }

    public void busquedaPrimero(Nodo nodo, String nodobuscado) {
        numIteraciones++;
        if (pos == 0) {
            pila.add(nodo);
        }
        if (nodo.getHeuristica() == 0) {
            encontrado = true;
        }
        if (nodo.visitado == false) {
            camino = camino + nodo.nombre;
        }
        if (nodo.nodos != null & nodo.visitado == false & encontrado == false) {
            nodo.visitado = true;
            LinkedList<Nodo> aux = new LinkedList();//Los hijos del nodo
            Iterator<Nodo> it = nodo.getNodos().iterator();

            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    aux.add(nodop);//Obtencion de los hijos del nodo
                }
            }
            int posMenor = 0;
            for (int j = 1; j < aux.size(); j++) {
                if (aux.get(0).getHeuristica() > aux.get(j).getHeuristica()) {
                    posMenor = j;//Nodo con menor heuristica de los nodos hijos
                }
            }
            Nodo menorPadre = null;
            if (!aux.isEmpty()) {
                menorPadre = aux.get(posMenor);
            }

            pos = pila.indexOf(nodo);//Me devuelve la posicion del nodo en la pila

            if (!pila.isEmpty()) {
                pila.remove(pos);
                if (pila.size() < pos) {
                    pila.addAll(aux);
                } else {
                    pila.addAll(pos, aux);//Agrega los hijos en la posicion del padre
                }
                if (menorPadre != null) {
                    pos = pila.indexOf(menorPadre);
                    busquedaPrimero(pila.get(pos), nodobuscado);
                }
            }
        }
    }

    public Nodo buscarorigen(Nodo nodo, String nodobuscado) {
        if (nodo.nombre.equalsIgnoreCase(nodobuscado)) {
            origen = nodo;
            return nodo;
        }
        if (nodo.nodos != null & nodo.buscado == false) {
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    buscarorigen(nodop, nodobuscado);

                }
            }
            nodo.setBuscado(true);
        }
        return origen;
    }

    @Override
    public String calcularBusqueda() {
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedaPrimero(nodo, nodobuscado);
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if (encontrado) {
            return tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t SI";
        } else {
            return tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t NO";
        }
    }

    @Override
    public String busqueda(Nodo nodo, String buscado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
