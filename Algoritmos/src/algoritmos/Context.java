/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.List;

/**
 *
 * @author Darwin
 */
public class Context {
    Busqueda c;
 
	public Context( Busqueda c )
	{
		this.c = c;
	}
 
	public void setBusqueda(Busqueda c) {
		this.c = c;
	}
 
	//Metodo de estrategia 'c'
	public String some_method()
	{
            return c.calcularBusqueda();
	}    
}
