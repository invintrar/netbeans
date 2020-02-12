/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *Búsqueda bidireccional:
	- Se llevan a la vez dos búsquedas: una descendente 	
            desde el nodo inicial y otra ascendente desde el nodo meta.
	- Al menos una de estas dos búsquedas debe ser en anchura para que el recorrido ascendente y 
            descendente puedan encontrarse en algún momento. 
	- Cuando se llegue a un nodo que ya había sido 	explorado con el otro tipo de búsqueda, el algoritmo acaba.
     	- El camino solución es la suma de los caminos 	hallados por cada búsqueda desde el nodo mencionado 
            hasta el nodo inicial y hasta el nodo meta.


 * @author Darwin
 */
public class Bidireccional implements Busqueda{

    String caminoh="";
    String camino="";
    boolean terminar = false;
    private Nodo nodo;
    private Nodo nodoh;
    private int numIteraciones = 0;
    
    private LinkedList<Nodo> cola = new LinkedList();
    private LinkedList<Nodo> padre = new LinkedList();
    public Bidireccional(Nodo nodo, Nodo nodohoja) {
        this.nodo = nodo;
        this.nodoh = nodohoja;
    }
    public void bdireccional(Nodo nodo, Nodo nhoja) {

        amplitud(nodo, nhoja);
    }

    public void amplitud(Nodo nodo, Nodo h) {
        numIteraciones++;
        if (nodo.nodos != null & nodo.visitado == false & terminar == false) {
            camino = camino + nodo.nombre;
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    cola.add(nodop);
                }
            }
            nodo.setVisitado(true);
        } else {
//            if( terminar==true){
//            camino = camino + nodo.nombre;
//            }
            terminar = true;
        }
if(h!=null)        
        if (h.getPadre() != null & h.visitado == false & terminar == false) {
            caminoh = caminoh + h.nombre;
            Iterator<Nodo> it = h.getPadre().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    padre.add(nodop);
                }
            }
            h.setVisitado(true);
        } else {
//            if(terminar==true){
//            caminoh=caminoh+h.nombre;
//            }
            terminar = true;
        }
        
        
        if (!cola.isEmpty()) {
            Nodo n = cola.getFirst();
            cola.removeFirst();
            if (!padre.isEmpty()) {
                h = padre.getFirst();
                padre.removeFirst();
                
                amplitud(n, h);
            } else {
                System.out.println("Camino padre ---" + caminoh);
                System.out.println("Camino raiz ---" + camino);
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
        amplitud(nodo, nodoh);
        time_end = System.nanoTime();//Termina el tiempo
        String tiempo = Double.toString(time_end - time_start);
        if(terminar)
        return "\t" +tiempo+"\t"+this.camino +"-"+this.caminoh+ "\t\t" + this.numIteraciones+"\t SI";
        else
        return "\t" +tiempo+"\t"+this.camino+"-"+this.caminoh + "\t\t" + this.numIteraciones+"\t NO";       
    }
}
