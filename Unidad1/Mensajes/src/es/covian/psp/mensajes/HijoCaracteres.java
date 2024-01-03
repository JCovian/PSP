/*Práctica 2. Proyecto HijoMayusculas

        Clase Hijo:

        -          Lee de su entrada estandar hasta recibir el caracter de terminacion y
                    muestra lo leido entonces

        Clase Padre

        -          Lanza el proceso Hijo.

        -          Pide al usuario que introduzca un texto, que puede contener espacios.

        -          Envía el texto recibido al Hijo.

        -          Muestra la salida estándar y de errores del hijo por su propia salida correspondiente.

        -          Al final, muestra en un mensaje el código de salida del hijo.
*/

package es.covian.psp.mensajes;

import java.io.IOException;
import java.io.InputStreamReader;

public class HijoCaracteres {
    public static void main(String[] args) throws IOException {
        InputStreamReader lectura = new InputStreamReader(System.in);
        char caracter = (char)lectura.read();
        String totalLeido = "";
        while (caracter !=  '*') {
            totalLeido += caracter;
            caracter = (char)lectura.read();
        }
        System.out.println(totalLeido);
    }
}
