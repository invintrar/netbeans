/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Darwin
 */
public class Amplitud implements Busqueda {

    private LinkedList<Nodo> cola = new LinkedList();
    private String camino = "";
    private Nodo nodo;
    private String nodobuscado = "";
    private int numIteraciones = 0;
    private boolean encontrado = false;
    private double tiempo=0;
    public Amplitud(Nodo nodo, String nodoABuscar) {
        this.nodo = nodo;
        this.nodobuscado = nodoABuscar;
    }

    public String getCamino() {
        return camino;
    }

    public void setCamino(String camino) {
        this.camino = camino;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public String busquedaAmplitud(Nodo nodo) {
        numIteraciones++;
        if(nodo.visitado==false)
        camino = camino + nodo.nombre;
        System.out.println("Nodo : " + nodo.getNombre());
        if (nodo.nombre.equalsIgnoreCase(nodobuscado)) {
            encontrado = true; //System.out.println("Nodo Encontrado Camino: " + camino );
            return camino;
        }
        if (nodo.nodos != null & nodo.visitado == false) {//Para saber que no es  una hoja
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null){
                    cola.add(nodop);
                }
            }
            nodo.setVisitado(true);
        }
        if (!cola.isEmpty() & encontrado==false ) {//hasta que se vacie la cola o hasta encotrar 
            Nodo n = cola.getFirst();
            cola.removeFirst();
            busquedaAmplitud(n);
        }
        return "";
    }

    Nodo origen = null;

    public Nodo buscarorigen(Nodo nodo, String nodobuscado) {
        //System.out.println("Nodo" + nodo.nombre );
        if (nodo.nombre.equalsIgnoreCase(nodobuscado)) {
            //System.out.println("Nodo Encontrado" + nodo.nombre);
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

    public int getNumIteraciones() {
        return numIteraciones;
    }

    public void setNumIteraciones(int numIteraciones) {
        this.numIteraciones = numIteraciones;
    }

    @Override
    public String busqueda(Nodo nodo, String buscado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Amplitud() {
    }

    @Override
    public String calcularBusqueda() {
        double time_start, time_end;
        time_start = System.nanoTime();   //Comienza el tiempo  
        busquedaAmplitud(nodo);
        time_end = System.nanoTime();//Termina el tiempo
        tiempo = (time_end - time_start);

        if(encontrado)
        return "\t"+tiempo+"\t"+this.camino + "\t\t" + this.numIteraciones+"\t SI";
        else
        return "\t"+tiempo+"\t"+this.camino + "\t\t" + this.numIteraciones+"\t NO";       
    }
}