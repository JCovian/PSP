/**
 * Practica 1
 * Realiza un programa Java que admita desde la linea de comandos un nombre de máquina o una dirección IP
 * y visualice la información sobre ella
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppMain {

    public static void main(String[] args) {
        String url = pedirUrl();
        printDatosUrl(url);
    }

    private static String pedirUrl(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una URL:");
        return sc.nextLine();
    }
    private static void printDatosUrl(String url) {
        try {
            InetAddress dir = InetAddress.getByName(url);
            System.out.println("\tSalida toString(): " + dir);
            System.out.println("\tMétodo getHostName(): " + dir.getHostName());
            System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
            System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
            //Otras posibles IPs
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            System.out.println("\tOtras direcciones; ");
            for (InetAddress direccion : direcciones) {
                System.out.println(direccion.toString());
            }

        } catch (UnknownHostException e) {
            //throw new RuntimeException(e);
            System.err.println("Host desconocido");
        }
    }
}
