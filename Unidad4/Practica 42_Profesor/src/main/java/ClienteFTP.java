import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Scanner;

public class ClienteFTP {
    //Comando de salida
    private final String SALIR = "QUIT";
    // Códigos de escape ANSI para cambiar el color del texto
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";


    private FTPClient cliente;
    //Lo usaremos para almacenar el contenido del directorio de trabajo actual
    private FTPFile[] workingDirContent;

    private Scanner sc = new Scanner(System.in);


    /**
     * Inicializa la instancia de FTPClient
     */
    public ClienteFTP() {
        cliente = new FTPClient();
    }

    /**
     * Ejecuta el bucle de navegación.
     *  - Muestra el contenido del directorio de trabajo actual
     *  - Pide por pantalla al usuario un directorio para entrar o archivo para descargar
     *      - Procesa el objetivo (directorio o archivo) introducido
     *      - Si lo introducido es la palabra de salida de la constante SALIR, termina el bucle
     *
     * Propaga todas las excepciones
     */
    public void bucleNavegacion() throws IOException {
        String respuesta = "";
        do{
            mostrarContenidoDirectorio();
            System.out.println("Directorio para entrar o archivo a descargar: ");
            respuesta = sc.nextLine();
            procesar(respuesta);
        }while(respuesta.equals(SALIR));
    }

    /**
     * Dado un supuesto nombre de archivo o directorio obtiene el FTPFile correspondiente. Si no es null:
     *  - Si es un archivo, lo descarga
     *  - Si es un directorio, entra en él
     *
     *  PROPAGA todas las excepciones
     */
    private void procesar(String nombreObjetivo) throws IOException {
        FTPFile objetivo = getFTPFileObjetivo(nombreObjetivo);
        if(objetivo != null){
            if(objetivo.isDirectory()){
                cliente.changeWorkingDirectory(nombreObjetivo);
            } else{
                descargarArchivo(nombreObjetivo);
            }
        }
    }

    /**
     * Explora el array workingDirecrtory en busca de un archivo o directorio con el nombreObjetivo dado
     * Devuelve su referencia FTPFile o null si no está dentro del array
     */
    private FTPFile getFTPFileObjetivo(String nombreObjetivo) throws IOException {
        for (FTPFile ftpFile : workingDirContent) {
            if(ftpFile.getName().equals(nombreObjetivo)){
                return ftpFile;
            }
        }
        return null;
    }

    /**
     * Dado un nombre de fichero remoto en la ruta actual del FTP
     * lo descarga a un fichero con el mismo nombre en la raíz del proyecto o donde queráis
     *
     * Muestra un mensaje si la descarga se ejecuta correctamente
     */
    private void descargarArchivo(String ficheroRemoto) throws IOException {
        //Creamos el directorio "descargas" en la raiz del proyecto, si no existe
        File descargas = new File("descargas");
        if(!descargas.isDirectory()){
            descargas.mkdir();
        }

        //Creamos un OutputStream para crear un fichero local en el directorio descargas
        try(BufferedOutputStream flujoFicheroLocal = new BufferedOutputStream(new FileOutputStream("descargas/" + ficheroRemoto))) {
            boolean descargaOk = cliente.retrieveFile(ficheroRemoto, flujoFicheroLocal);
            if (descargaOk) {
                System.out.println("Archivo descargado corrrectamente");
            }
        }
    }

    /**
     * Muestra el contenido dl directorio actual:
     *  - primero una línea con la ruta actual
     *  - después, cada uno de sus ficheros y subdirectorios
     */
    private void mostrarContenidoDirectorio() throws IOException {
        workingDirContent = cliente.listFiles();
        System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
        for (FTPFile ftpFile : workingDirContent) {
            System.out.println(ftpFile);
        }
    }

    /**
     * Solicita al usuario el servidor ftp e intenta conectarse a él, mostrando la respuesta dada
     * Antes de conectar, establece la codificación UTF-8 para la conexión del canal de control
     * Cuando consiga conectar, llama a la función de login()
     *
     * Repite el procedimiento mientras no consiga conectarse a un servidor (captura excepciones)
     */
    public void conectarCliente() {
        while(!cliente.isConnected()){
            System.out.print("Introduce el servidor ftp: ");
            String servidor = sc.nextLine();
            cliente.setControlEncoding("UTF-8");
            try {
                cliente.connect(servidor);
                mostrarRespuesta();
                login();
            } catch (IOException e) {
                System.err.println("No se ha podido conectar al servidor introducido");
            }
        }
    }

    /**
     * Solcita usuario y contraseña e intenta loguearse en el servidor hasta conseguirlo
     * Propaga las excepciones
     */

    private void login() throws IOException {
        boolean logueado = false;
        do{
            System.out.println("Introduzca el usuario");
            String usuario = sc.nextLine();
            System.out.println("Introduzca la constraseña");
            String pass = sc.nextLine();
            logueado = cliente.login(usuario, pass);
        }while (!logueado);

    }

    /**
     * Muestra la respuestas del último comando enviado al servidor en color verde
     */
    private void mostrarRespuesta() {
        System.out.println(ANSI_GREEN + cliente.getReplyString() + ANSI_RESET);
    }

    /**
     * Se desloguea y se desconecta del servidor ftp, propagando excepciones
     */
    public void desconectar() throws IOException {
        cliente.disconnect();
    }
}
