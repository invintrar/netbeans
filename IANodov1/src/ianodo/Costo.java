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
public class Costo implements Busqueda {

    private LinkedList<Nodo> cola = new LinkedList();
    private LinkedList sumar = new LinkedList();
    private LinkedList caminos = new LinkedList();
    private int numIteraciones = 0;
    private boolean mayor = true;
    private Nodo nodo;
    int costo = 0;
    int i = 0;
    private String camino = "";
    private String nodobuscado = "";
    private boolean encontrado = false;
    private int nivelProfundidad = 0;

    public Costo(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
        this.nodobuscado = nodoABuscar;
    }

    public void mostrar(Nodo nodo, String nodobuscado, int suma, String cami) {
        if (numIteraciones == 0) {
            cami = nodo.nombre;
            camino = cami;
        }
        numIteraciones++;
        if (nodo.nombre.equalsIgnoreCase(nodobuscado)) {
            encontrado = true;
        }
        if (nodo.nodos != null & encontrado == false) {
            Iterator<Nodo> it = nodo.getNodos().iterator();
            Iterator<Arista> aristas = nodo.getAristas().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                Arista arista = aristas.next();
                if (arista != null) {
                    sumar.add(arista.getPeso() + suma);
                }
                if (nodop != null) {
                    cola.add(nodop);
                    caminos.add(cami + nodop.getNombre());
                }
            }
            int pos = 0;
            int posmen = 0;

            if (nivelProfundidad > 0) {
                if (nivelProfundidad > 2)//Para poder comparar cual es el mayor en la suma y eliminar o si no los datos son comparados de manera erronea
                {
                    for (int n = 0; n < sumar.size(); n++) {
                        for (int j = 1; j < sumar.size(); j++) {
                            if ((int) sumar.get(n) > (int) sumar.get(j)) {
                                pos = n;//Para eliminar el nodo con mayor peso que existe

                            }
                        }
                    }
                    if (!cola.isEmpty()) {
                        Nodo n = cola.get(pos);
                        if (n.getNombre().equalsIgnoreCase(nodobuscado)) {
                            suma = (int) sumar.get(pos);
                            cami = (String) caminos.get(pos);
                            camino = cami;
                            costo = suma;
                            encontrado = true;
                        }
                        cola.remove(pos);
                        sumar.remove(pos);
                        caminos.remove(pos);
                    }
                }
                i = i + 1;
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
            nodo.setVisitado(true);
        }

        if (!cola.isEmpty() & encontrado == false) {
            Nodo n = cola.getFirst();
            suma = (int) sumar.getFirst();
            cami = (String) caminos.getFirst();
            camino = cami;
            costo = suma;
            cola.removeFirst();
            sumar.removeFirst();
            caminos.removeFirst();
            nivelProfundidad++;
            mostrar(n, nodobuscado, suma, cami);
            nivelProfundidad--;

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
        mostrar(nodo, nodobuscado, 0, "");
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if (encontrado) {
            return tiempo + "\t" + this.camino + "(" + this.costo + ")" + "\t\t" + this.numIteraciones + "\t SI";
        } else {
            return tiempo + "\t" + this.camino + "(" + this.costo + ")" + "\t\t" + this.numIteraciones + "\t NO";
        }
    }

}
