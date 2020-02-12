/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorafactorial;

/**
 *
 * @author Darwin
 */
//Método Factorial recursivo
public class CalculadoraFactorial {

    //método factorial recursivo (asume que su parametro es mayor o igual a cero)
    public static long factorial(long numero) {
        if (numero <= 1) //evalua el caso base
        {
            return 1;//caso base 0!=1 y 1!=1
        } else//paso recursivo
        {
            return numero * factorial(numero - 1);
        }
    }//fin metodo factorial

    public static void main(String[] args) {
        // Calculamos factorial del 0 al 10
        for (int contador = 0; contador <= 5; contador++) {
            System.out.printf("%d! = %d\n", contador, factorial(contador));
        }
    }//fin metodo main
}//fin clase CalculadoraFactorial
