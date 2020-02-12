/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesneuronales;

/**
 *
 * @author Darwin
 */
public class Busqueda {
    Nodo raiz;
    Nodo destino;

    public Busqueda(Nodo raiz, Nodo destino) {
        this.raiz = raiz;
        this.destino = destino;
    }
    public void profundidad(){
        
        for(Nodo x:raiz.hijos){
            System.out.println(x.nombre);
            if(raiz.visitado && destino.equals(x)){
                profundidad();
            }
        }
    }
    
    
}
