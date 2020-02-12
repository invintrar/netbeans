/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesneuronales;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin
 */
public class Nodo {

    String nombre;

    List<Nodo> hijos;
    List<Nodo> padre;

    List<Aristas> aristasHijos;
    List<Aristas> aristasPadres;

    boolean visitado;
    boolean buscado;

    int heuristica;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.visitado = false;
        this.buscado = false;
        hijos = new ArrayList();
        padre = new ArrayList();
        aristasHijos = new ArrayList();
    }
    public void mostrarHijos(){
        for(Nodo x:hijos){
            System.out.println(x.nombre);
            x.mostrarHijos();
        }
        
        
    }
    
}
