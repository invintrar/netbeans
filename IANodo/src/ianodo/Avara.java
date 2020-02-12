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
 * @author Vero
 */
public class Avara implements Busqueda {

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
    private int aux = 0;

    public Avara(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
    }
    int tota = 0;
    private LinkedList<Nodo> cola = new LinkedList();
    int suma = 0;
    String cami = "";
    boolean encontrado = false;

    private void recorrido(Nodo nodo) {
        if(numIteraciones==0){
            cami=nodo.getNombre();
        }
            numIteraciones++;
        if (nodo.getHeuristica() == 0) {
            encontrado = true;
            respuestas.add(camino);
            totalidad.add(suma);
        }
        if (nodo.nodos != null & nodo.isVisitado()==false) {
            List sumarAux = new LinkedList();
            Iterator<Nodo> it = nodo.getNodos().iterator();
            Iterator<Arista> aristas = nodo.getAristas().iterator();
            int contador=0;
            while (it.hasNext()) {
                Nodo nodop = it.next();
                Arista arista = aristas.next();
                if (arista != null) {
                    System.out.println("------------"+(nodop.getHeuristica() + arista.getPeso()));
                    sumar.add(nodop.getHeuristica() + arista.getPeso());
                    sumarAux.add(nodop.getHeuristica() + arista.getPeso());
                    total.add(suma + arista.getPeso());
                }
                if (nodop != null) {
                    cola.add(nodop);
                    //cami = cami + nodop.nombre;
                    caminos.add(cami+ nodop.nombre);
                System.out.println("-----"+cami);
                }
                contador++;
            }
            nodo.setVisitado(true);
            
            //ORDENAMIENTO
            int salto, aux, i;
            boolean cambios;
            for (salto = sumar.size() / 2; salto != 0; salto /= 2) {
                cambios = true;
                while (cambios) { // Mientras se intercambie algún elemento
                    cambios = false;
                    for (i = salto; i < sumar.size(); i++) // se da una pasada
                    {
                        if ((int) sumar.get(i - salto) > (int) sumar.get(i)) { // y si están desordenados
                            //DE ACUERDO A LA MENOR CANTIDAD EN HEURISTICA Y PESO
                            aux = (int) sumar.get(i); // se reordenan
                            sumar.set(i, (int) sumar.get(i - salto));
                            sumar.set(i - salto, aux);
                            cambios = true; // y se marca como cambio.
                            //ORDENA LA COLA
                            Nodo auxiliar = cola.get(i); // se reordenan
                            cola.set(i, cola.get(i - salto));
                            cola.set(i - salto, auxiliar);
                            //ORDENA LOS CAMINOS
                            String auxi = (String) caminos.get(i); // se reordenan
                            caminos.set(i, (String) caminos.get(i - salto));
                            caminos.set(i - salto, auxi);
                        }
                    }
                }
            }
        }
        if (!cola.isEmpty()) {//Nodo con el menor f
            Nodo n = cola.getFirst();
            suma = (int) total.getFirst();
            cami = (String) caminos.getFirst();
            camino = cami;
            cola.removeFirst();
            sumar.removeFirst();
            caminos.removeFirst();
            recorrido(n);
        }
        else 
            //Es para el final saber que camino es el mas barato de acuerdo al nodo padre o el que se sigio
            if (cola.isEmpty()) {
            pos=0;
                for (int j = 1; j < totalidad.size(); j++) {
                    if ((int) totalidad.get(0) > (int) totalidad.get(j)) {
                        pos = j;
                    }
                }
            
            for (Iterator<String> iterator = respuestas.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                System.out.println("Reespuestas" + next);
            }
            if(respuestas.size()!=0){
            camino = (String) respuestas.get(pos);
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
        if (encontrado) {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t SI";
        } else {
            return "\t" + tiempo + "\t" + this.camino + "\t\t" + this.numIteraciones + "\t NO";
        }
    }
}
