package es.covian.psp.mensajes;

import java.io.*;
import java.util.Scanner;

public class PadreProfesor {
    public static void main(String[] args) {
        //Obtenemos el nombre cualificado de la clase
        String clase = HijoProfesor.class.getName();
//        System.out.println("Clase: " + clase);

        ProcessBuilder pb = new ProcessBuilder("java", clase);
        //Cambiamos al directorio de trabajao donde se ejecuta el comando indicado
        pb.directory(new File("out/production/Mensajes"));
        //Confirmamos el cambio de directorio
        System.out.println("Directorio de ejecución: " + pb.directory().getAbsolutePath());

        try {
            Process hijo = pb.start();
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca el mensaje a enviar: ");
            String mensaje = sc.nextLine();

            //Abrimos el flujo de comunicacion con la entrada del hijo
            try(PrintWriter entradaHijo = new PrintWriter(hijo.getOutputStream());){
                entradaHijo.println(mensaje);
            }

            //Abrimos el flujo de comunciación con la salida del hijo
            //leerFlujoSaliente(hijo.getInputStream(), System.out);
            leerFlujoSalienteScanner(hijo.getInputStream(), System.out);

            //Abrimos el flujo de comunciación con la salida de errores del hijo
            leerFlujoSaliente(hijo.getErrorStream(), System.err);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerFlujoSaliente(InputStream hijo, PrintStream salidaPadre) throws IOException {
        try (BufferedReader salidaHijo = new BufferedReader(new InputStreamReader(hijo, "cp437"));) {
            String linea;
            while ((linea = salidaHijo.readLine()) != null) {
                salidaPadre.println(linea);
            }
        }
    }

    private static void leerFlujoSalienteScanner(InputStream hijo, PrintStream salidaPadre) {
        try(Scanner sc = new Scanner(hijo);) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                salidaPadre.println(linea);
            }
        }
    }
}
