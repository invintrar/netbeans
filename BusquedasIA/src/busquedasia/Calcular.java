/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedasia;

import java.util.ArrayList;
import java.util.List;
import org.graphstream.graph.Graph;

/**
 *
 * @author Darwin
 */
public class Calcular {

    private double tiempo;
    private Busquedas busquedas;
    List listRutas = new ArrayList();
    int b, d, suma, numeroNodos;

    public Calcular(Graph grafo) {
        busquedas = new Busquedas(grafo);
    }

/////////////////////BUSQUEDA EN AMPLITUD /////////////////////////////////////
    public void amplitud() {

        busquedas.restablecer();
        busquedas.ingresarNodos();
        busquedas.agregarNivel();
        busquedas.mostrar();
        d = busquedas.getNivel();
        suma = busquedas.getFactorRamificacion();
        numeroNodos = busquedas.getNumeroNodos();
        b = suma / numeroNodos;
        int cTemporal = (int) (Math.pow(b, d));
        int cEspacial = (int) (b * d - d + 1);
        busquedas.restablecer();
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.amplitud();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "Amplitud" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "Amplitud" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "No";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t%s\t%s\n", "Algoritmo", "Tiempo(ns)", "Ruta", "Iteraciones", "Solucion", "C.Temporal", "C.Espacial");
        System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n", "Amplitud", tiempo, busquedas.getRuta(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }

/////////////////////BUSQUEDA EN PROFUNDIDAD////////////////////////////////////
    public void profundidad() {
        busquedas.restablecer();
        int cTemporal = (int) (Math.pow(b, d));
        int cEspacial = (int) (b * d);
        //busquedas.restablecer();
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.profundidad();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "Profundidad" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "Profundidad" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "NO";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n", "Profundidad", tiempo, busquedas.getRuta(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }

//////////////////BUSQUEDA PROFUNDIDAD ITERATIVA///////////////////////////////
    public void iterativa() {
        busquedas.restablecer();
        int cTemporal = (int) (Math.pow(b, d));
        int cEspacial = (int) (b * d);
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.iterativa();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "Iterativa" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "Iterativa" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "NO";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n", "Iterativa", tiempo, busquedas.getRuta(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }

////////////////BUSQEDAD BIDIRECCIONAL/////////////////////////////////////////
    public void bidireccional() {
        busquedas.restablecer();
        int cTemporal = (int) (Math.pow(b, d / 2));
        int cEspacial = (int) (2 * Math.pow(b, b / 2));
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.bidireccional();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "Bidireccional" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "Bidireccional" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "NO";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n", "Bidireccional", tiempo, busquedas.getRuta()+busquedas.getRutaDestino(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }

///////////////BUSQUEDA COSTOUNIFORME//////////////////////////////////////////    
    public void costoUniforme() {
        busquedas.restablecer();
        int cTemporal = (int) (Math.pow(b, d+1));
        int cEspacial = (int) (Math.pow(b, d+1));
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.costoUniforme();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "C.Uniforme" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "C.Uniforme" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "NO";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n", "C.Uniforme", tiempo, busquedas.getRuta(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }
    
    /////////////////////BUSQUEDA ASCENSO A LA COLINA//////////////////////////
    public void ascensoColina() {
        busquedas.restablecer();
        int cTemporal = (int) (d+1);
        int cEspacial = 1;
        //busquedas.restablecer();
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.ascenso();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "A.Colina" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "A.Colina" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "NO";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n", "A.Colina", tiempo, busquedas.getRuta(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }
    
    /////////////////////BUSQUEDA EN PRIMERO EL MEJOR////////////////////////////////////
    public void primeroMejor() {
        busquedas.restablecer();
        int cTemporal = (int) (Math.pow(b, d));
        int cEspacial = (int) (b * d);
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedas.primeroMejor();
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);
        String ruta;
        String solucion;
        if (busquedas.isEncontrado()) {
            ruta = "P.Mejor" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t SI" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "Si";
        } else {
            ruta = "P.Mejor" + "\t" + tiempo + "\t" + busquedas.getRuta() + "\t\t" + busquedas.getNumeroIteraciones() + "\t NO" + "\t" + cTemporal + "\t" + cEspacial;
            solucion = "NO";
        }
        listRutas.add(ruta);
        System.out.printf("%s\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n", "P.Mejor    ", tiempo, busquedas.getRuta(), busquedas.getNumeroIteraciones(), solucion, cTemporal, cEspacial);
    }
}
