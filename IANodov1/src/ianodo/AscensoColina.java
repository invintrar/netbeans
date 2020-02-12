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
public class AscensoColina implements Busqueda {

    private LinkedList<Nodo> pila = new LinkedList();
    private String camino = "";
    private boolean finalizar = false;
    int pos = 0;
    private int numIteraciones = 0;
    private boolean encontrado = false;
    private Nodo nodo;
    private String nodobuscado = "";
    Nodo origen = null;

    //Metodo constructor
    public AscensoColina(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
        this.nodobuscado = nodoABuscar;
    }

    public void busquedaAscenso(Nodo nodo, String nodobuscado) {
        numIteraciones++;
        camino = camino + nodo.nombre;
        if (nodo.getHeuristica() == 0) {
            finalizar = true;
            encontrado = true;
        }
        if (nodo.nodos != null & nodo.visitado == false & finalizar == false) {
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                System.out.println("nodo" + nodop.getNombre());
                if (nodop != null) {
                    pila.add(nodop);//Obtencion de los hijos del nodo
                }
            }
            pos = 0;
            for (int n = 0; n < pila.size() - 1; n++) {
                for (int j = 1; j < pila.size(); j++) {
                    if (pila.get(n).getHeuristica() > pila.get(j).getHeuristica()) {
                        pos = j;//Nodo con menor heuristica
                    }
                }
            }
            if (!pila.isEmpty()) {
                nodo.setVisitado(true);
                nodo = pila.get(pos);
                pila.clear();
                busquedaAscenso(nodo, nodobuscado);
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
        System.out.println("Hola Ascenso a la Colina");
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedaAscenso(nodo, nodobuscado);
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
