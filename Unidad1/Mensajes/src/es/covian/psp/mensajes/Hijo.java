/*Crea un nuevo proyecto IntelliJ de nombre mensajes. Crea dos clases.

Clase Hijo, que sin mostrar nada previamente, responda de manera determinada a lo que recibe por su entrada estándar y termine.
si recibe la cadena "Tic", responde "Toc"
si recibe la cadena "Eco", responde "Eco"
si recibe la cadena "Marco", responde "Polo"
si recibe cualquier otra cosa, escribe "Error" por su salida de errores y devuelve un código de salida adecuado.
Compilado y probadlo.
Clase Padre.
Lanza el proceso hijo y se comunica con él.
Solicita por pantalla al usuario que introduzca el mensaje a enviar al hijo, lo recoge y se lo envía.
A continuación lee de sus flujos de salida estándar y de errores, y los muestra por sus propios flujos correspondientes
(el de estándar al estándar, y el de errores al de errores).
El hijo es muy simple, pero en la clase Padre procurad separar cada operación en funciones independientes y evitad repetir
código en la medida de lo posible.*/

package es.covian.psp.mensajes;

import java.util.Scanner;

public class Hijo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        switch (entrada) {
            case "Tic" -> System.out.println("Toc");
            case "Eco" -> System.out.println("Eco");
            case "Marco" -> System.out.println("Polo");
            default -> {
                System.err.println("Error");
                System.exit(-1);
            }
        }
    }
}
