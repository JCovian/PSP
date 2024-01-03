import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Jose Aquilino Covián Ornia
 * Escribe en la entrada del proceso hijo una cadena codificada directamente
 * en el programa (un literal), conteniendo el caracter de terminación (*).
 * Muestra la salida del hijo.
 */
public class PadreCaracteres {
    public static void main(String[] args) {
        String rutaHijo = HijoCaracteres.class.getName();
        ProcessBuilder pb = new ProcessBuilder("java", rutaHijo);
        // Asigna el directorio de trabajo
        pb.directory(new File("out/production/Practica3"));

        try {
            Process procesoHijo = pb.start();

            // Comunica con la entrada del hijo
            try(PrintWriter entradaHijo = new PrintWriter(procesoHijo.getOutputStream());){
                entradaHijo.println(sendMessage());
            }

            // Comunica con la salida del hijo
            try(BufferedReader salidaHijo = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream(), StandardCharsets.ISO_8859_1))){
                String mensaje;
                while ((mensaje = salidaHijo.readLine()) != null) {
                    System.out.println(mensaje);
                }
            }

            // Comunica con la salida de errores del hijo
            try(BufferedReader salidaErrorHijo = new BufferedReader(new InputStreamReader(procesoHijo.getErrorStream(), StandardCharsets.ISO_8859_1))){
                String mensaje;
                while ((mensaje = salidaErrorHijo.readLine()) != null) {
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
        System.out.println("Escriba texto a enviar, introduzca un * como caracter de finalización: ");
        return sc.nextLine();
    }
}
