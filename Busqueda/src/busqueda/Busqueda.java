/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busqueda;

/**
 *
 * @author Estudiante
 */
public class Busqueda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Nodo A= new Nodo();
        Nodo B= new Nodo();
        Nodo C= new Nodo();
        Nodo D= new Nodo();
        Nodo E= new Nodo();
        Nodo F= new Nodo();
        Nodo G= new Nodo();
        
        A.getHijos().add(B);
        A.getHijos().add(C);
        B.getHijos().add(D);
        B.getHijos().add(E);
        C.getHijos().add(F);
        C.getHijos().add(G);
        E.getHijos().add(G);
        F.getHijos().add(G);
        
        A.setNombre("A");
        B.setNombre("B");
        C.setNombre("C");
        D.setNombre("D");
        E.setNombre("E");
        F.setNombre("F");
        G.setNombre("G");
        
        
        
        
        //System.out.println("");
        //E.mostrarHijos();
        
        
        System.out.println("");
        A.buscarHijo(C);
        
    }
}