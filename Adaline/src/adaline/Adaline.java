/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaline;

import java.util.Scanner;

/**
 *
 * @author Darwin
 */
public class Adaline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //int [][] matriz = new int[7][4];
        double[][] matriz = new double[8][5];

        matriz[0][0] = 0;
        matriz[1][0] = 0;
        matriz[2][0] = 0;
        matriz[3][0] = 0;
        matriz[4][0] = 0;
        matriz[5][0] = 0;
        matriz[6][0] = 0;
        matriz[7][0] = 1;

        matriz[0][1] = 0;
        matriz[1][1] = 0;
        matriz[2][1] = 0;
        matriz[3][1] = 1;
        matriz[4][1] = 1;
        matriz[5][1] = 1;
        matriz[6][1] = 1;
        matriz[7][1] = 0;

        matriz[0][2] = 0;
        matriz[1][2] = 1;
        matriz[2][2] = 1;
        matriz[3][2] = 0;
        matriz[4][2] = 0;
        matriz[5][2] = 1;
        matriz[6][2] = 1;
        matriz[7][2] = 0;

        matriz[0][3] = 1;
        matriz[1][3] = 0;
        matriz[2][3] = 1;
        matriz[3][3] = 0;
        matriz[4][3] = 1;
        matriz[5][3] = 0;
        matriz[6][3] = 1;
        matriz[7][3] = 0;

        matriz[0][4] = 1;
        matriz[1][4] = 1;
        matriz[2][4] = 1;
        matriz[3][4] = 1;
        matriz[4][4] = 1;
        matriz[5][4] = 1;
        matriz[6][4] = 1;
        matriz[7][4] = 0;

        int numeroIteraciones;
        int fs;
        double w1, w2, w3, w4, E,s;
        double[] w = new double[4];
        double delta = 0.3;
        //Generamos pesos aleatorios
        w1 = 0.84;//Math.random();
        w2 = 0.39;//Math.random();
        w3 = 0.78;//Math.random();
        w4 = 0.65;//Math.random();
        w[0] = w1;
        w[1] = w2;
        w[2] = w3;
        w[3] = w4;
        E = 0;
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
                    System.out.println("Numero Iteraciones");
                    numeroIteraciones = entrada.nextInt();
                    for (int i = 0; i < numeroIteraciones; i++) {

                        for (int j = 0; j < 8; j++) {
                            s=0;
                            //for calcular ycalculada
                            for (int k = 0; k < 4; k++) {
                                s += matriz[j][k] * w[k];
                            }
                            ////Calculamos el error ydeseada-ycalculada
                            double error = matriz[j][4] - s;
                            //double abs = Math.abs(error);
                            
                            //Calculamos los nuevos pesos
                            for (int m = 0; m < 4; m++) {
                                w[m] = w[m] + delta * error * matriz[j][m];
                            }//For calcula los nuevos pesos
                            
                            E += Math.pow(error, 2);

                        }//For recorre las filas

                    }//Fin for para iteraciones
                    E = E * 0.5;
                    System.out.println(E);
                    break;
                case 2:
                    System.out.println("Ingres x1");
                    int x1 = entrada.nextInt();
                    System.out.println("Ingrese x2");
                    int x2 = entrada.nextInt();
                    System.out.println("Ingrese x3");
                    int x3 = entrada.nextInt();
                    System.out.println("Ingrese x4");
                    int x4 = entrada.nextInt();
                    double salida = x1 * w[0] + x2 * w[1] + x3 * w[2] + x4 * w[3];
                    System.out.printf("%.2f\n", salida);

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
