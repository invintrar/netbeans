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
public class RedesNeuronales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo A =new Nodo("A");//1
        Nodo B =new Nodo("B");//2
        Nodo C =new Nodo("C");//3
        Nodo D =new Nodo("D");//4
        Nodo E =new Nodo("E");//5
        Nodo F =new Nodo("F");//6
        Nodo G =new Nodo("G");//7
        Nodo H =new Nodo("H");//8
        Nodo I =new Nodo("I");//9
        Nodo J =new Nodo("J");//10
        Nodo K =new Nodo("K");//11
        Nodo L =new Nodo("L");//12
        
        A.hijos.add(B);
        A.hijos.add(G);
        A.hijos.add(H);
        
        B.hijos.add(C);
        B.hijos.add(F);
        
        C.hijos.add(D);
        C.hijos.add(E);
        
        H.hijos.add(I);
        H.hijos.add(L);
        
        I.hijos.add(J);
        I.hijos.add(K);
        
        //D.mostrarHijos();
        //Creamos objeto Busqueda
        Busqueda buscar=new Busqueda(A, I);
        buscar.profundidad();
    }
    
}
