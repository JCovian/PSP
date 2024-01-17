package es.covian.psp.practica2;

/*
 * EJERCICIO EVALUABLE PARA PRIMER PARCIAL (ENERO)
 *
 */

import java.util.Scanner;

public class EsPrimo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int entero;

        // >> DESCOMENTAR LA SIGUIENTE LINEA PARA PROBAR LA PRIMERA PARTE DEL EJERCICIO
        //System.out.println("Introduce un entero");
        // >> VOLVER A COMENTAR PARA PODER PROBAR LA SEGUNDA PARTE

        // Comprueba si se introduce un valor INT
        if(!sc.hasNextInt()) {
            System.err.println("El valor recibido no es un número entero");
        } else {
            entero = sc.nextInt();
            // Calcula si el entero introducido es primo
            System.out.println(esPrimo(entero));
        }
    }

    // Comprueba si un número entero es primo
    // Si es primo devuelve 1, sino 0
    private static int esPrimo(int num) {
        num = Math.abs(num);

        //Revisa si num es divisible entre 2 sino, chequea el resto de numeros menos si mismo
        if (num > 3) {
            // Compruebo si es par
            if (num % 2 == 0) {
                return 0;
            } else {
                for (int i = 3; i < num; i++) {
                    if (num % i == 0) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
}
