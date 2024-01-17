/**
* @author Jose Aquilino Covián Ornia
* Se pide un nombre de fichero por teclado y una orden CMD
* se crea el fichero y se añade en el el resultado de ejecutar
* la orden CMD
*
*/

import java.io.*;
import java.util.Scanner;

public class AppCmdToFile {
        private static final Scanner sc = new Scanner(System.in);
        private static final Runtime rt = Runtime.getRuntime();

    public static void main(String[] args) {
        Process proceso;
        File fichero;
        String comando;
        String programa = "cmd.exe /c ";
        String nombreFichero;

        System.out.print("Introduzca el nombre del fichero de salida: ");
        nombreFichero = sc.nextLine();
        //Crea el fichero
        fichero = new File(nombreFichero);
        try {
            System.out.println((fichero.createNewFile() ?
                    "\nFichero creado correctamente" : "\nEl fichero ya existe"));
        } catch (IOException e) {
            System.err.println("ERROR E/S al crear el fichero");
            throw new RuntimeException(e);
        }

        System.out.print("\nIntroduzca un comando CMD: ");
        comando = sc.nextLine();
        sc.close();

        //Crea el proceso
        try {
            proceso = rt.exec(programa + comando);
            //Flujo de entrada
            BufferedReader leer = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            //Flujo de salida
            BufferedWriter escribir = new BufferedWriter(new FileWriter(nombreFichero));

            String linea;
            while((linea = leer.readLine()) != null) {
                System.out.println(linea); //Escribe el resultado por consola
                escribir.write(linea); //Escribe la linea en el fichero
                escribir.newLine(); //Avanza una linea en el fichero
            }
            escribir.close(); //Cierra el flujo de escritura
            leer.close(); //Cierra el flujo de lectura

        } catch (IOException e) {
            System.err.println("¡ERROR E/S!");
            throw new RuntimeException(e);
        }
    }
}
