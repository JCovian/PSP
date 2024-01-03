package es.covian.psp.mensajes;

import java.io.*;
import java.util.Scanner;

public class Padre2 {
    public static void main(String[] args) {
        String rutaHijo = Hijo2.class.getName();
        ProcessBuilder pb = new ProcessBuilder("java", rutaHijo);
        //Asignamos el directorio de tabaja
        pb.directory(new File("out/production/Mensajes"));

        try {
            Process procesoHijo = pb.start();

            //Comunicamos con la entrada del hijo
            try(PrintWriter entradaHijo = new PrintWriter(procesoHijo.getOutputStream());){
                entradaHijo.println(sendMessage());
            }

            //Comunicamos con la salida del hijo
            try(BufferedReader salidaHijo = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream(),"cp437"))){
                String mensaje;
                while ((mensaje = salidaHijo.readLine()) != null) {
                    System.out.println(mensaje);
                }
            }

            //Comunicamos con la salida de errores del hijo
            try(BufferedReader salidaHijo = new BufferedReader(new InputStreamReader(procesoHijo.getErrorStream(),"cp437"))){
                String mensaje;
                while ((mensaje = salidaHijo.readLine()) != null) {
                    System.err.println(mensaje);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo que pide un texto por teclado
    public static String sendMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca mensaje a enviar: ");
        return sc.nextLine();
    }
}
