import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class App {
    public static String url = null;
    public static final String RUTA = "./Files";
    public static String fichero = null;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduzca una URL válida: ");
        url = sc.nextLine();
        try {
            URL aURL = null;
            aURL = new URL(url);
            mostrarDatos(aURL);
            escribirFichero(aURL);
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e);
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

    private static void escribirFichero(URL aURL) {
        //Conectamos con la URL
        try {
            URLConnection urlConnection = aURL.openConnection();

            //Flujo para leer el contenido de la URL
            BufferedReader lectura = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            System.out.println("Introduzca el nombre del fichero: ");
            fichero = sc.nextLine();
            File file = new File(RUTA, fichero);
            BufferedWriter escritura = new BufferedWriter(new FileWriter(file, true));
            String leido;
            while((leido = lectura.readLine()) != null){
                escritura.write(leido);
                escritura.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
