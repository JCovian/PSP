package com.prsp.examen.ejercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
Pulsar la combinación de teclas Ctrl + D para terminar de introducir datos
 */

/**
 * Calcula la media de los valores enteros recibidos
 * TODO: Si no se recibe ningún entero, sale con un código de error
 */
public class CalculaMedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double suma = 0;
        int cuenta = 0;
        while(sc.hasNextLine()){
            String recibido = sc.nextLine();
            try {
                int valor = Integer.parseInt(recibido);
                suma += valor;
                cuenta++;
            } catch (NumberFormatException e){
                System.err.println(recibido  + " no es un número");
            }
        }

        //Añadimos esto para devolver un código de salida adecuado cuando no se recibe ningún número
        if(cuenta == 0){
            System.exit(-1);
        }
        //Devolvemos la media
        System.out.println(suma/cuenta);

    }
}
