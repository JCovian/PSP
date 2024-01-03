package es.covian.psp.mensajes;

import java.io.*;
import java.util.Scanner;

public class Padre {
    public static void main(String[] args) {
        String rutaHijo = Hijo.class.getName();
        ProcessBuilder pb = new ProcessBuilder("java", rutaHijo);
        //Cambiamos el "working directory" a la ruta base donde IntelliJ genera los .class
        pb.directory(new File("out/production/Mensajes"));
        //System.out.println("El directorio de ejecución es " + pb.directory().getAbsolutePath());

        try {
            Process proceso = pb.start();

            //Comunicamos con la entrada y salida del proceso
            try(PrintWriter entradaProceso = new PrintWriter(proceso.getOutputStream());){
                entradaProceso.println(sendMessage());
            }

            //Nos ponemos a la escucha
            try(BufferedReader salidaProceso = new BufferedReader(new InputStreamReader(proceso.getInputStream(),"cp437"))){
                String mensaje;
                while ((mensaje = salidaProceso.readLine()) != null) {
                    System.out.println("Mensaje del hijo: " + mensaje);
                }
            }

            try (BufferedReader salidaError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {
                System.out.println("Salida de errores del proceso: ");
                String error;
                while ((error = salidaError.readLine()) != null){
                    System.out.println(error);
                }
            }

            int exitVal = proceso.waitFor();
            System.out.println("Valor de salida: " + exitVal);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String sendMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca mensaje a enviar: ");
        return sc.next();
    }
}
