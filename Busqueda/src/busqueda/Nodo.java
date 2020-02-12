package busqueda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estudiante
 */
import java.util.ArrayList;
import java.util.List;
public class Nodo
{
    private String Nombre;
    private boolean EsRaiz;

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }
    
    private List<Nodo> hijos = new ArrayList();
    
    //implementar los sets y gets de padres
    private List<Nodo> padres = new ArrayList();

    public void setHijos(List<Nodo> hijos) {
        this.hijos = hijos;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }
    
    public void isEsHijo()
    {
        
    }
    
    public void mostrarHijos()
    {
        
        for(Nodo x:hijos)
        {
            System.out.println(x.getNombre());
            x.mostrarHijos();
        }
    }
    
    public void buscarHijo(Nodo buscado)
    {
        //String esPadre;
        for(Nodo x:hijos)
        {
            //esPadre=x.Nombre;
            if (x == buscado)
            {
                System.out.println(x.getNombre());
            }
            x.buscarHijo(buscado);
        }
    }
}