import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Profesor {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.println("Introduce una URL: ");
        String urlString = sc.nextLine();
        try {
            URL miUrl = new URL(urlString);
            mostrarDatos(miUrl);

            System.out.println("Introduce un nombre de fichero: ");
            String ficheroNom = sc.nextLine();
            try(PrintWriter flujoFichero = new PrintWriter(new FileWriter(ficheroNom + ".html"))){
                URLConnection conexion = miUrl.openConnection();
                BufferedReader flujoLectura = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
                String linea;
                while((linea = flujoLectura.readLine()) != null) {
                   flujoFichero.println(linea);
                }
                System.out.println("Contenido de la URL almacenada");
                flujoLectura.close();
            } catch (IOException e) {
                System.err.println("ERROR I/O al abrir el fichero");
            }

        } catch (MalformedURLException e) {
            System.err.println("URL incorrecta: " + e.getMessage());
        }
    }

    private static void mostrarDatos(URL aURL) {
        System.out.println("protocolo = " + aURL.getProtocol());
        System.out.println("host = " + aURL.getHost());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("port = " + aURL.getPort());
        System.out.println("default port = " + aURL.getDefaultPort());
        System.out.println("ref = " + aURL.getRef());
    }
}
