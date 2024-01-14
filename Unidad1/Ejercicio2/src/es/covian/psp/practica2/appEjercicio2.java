package es.covian.psp.practica2;

/*
 * ESTA ES LA SEGUNDA PARTE DEL EJERCICIO
 */

import java.io.*;

public class appEjercicio2 {
    private static String esPrimo(int n) {
        if (n == 0) {
            return "NO es primo";
        } else {
            return "Es primo";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String rutaHijo = EsPrimo.class.getName();
            ProcessBuilder pb = new ProcessBuilder("java", rutaHijo);
            // Asigna el directorio de trabajo
            pb.directory(new File("out/production/Ejercicio2"));
            try {
                Process procesoPrimo = pb.start();

                // Comunica con la entrada de esPrimo y le envia los valores de args
                try (PrintWriter entradaPrimo = new PrintWriter(procesoPrimo.getOutputStream())) {
                    entradaPrimo.println(args[i]);
                }

                // Comunica con la salida estándar de esPrimo
                try (BufferedReader salidaPrimo = new BufferedReader(new InputStreamReader(procesoPrimo.getInputStream(),"ISO-8859-1"))) {
                    String mensaje;
                    while ((mensaje = salidaPrimo.readLine()) != null) {
                        System.out.println("Argumento " + args[i] + ": " + esPrimo(Integer.parseInt(mensaje)));
                    }
                }

                // Comunica con la salida de errores de esPrimo
                try (BufferedReader salidaErroresPrimo = new BufferedReader(new InputStreamReader(procesoPrimo.getErrorStream(),"ISO-8859-1"))) {
                    String mensaje;
                    while ((mensaje = salidaErroresPrimo.readLine()) != null) {
                        System.err.println("Argumento " + args[i] + ": " + mensaje);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
