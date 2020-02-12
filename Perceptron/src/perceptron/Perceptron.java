/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import java.util.Scanner;

/**
 *
 * @author Darwin
 */
public class Perceptron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          
        
        int[][] matriz = new int[4][3];
        matriz[0][0] = -1;
        matriz[1][0] = -1;
        matriz[2][0] = 1;
        matriz[3][0] = 1;

        matriz[0][1] = -1;
        matriz[1][1] = 1;
        matriz[2][1] = -1;
        matriz[3][1] = 1;

        matriz[0][2] = -1;
        matriz[1][2] = -1;
        matriz[2][2] = -1;
        matriz[3][2] = 1;

        int numeroIteraciones;
        int fs;
        double w1, w2;
        double s = 0;
        double[] w = new double[2];
        double theta = 0.5;
        //Generamos pesos aleatorios
        w1 = 1;//Math.random();
        w2 = 1;//Math.random();
        w[0] = w1;
        w[1] = w2;

        System.out.printf("w1\tw1\n%.2f\t%.2f%n", w1, w2);

        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.printf("Opciones:\n\t%s\n\t%s\n\t%s\n",
                    "1.Entrenar",
                    "2.Verificar",
                    "3.Salir");
            int opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    //System.out.println("Theta:");
                    //theta=entrada.nextDouble();
                    System.out.println("Numero Iteraciones");
                    numeroIteraciones = entrada.nextInt();
                    for (int i = 0; i < numeroIteraciones; i++) {
                        for (int j = 0; j < 4; j++) {
                            //for sumatoria
                            for (int k = 0; k < 2; k++) {
                                s = matriz[j][k] * w[k] + s;

                            }
                            //Comprueba funcion
                            s = s + theta;
                            System.out.printf("%.2f%n", s);
                            if (s > 0) {

                                fs = 1;
                                s = 0;

                                if (fs != matriz[j][2]) {
                                    theta = theta - 1;
                                    //Calculamos pesos nuevos
                                    for (int m = 0; m < 2; m++) {
                                        w[m] = w[m] + matriz[j][m];
                                    }//For calcula los nuevos pesos
                                }

                            } else {
                                fs = -1;
                                s = 0;

                                if (fs != matriz[j][2]) {
                                    theta = theta - 1;
                                    //Calculamos pesos nuevos
                                    for (int m = 0; m < 2; m++) {
                                        w[m] = w[m] - matriz[j][m];
                                    }//For calcula los nuevos pesos
                                }

                            }
                            //error = matriz[j][2] - fs;

                        }//For recorre las filas

                    }//Fin for para iteraciones
                    System.out.printf("Pesos Entrenados:\n\t\t  w1\tw2\ttheta\n\t\t  %.2f\t%.2f\t%.2f\n", w[0], w[1], theta);
                    break;
                case 2:
                    System.out.println("Ingres x1");
                    int x1 = entrada.nextInt();
                    System.out.println("Ingrese x2");
                    int x2 = entrada.nextInt();
                    double salida = x1 * w[0] + x2 * w[1] + theta;
                    if (salida > 0) {
                        int y = 1;
                        System.out.printf("Y:%s\n", y);
                    } else {
                        int y = -1;
                        System.out.printf("Y:%s\n", y);
                    }

                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.printf("Ingrese Opcion Correcta");
                    break;

            }
        }

    }//Fin metodo Main

}//Fin de clase perceptron 
