/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    String nombre;
    List<Nodo> nodos;//hijos
    List<Nodo> padre;//padre
    List<Arista> aristas;//hijos
    List<Arista> aristasPadres;//padres
    boolean visitado;
    boolean buscado;
    int heuristica;

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public List<Nodo> getPadre() {
        return padre;
    }

    public void setPadre(List<Nodo> padre) {
        this.padre = padre;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }

    public Nodo(List<Nodo> nodos, List<Arista> aristas) {
        this.nodos = nodos;
        this.aristas = aristas;
    }

    public Nodo(String nombre, List<Nodo> nodos, List<Nodo> padre, List<Arista> aristas) {
        this.nombre = nombre;
        this.nodos = nodos;
        this.padre = padre;
        this.aristas = aristas;
    }

    public Nodo(String nombre, List<Nodo> nodos) {
        this.nombre = nombre;
        this.nodos = nodos;
        this.visitado=false;
        this.buscado=false;
    }

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.visitado=false;
        this.buscado=false;
        nodos=new ArrayList();
        padre=new ArrayList(); 
        aristas=new ArrayList();
    }

    public boolean isBuscado() {
        return buscado;
    }

    public void setBuscado(boolean buscado) {
        this.buscado = buscado;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }
}