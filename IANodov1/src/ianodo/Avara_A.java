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
public class Avara_A implements Busqueda {

    private Nodo nodo;
    String camino = "";
    private int numIteraciones = 0;
    private int pos = 0;
    boolean terminar = false;
    private LinkedList sumar = new LinkedList();
    private LinkedList caminos = new LinkedList();
    private LinkedList respuestas = new LinkedList();
    private LinkedList total = new LinkedList();
    private LinkedList totalidad = new LinkedList();
    List<Nodo> recorrer = new ArrayList();
    private int aux = 0;
    int tota = 0;
    int nivel = 0;
    int suma = 0;
    private LinkedList<Nodo> pila = new LinkedList();
    String cami = "";
    boolean encontrado = false;

    public Avara_A(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
    }

    private void recorrido(Nodo nodo) {
        if (numIteraciones == 0) {
            camino = camino + nodo.nombre;
        }
        numIteraciones++;
        if (nodo.getHeuristica() == 0) {
            encontrado = true;
        }
        if (nodo.nodos != null & nodo.isVisitado() == false & encontrado == false) {
            List sumarAux = new LinkedList();
            List<Nodo> pilaAux = new LinkedList();
            Iterator<Nodo> it = nodo.getNodos().iterator();
            Iterator<Arista> aristas = nodo.getAristas().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                Arista arista = aristas.next();
                if (arista != null) {
                    sumarAux.add(nodop.getHeuristica() + arista.getPeso());
                    total.add(suma + arista.getPeso());
                }
                if (nodop != null) {
                    pilaAux.add(nodop);

                }
            }
            nodo.setVisitado(true);

            //ORDENAMIENTO
            int salto, aux, i;
            boolean cambios;
            for (salto = sumarAux.size() / 2; salto != 0; salto /= 2) {
                cambios = true;
                while (cambios) { // Mientras se intercambie algún elemento
                    cambios = false;
                    for (i = salto; i < sumarAux.size(); i++) // se da una pasada
                    {
                        if ((int) sumarAux.get(i - salto) > (int) sumarAux.get(i)) { // y si están desordenados
                            //DE ACUERDO A LA MENOR CANTIDAD EN HEURISTICA Y PESO
                            aux = (int) sumarAux.get(i); // se reordenan
                            sumarAux.set(i, (int) sumarAux.get(i - salto));
                            sumarAux.set(i - salto, aux);
                            cambios = true; // y se marca como cambio.
                            //ORDENA LA COLA
                            Nodo auxiliar = pilaAux.get(i); // se reordenan
                            pilaAux.set(i, pilaAux.get(i - salto));
                            pilaAux.set(i - salto, auxiliar);
                        }
                    }
                }
            }
            pila.addAll(0, pilaAux);
            sumar.addAll(0, sumarAux);
            System.out.println("---");
            for (Iterator<Nodo> iterator = pila.iterator(); iterator.hasNext();) {
                Nodo next = iterator.next();
                System.out.println("nodo---" + next.getNombre());
            }

        }
        if (!pila.isEmpty()) {//Nodo con el menor f
            Nodo n = pila.removeFirst();
            suma = (int) sumar.getFirst();
            camino = camino + n.nombre;
            analisisPadre(nodo, n, suma);
            nivel++;
            recorrido(n);
            nivel--;
        }
    }

    public void analisisPadre(Nodo nodo, Nodo n, int suma) {
        if (n.getPadre().size() > 1) {
            for (Iterator<Nodo> iterator = n.getPadre().iterator(); iterator.hasNext();) {
                Nodo next = iterator.next();
                if (next != nodo) {
                    busquedaProfundidad(next);
                    for (int i = 0; i < recorrer.size(); i++) {
                        Nodo nget = recorrer.get(i);
                        System.out.println("Nodo" + nget.getNombre());
                    }
                }
            }
        }
    }

    public void busquedaProfundidad(Nodo nodo) {
        numIteraciones++;
        camino = camino + nodo.getNombre();
        recorrer.add(0, nodo);
        System.out.println("Nodo" + nodo.getNombre());
        if (nodo.nombre.equalsIgnoreCase(nodo.getNombre())) {
            encontrado = true;//System.out.println("Nodo Encontrado Camino: " + camino);
        }
        if (nodo.nodos != null & nodo.visitado == false & encontrado == false) {
            LinkedList<Nodo> aux = new LinkedList();
            Iterator<Nodo> it = nodo.getPadre().iterator();
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
        recorrido(nodo);
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if (encontrado) {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t SI";
        } else {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t NO";
        }
    }
}
