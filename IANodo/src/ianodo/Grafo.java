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
import java.util.Scanner;

/**
 *
 * @author Vero
 */
public class Grafo {
static List<Nodo> listaNodos = new ArrayList();
    static int nivel = 0;
    private static LinkedList<Nodo> cola = new LinkedList();

    private Busqueda busqueda;
    List<Nodo> listagrafo = new ArrayList();
    static Nodo nodoraiz;
    public void listaNodos(List<Nodo> lista){
        nodoraiz=lista.get(0);
        this.listaNodos=lista;
    }
    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }

    public List<Nodo> getListagrafo() {
        return listagrafo;
    }
    public void nuevos(String a, String b ){
        for (Iterator<Nodo> iterator = listaNodos.iterator(); iterator.hasNext();) {
            Nodo next = iterator.next();
            if(next.getNombre().equalsIgnoreCase(a)){
        nodoraiz=next;
            }
        }
        

    }
    public void setListagrafo(List<Nodo> listagrafo) {
        this.listagrafo = listagrafo;
    }

    public Nodo datos() {
        //----------------------DATOS PARA LAS BUSQUEDAS: AMPLITUD, PROFUNDIDAD, ITERATIVA, BIDIRECCIONAL
        Nodo nodoraiz = new Nodo("A");
        this.nodoraiz = nodoraiz;
        Nodo nodo = new Nodo("D");
        Nodo nodo1 = new Nodo("F");
        Nodo nodo2 = new Nodo("G");
        Nodo nodo3 = new Nodo("J");
        Nodo nodo4 = new Nodo("H");
        Nodo nodo5 = new Nodo("C");
        Nodo nodo6 = new Nodo("E");
        Nodo nodo7 = new Nodo("K");
        Nodo nodo8 = new Nodo("B");
        Nodo nodo9 = new Nodo("Z");
        Nodo nodo10 = new Nodo("W");
        Nodo nodo11 = new Nodo("L");
        nodoraiz.getNodos().add(nodo);
        nodoraiz.getNodos().add(nodo1);
        nodoraiz.getNodos().add(nodo2);
        nodoraiz.getAristas().add(new Arista("D", 4));
        nodoraiz.getAristas().add(new Arista("F", 5));
        nodoraiz.getAristas().add(new Arista("G", 6));
        nodo.getPadre().add(nodoraiz);
        nodo1.getPadre().add(nodoraiz);
        nodo2.getPadre().add(nodoraiz);
        nodoraiz.setHeuristica(10);

        nodo.getNodos().add(nodo3);
        nodo.getNodos().add(nodo4);
        nodo.getAristas().add(new Arista("J", 4));
        nodo.getAristas().add(new Arista("H", 3));
        nodo4.getPadre().add(nodo);
        nodo3.getPadre().add(nodo);
        nodo.setHeuristica(10);

        nodo1.getNodos().add(nodo5);
        nodo1.getNodos().add(nodo6);
        nodo1.getAristas().add(new Arista("C", 2));
        nodo1.getAristas().add(new Arista("E", 3));
        nodo5.getPadre().add(nodo1);
        nodo6.getPadre().add(nodo1);
        nodo1.setHeuristica(20);

        nodo2.setHeuristica(30);

        nodo3.getNodos().add(nodo7);
        nodo3.getAristas().add(new Arista("K", 6));
        nodo7.getPadre().add(nodo3);
        nodo3.setHeuristica(15);

        nodo7.getNodos().add(nodo11);
        nodo7.getAristas().add(new Arista("L", 4));
        nodo11.getPadre().add(nodo7);
        nodo7.setHeuristica(6);

        nodo4.getNodos().add(nodo8);
        nodo4.getAristas().add(new Arista("B", 2));
        nodo8.getPadre().add(nodo4);
        nodo4.setHeuristica(4);

        nodo6.getNodos().add(nodo9);
        nodo6.getNodos().add(nodo10);
        nodo6.getAristas().add(new Arista("Z", 2));
        nodo6.getAristas().add(new Arista("W", 3));
        nodo9.getPadre().add(nodo6);
        nodo10.getPadre().add(nodo6);
        nodo6.setHeuristica(5);

        nodo5.setHeuristica(10);
        nodo8.setHeuristica(4);
        nodo9.setHeuristica(6);
        nodo10.setHeuristica(0);
        nodo11.setHeuristica(3);
        return nodoraiz;
    }

    
    public Nodo generardatos(int numhijos) {
        int n = 0;
        for (int i = 0; i <= numhijos; i++) {
            if (i < 27) {
                Nodo a = new Nodo(Character.toString((char) (i + 65)));
                int heuristica = (int) (Math.random() * 10);
                a.setHeuristica(heuristica);
                listaNodos.add(a);
            } else {
                n++;
                Nodo a = new Nodo(Character.toString((char) (n + 65)) + "" + n);
                int heuristica = (int) (Math.random() * 10);
                a.setHeuristica(heuristica);
                listaNodos.add(a);
            }
        }
        nodoraiz = listaNodos.get(0);
        aleatorio(listaNodos.get(0), numhijos);
        return listaNodos.get(0);
    }
    int count = 0;

    public void volverEstado() {
        for (Iterator<Nodo> iterator = listaNodos.iterator(); iterator.hasNext();) {
            Nodo next = iterator.next();
            next.setVisitado(false);

        }
    }

    public Nodo getNodoraiz() {
        return nodoraiz;
    }

    public void setNodoraiz(Nodo nodoraiz) {
        this.nodoraiz = nodoraiz;
    }

    public void aleatorio(Nodo nodo, int numhijos) {
        int hijos;
        System.out.println("Nodo" + nodo.getNombre());
        if (count < numhijos) {
            hijos = (int) (Math.random() * 3) + 2;
            if (hijos > 0) {
                for (int i = 1; i < hijos; i++) {
                    count++;
                    if (count < numhijos) {
                        {
                            nodo.getNodos().add(listaNodos.get(count));
                            listaNodos.get(count).getPadre().add(nodo);
                            int peso = (int) (Math.random() * 6);
                            nodo.getAristas().add(new Arista(listaNodos.get(count).getNombre(), peso));

                        }
                    }
                }
            }
        }
        if (nodo.getNodos() != null) {
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    aleatorio(nodop, numhijos);
                }
            }
        }
    }

    String datos = "";

    public String mostrarGrafo(Nodo nodo, int nivel) {
        if (nodo != null) {
            datos = datos + "Nivel " + nivel + ": " + nodo.getNombre() + "(" + nodo.getHeuristica() + ")" + "\n";
            if (nodo.nodos != null & nodo.visitado == false) {//Para saber que no es  una hoja
                Iterator<Nodo> it = nodo.getNodos().iterator();
                while (it.hasNext()) {
                    Nodo nodop = it.next();
                    nivel++;
                    mostrarGrafo(nodop, nivel);
                    nivel--;
                }
            }
        }
        return datos;
    }
}
