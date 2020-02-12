/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ianodo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Darwin
 */
public class AEstrella implements Busqueda {

    private Nodo nodo;
    String camino = "";
    private int numIteraciones = 0;
    private int pos = 0;
    boolean terminar = false;
    private String nodoABuscar;
    private List<Nodo> nodosPorRecorrer = new ArrayList<Nodo>();
    private List<Nodo> nodosAux = new ArrayList<Nodo>();
    private List<Nodo> nodoPadre = new ArrayList<Nodo>();
    private List sumar = new LinkedList();
    private List caminos = new LinkedList();
    private int aux = 0;
    int contador = 0;
    int suma = 0;
    String caminoPadre = "";

    public AEstrella(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
        this.nodoABuscar = nodoABuscar;
    }

    public AEstrella() {
    }

    private void recorrido(Nodo nodo) {
        numIteraciones++;
        camino = camino + nodo.getNombre();
        if (nodo.getHeuristica() == 0) {
            terminar = true;
        }
        //Analiza los nodos Padres
        if (nodo.getPadre().size() > 1 & nodoPadre != null) {
            for (Iterator<Nodo> iterator = nodoPadre.iterator(); iterator.hasNext();) {
                Nodo next = iterator.next();
                Iterator<Nodo> hijosIteratr = next.getNodos().iterator();
                Iterator<Arista> aristahijosIterator = next.getAristas().iterator();
                while (hijosIteratr.hasNext()) {//Agrega las Aristas de los hijos del nodo a la lista
                    Nodo nodoa = (Nodo) hijosIteratr.next();
                    Arista elemento = (Arista) aristahijosIterator.next();
                    if (nodoa.getNombre().equalsIgnoreCase(nodo.getNombre())) {
                        int sumaAux = (nodoa.getHeuristica() + elemento.getPeso());
                        if (sumaAux < suma) {
                            camino = caminoPadre + next.getNombre() + nodoa.getNombre();
                        }
                    }
                }
            }
        }

        Iterator<Nodo> nombreIterator = nodo.getNodos().iterator();
        Iterator<Arista> aristaIterator = nodo.getAristas().iterator();

        //Agrega las Aristas de los hijos del nodo a la lista
        while (nombreIterator.hasNext()) {
            Nodo nodoa = (Nodo) nombreIterator.next();
            Arista elemento = (Arista) aristaIterator.next();
            nodosPorRecorrer.add(nodoa);
            sumar.add(nodoa.getHeuristica() + elemento.getPeso());
            caminos.add(camino);
            if (contador == 0) {
                nodoPadre.add(nodoa);
                caminoPadre = nodo.getNombre();
            }
        }
        contador = contador + 1;

        pos = 0;
        for (int n = 0; n < sumar.size() - 1; n++) {
            for (int j = 1; j < sumar.size(); j++) {
                if ((int) sumar.get(n) > (int) sumar.get(j)) {
                    pos = j;//Nodo con menor funcion 
                }
            }
        }
        if (!sumar.isEmpty() & terminar == false) {
            nodo.setVisitado(true);
            Nodo nodoaux = nodosPorRecorrer.get(pos);
            nodosPorRecorrer.remove(pos);
            if (contador == 1) {
                nodoPadre.remove(pos);
                for (Iterator<Nodo> iterator = nodoPadre.iterator(); iterator.hasNext();) {
                    Nodo next = iterator.next();
                    System.out.println("nodopadre" + next.getNombre());

                }
            }
            nodosPorRecorrer.clear();
            suma = (int) sumar.get(pos);
            sumar.remove(pos);
            caminos.remove(pos);
            sumar.clear();

            //System.out.println("AESTRELLA" + nodo.getNombre());
            if (nodo.getPadre().size() > 1 & nodoPadre != null) {

                recorrido(nodoaux);
            } else {
                recorrido(nodoaux);
            }
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
        recorrido(nodo);
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if (terminar) {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t SI";
        } else {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t NO";
        }
    }
}
