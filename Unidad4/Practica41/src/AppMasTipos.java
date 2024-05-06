import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class AppMasTipos {
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
            evaluaURL(aURL);
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

    private static void evaluaURL (URL aURL) {
        try {
            //Conectamos con la URL
            URLConnection urlConnection = aURL.openConnection();
            String tipo = urlConnection.getContentType();
            System.out.println(tipo);
            final String img = "image/jpeg";
            final String html = "text/html";
            final String pdf = "application/pdf";
            if (tipo.contains(html)) {
                tipo = html;
            }

            switch (tipo){
                case img -> guardaImagen();
                case html -> escribirFichero(urlConnection);
                case pdf -> guardaPDF(urlConnection, aURL);
                default -> throw new IllegalStateException("Unexpected value: " + tipo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void guardaImagen(){
        //TODO: Guardar imagen en disco
    }

    private static void guardaPDF(URLConnection urlConnection, URL aURL) {
        try {
            File pdf = new File(aURL.getFile());
            InputStream in = new FileInputStream(pdf);
            System.out.println("Introduzca el nombre del fichero: ");
            fichero = sc.nextLine();
            fichero = fichero + ".pdf";
            File file = new File(RUTA, fichero);
            OutputStream out = new FileOutputStream(file);

            byte [] buf = new byte[1024];
            int len;

            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirFichero(URLConnection urlConnection) {
        //Conectamos con la URL
        try {
        /*
            URLConnection urlConnection = aURL.openConnection();
            String tipo = urlConnection.getContentType();
            String img = "image/jpeg";
            String html = "text/html";
            String pdf = "aplication/pdf";
         */
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
