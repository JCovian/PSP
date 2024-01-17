import java.io.*;
import java.util.Scanner;

/**
 * Programa que recibe como argumentos del main una serie de tokens
 * Por cada token recibido crea un proceso de la clase EsPrimo y devuelve su salida estándar
 * y de error (si no es un número)
 * La salida de error se mostrará por la salida estándar
 */
public class NumerosSonPrimos {

    public static void main(String[] args) {

            //Creamos un proceso por cada argumento recibido. Uso el .class.getName para que funcione en caso de usar paquetes
            ProcessBuilder pb = new ProcessBuilder("java",EsPrimo.class.getName());
            //Le indico que se ejecute desde el directorio de binarios de IntelliJ
            pb.directory(new File("out/production/EsPrimo"));
        for (int i = 0; i < args.length; i++) {
            System.out.print("Argumento " + args[i] + ": ");
            try {
                //Iniciamos el proceso
                Process proceso = pb.start();
                //Le pasamos el argumento correspondiente a su flujo de entrada estándar
                try(PrintWriter entrada = new PrintWriter(proceso.getOutputStream());){
                    entrada.println(args[i]);
                }
                //Leemos de sus flujos de salida y de error
                //Voy a leer uno con Scanner y otro con BufferedReader, para ver ambas formas
                try(Scanner salida = new Scanner(proceso.getInputStream(),"ISO-8859-1");) {
                    //Leo de forma genérica, como si fuese a escribir una cadena completa en la salida
                    // (aunque sabemos que como mucho escribirá un 1 o un 0)
                    while (salida.hasNextLine()) {
                        String linea = salida.nextLine();
                        if (Integer.valueOf(linea) == 1) {
                            System.out.println("Es primo");
                        } else {
                            System.out.println("NO es primo");
                        }
                    }
                }

                //Ahora leo el flujo de errores con BufferedReader
                try(BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream(),
                        "ISO-8859-1"))){
                    String linea;
                    while((linea = errores.readLine()) != null){
                        System.out.println(linea);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
