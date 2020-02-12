/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vero
 */
public class lectura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombre = "C:\\Users\\Usuario\\Desktop\\Datos.txt";
        // Fichero del que queremos leer
        File fichero = new File(nombre);
        Scanner s = null;
        try {
            // Leemos el contenido del fichero
            System.out.println("... Leemos el contenido del fichero ...");
            s = new Scanner(fichero);
            // Leemos linea a linea el fichero
            while (s.hasNextLine()) {
                String linea = s.nextLine(); 	// Guardamos la linea en un String
                System.out.println(linea);      // Imprimimos la linea
                //---------------------ESCRIBIR---------------------------------        
                try {
                    nombre = "C:\\Users\\Usuario\\Desktop\\DatosNuevos.txt";
                    FileWriter fstream = new FileWriter(nombre, true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(linea+":0:0,\n");
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }

        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null) {
                    s.close();
                }
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
    }
}