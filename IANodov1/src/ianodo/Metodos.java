/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ianodo;

import dao.leer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin
 */
public class Metodos {

    int niv = 2;//Nivel de Busqueda Profundidad
    List ltiempo = new ArrayList();
    List lcamino = new ArrayList();
    List complejidadTemporal=new ArrayList();
    List complejidadEspacial=new ArrayList();
    
    Grafo grafo;
    Nodo nodoraiz;
    String nodobuscar;

    public String busquedaTotal() {
        Amplitud amplitud = new Amplitud();
//---------------------BUSQUEDA EN AMPLITUD-------------------------
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaAmplitud = new Amplitud(nodoraiz, nodobuscar);
        Context elContext = new Context(busquedaAmplitud);
        String resultadoAmplitud = elContext.some_method();
        lcamino.add(resultadoAmplitud);
        complejidadTemporal.add(1);
        grafo.volverEstado();
//---------------------BUSQUEDA EN PROFUNDIDAD-------------------
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaProfundidad = new Profundidad(nodoraiz, nodobuscar);
        elContext = new Context(busquedaProfundidad);
        String resultadoProfundidad = elContext.some_method();
        lcamino.add(resultadoProfundidad);
        grafo.volverEstado();
//----------------------BUSQUEDA ITERATIVA----------------------
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaIterativa = new Iterativa(nodoraiz, nodobuscar);
        elContext = new Context(busquedaIterativa);
        String resultadoIterativa = elContext.some_method();
        lcamino.add(resultadoIterativa);
        grafo.volverEstado();
//--------------BUSQUEDA BIDIRECCIONAL----------    
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Nodo nodohoja = amplitud.buscarorigen(nodoraiz, "L");
        Busqueda busquedaBidireccional = new Bidireccional(nodoraiz, nodohoja);
        elContext = new Context(busquedaBidireccional);
        String resultadoBidireccional = elContext.some_method();
        lcamino.add(resultadoBidireccional);
        grafo.volverEstado();
//--------------BUSQUEDA DE COSTO UNIFORME----------    
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaCosto = new Costo(nodoraiz, nodobuscar);
        elContext = new Context(busquedaCosto);
        String resultadoCosto = elContext.some_method();
        lcamino.add(resultadoCosto);
        grafo.volverEstado();
//--------------BUSQUEDA ASCENSO A LA COLINA----------    
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaAscenso = new AscensoColina(nodoraiz, nodobuscar);
        elContext = new Context(busquedaAscenso);
        String resultadoAscenso = elContext.some_method();
        lcamino.add(resultadoAscenso);
        grafo.volverEstado();
//--------------BUSQUEDA DE PRIMERO EL MEJOR----------    
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaPrimero = new PrimeroelMejor(nodoraiz, nodobuscar);
        elContext = new Context(busquedaPrimero);
        String resultadoPrimero = elContext.some_method();
        lcamino.add(resultadoPrimero);
        grafo.volverEstado();
//--------------BUSQUEDA DE A*----------    
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaAEstrella = new AEstrella(nodoraiz, nodobuscar);
        elContext = new Context(busquedaAEstrella);
        String resultadoAEstrella = elContext.some_method();
        lcamino.add(resultadoAEstrella);
        grafo.volverEstado();
//--------------BUSQUEDA AVARA----------    
        grafo = new Grafo();
        nodoraiz = grafo.getNodoraiz();
        Busqueda busquedaAvara = new Avara(nodoraiz, nodobuscar);
        elContext = new Context(busquedaAvara);
        String resultadoAvara = elContext.some_method();
        lcamino.add(resultadoAvara);
        grafo.volverEstado();
//---------------------------MOSTRAR-----------------------
        return ("\n" + "Algoritmo \t\t Tiempo(ns)  \t Camino \t Num. de Iteraciones \t Solucion \tComplejidad"
                + "\nAmplitud \t" + lcamino.get(0)+"\t"+complejidadTemporal.get(0)
                + "\nProfundidad \t" + lcamino.get(1) + "\nIterativa \t" + lcamino.get(2)
                + "\nBidireccional \t" + lcamino.get(3)
                + "\nCosto Uniforme \t" + lcamino.get(4)
                + "\nAscenso a la Colina\t" + lcamino.get(5)
                + "\nPrimero el Mejor \t" + lcamino.get(6) + "\nA* \t" + lcamino.get(7)
                + "\nAvara \t" + lcamino.get(8));

    }

    public void datoiniciofin(String a, String b) {
        System.out.println("" + a + "b" + b);
        grafo = new Grafo();
        grafo.nuevos(a, b);
        nodobuscar = b;

    }

    public List<Nodo> cargarGrafo() {
        leer lee = new leer();
        List<Nodo> nodos = lee.obtenerNodos();
        return nodos;
    }
}
