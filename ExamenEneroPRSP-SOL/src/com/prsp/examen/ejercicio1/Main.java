package com.prsp.examen.ejercicio1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Recibe una serie de datos como argumentos de la función main()
 * Pasa esos datos a un único proceso que ejecuta la clase CalculaMedia y muestra
 *  - la salida estándar del proceso CalculaMedia por su salida estándar
 *  - la salida de errores del proceso CalculaMedia por su salida de errores
 *  - el valor de salida del proceso ejecutado
 */
public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java",CalculaMedia.class.getName());
        pb.directory(new File("out/production/ExamenEneroPRSP-SOL"));
        try {
            Process proceso = pb.start();
            //Pasamos los datos de entrada
            try(
                    PrintWriter entrada = new PrintWriter(proceso.getOutputStream(), true);
            ){
                //Pasamos todas las entradas una a una con un salto de línea final
                for (int i = 0; i < args.length; i++) {
                    entrada.println(args[i]);
                }
            }

            //Recogemos los datos de la salida estándar y de errores
            try(
                    BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream(), StandardCharsets.ISO_8859_1));
                    BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream(),StandardCharsets.ISO_8859_1))
            ) {

                mostrarRespuestaProceso(errores, System.err);
                mostrarRespuestaProceso(salida, System.out);


                System.out.println("Código de salida: " + proceso.waitFor());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static void mostrarRespuestaProceso(BufferedReader flujoHijo, PrintStream flujoPadre) {
        String linea;
        try{
            while((linea = flujoHijo.readLine()) != null){
                flujoPadre.println(linea);
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
