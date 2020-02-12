/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ianodo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Vero
 */
public class Iterativa implements Busqueda {

    private int numeroDeNivel=2;
    private List<Nodo> nodosRecorridos = new ArrayList<>(); 
    private String camino = "";
    private String nodoABuscar;
    private int nivelProfundidad = 0;
    private boolean pararAlgoritmo = false;
    private int numIteraciones = 0;

    private Nodo nodo;

    public Iterativa(Nodo nodo, String nodoBuscar) {
        this.nodo = nodo;
        this.nodoABuscar = nodoBuscar;
    }

    public String getNodoABuscar() {
        return nodoABuscar;
    }

    public void setNodoABuscar(String nodoABuscar) {
        this.nodoABuscar = nodoABuscar;
    }

    public int getNumeroDeNivel() {
        return numeroDeNivel;
    }

    public void setNumeroDeNivel(int numeroDeNivel) {
        this.numeroDeNivel = numeroDeNivel;
    }

    public void busqueda(Nodo nodo) {
        int aux = numeroDeNivel;
        int n = 0;
        numeroDeNivel = 1;
        while (numeroDeNivel <= aux & this.pararAlgoritmo == false) {
            recorrido(nodo);
            numeroDeNivel = numeroDeNivel + 1;
            camino = camino + "-";
            volverEstado();
        }
    }
    public void volverEstado(){
        for (Iterator<Nodo> iterator = nodosRecorridos.iterator(); iterator.hasNext();) {
            Nodo next = iterator.next();
            next.setVisitado(false);
            
        }
    }
    public String aux = "";

    public void recorrido(Nodo nodo) {
        numIteraciones++;
        camino = camino + nodo.getNombre();
        nodosRecorridos.add(nodo);
        nodo.setVisitado(true);
        if (nodo.getNombre().equals(nodoABuscar)) {
            this.pararAlgoritmo = true;
            aux = nivelProfundidad + "";
        } else if (nodo != null && nivelProfundidad != numeroDeNivel) {
            Iterator<Nodo> nombreIterator = nodo.getNodos().iterator();
            while (nombreIterator.hasNext()) {
                Nodo elemento = (Nodo) nombreIterator.next();
                if (nivelProfundidad <= numeroDeNivel && this.pararAlgoritmo == false) { //Verifica si el nodo fue visitado y si el nodo aun no es encontrado
                    nivelProfundidad++;//Aumenta un nivel de profundidad
                    recorrido(elemento);
                    nivelProfundidad--;//Disminuye un nivel de profundidad
                }
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
        busqueda(nodo);
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if (pararAlgoritmo) {
            return "\t" +tiempo+"\t"+this.camino + "\t" + this.numIteraciones + "\t SI";
        } else {
            return "\t" +tiempo+"\t"+this.camino + "\t" + this.numIteraciones + "\t NO";
        }
    }
}
