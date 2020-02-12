/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readtextfile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Darwin
 */
public class ReadTextFile {

    private static Scanner input;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        openFile();
        readRecords();
        closeFile();
    }

    //open file archivo.txt
    public static void openFile() {
        try {
            input = new Scanner(Paths.get("C:\\Users\\Darwin\\Documents\\archivo.txt"));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }

    //read record from file
    public static void readRecords() {
        //System.out.printf("%-10s%-12s%n","Padres","Hijos");
        List<String> matriz = new ArrayList();
        if (input.hasNext(",")) {

        }

        try {
            while (input.hasNext()) {
                String linea = input.next();
                boolean indicaPadre = true;
                String[] valor = linea.split(",");
                for (String nodo : valor) {
                    System.out.println(nodo);
                }
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
        }
    }//End method readRecords

    //close file and terminate aplication
    public static void closeFile() {
        if (input != null) {
            input.close();
        }
    }//end closeFile

}//en classReadTextFile
