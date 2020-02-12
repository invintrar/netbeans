/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Darwin
 */
public class mostrar {
    String nombre;
    String apellido;
    void ingresar(String nom, String ap){
        nombre = nom;
        apellido = ap;
    }
    void mostrarNombre(){
        System.out.printf("Su nombre es: %s\t%s\n", nombre,apellido);
    }
}

