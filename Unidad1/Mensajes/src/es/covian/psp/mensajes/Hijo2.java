/*Práctica 2. Proyecto HijoMayusculas

        Clase Hijo:

        -          Lee de su flujo de entrada estándar, y escribe lo leído en mayúsculas por su salida estándar.

        -          Si lee la cadena “0” escribe por su salida de errores el texto ”Error”.

        -          En el caso de error, devuelve un código de salida adecuado.

        Clase Padre

        -          Lanza el proceso Hijo.

        -          Pide al usuario que introduzca un texto, que puede contener espacios.

        -          Envía el texto recibido al Hijo.

        -          Muestra la salida estándar y de errores del hijo por su propia salida correspondiente.

        -          Al final, muestra en un mensaje el código de salida del hijo.*/

package es.covian.psp.mensajes;

import java.util.Scanner;

public class Hijo2 {
    public static void main(String[] args) {
        //Lee por la entrada estándar
        Scanner sc = new Scanner(System.in);
        String salida = sc.nextLine();

        //visualizamos en mayúsculas el texto excepto si es 0 que da error
        if (salida.equals("0")) {
            System.err.println("ERROR ha introducido un 0");
            System.exit(-1);
        } else {
            System.out.println(salida.toUpperCase());
        }

    }
}
