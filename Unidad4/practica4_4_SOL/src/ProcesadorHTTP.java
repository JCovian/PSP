import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ProcesadorHTTP extends Thread {
    private Socket cliente;
    private BufferedReader flujoEntrada;
    private PrintWriter flujoSalida;

    //Los declaro como atributos porque los uso en distintas funciones
    private String cuerpoRespuesta;
    private String cabeceraRespuesta;
    private String peticion;
    private Map<String, String> cabeceras;

    public ProcesadorHTTP(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        procesarSolicitud();
        System.out.println("Solicitud atendida");
        try {
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera la primera línea de la solicitud y la atiende
     */
    private void procesarSolicitud() {
        try {
            //Inicializamos los flujos
            flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream(), "UTF-8"));
            flujoSalida = new PrintWriter(cliente.getOutputStream(), true);

            //Leemos la primera línea de la petición del cliente, que contiene el método y el recurso solicitado
            peticion = flujoEntrada.readLine();

            //Vamos a almacenar el resto de cabeceras en un HashMap. Ejemplo de uso: https://programandoenjava.com/hashmap-en-java/
            //Las cabeceras llegan en forma de dato: valor
            //Ejemplo
            //Host: localhost:8000
            //User-Agent: Mozilla/5.0
            cabeceras = new HashMap<>();
            String lineaCabecera;
            //Al final de las cabeceras hay una línea vacía, así que lo tenemos en cuenta con isEmtpy()
            while ((lineaCabecera = flujoEntrada.readLine()) != null && !lineaCabecera.isEmpty()) {
                //La rompemos por el separador ":<espacio>" para separar ambas partes
                String[] trozosLineaCabecera = lineaCabecera.split(": ");
                cabeceras.put(trozosLineaCabecera[0], trozosLineaCabecera[1]);
            }

            printPeticionConCabeceras();
            //Rompemos la línea en trozos, separados por uno o más espacios
            //Formato esperado: MétodoHttp /recurso Protocolo/Version
            //Ejemplo: GET /wiki/Java HTTP/2
            String[] datos = peticion.split("\s+");
            //Confirmamos que sea una solicitud HTTP
            if (datos[2].contains("HTTP")) {
                switch (datos[0]) {
                    case "GET":
                        procesarGet(datos[1]);
                        break;
                    default: //Solo implementamos respuesta a solicitudes GET
                        //ERROR 501: Se puede probar fácilmente con Postman instalado localmente: https://www.postman.com/
                        cuerpoRespuesta = "<html><head><title>Error 501</title></head>" +
                                "<body><h1>Error</h1><p>Método  " + datos[0] + " no implementado</p></body></html>";
                        cabeceraRespuesta = "HTTP/1.1 501 Not Implemented\n" +
                                "Content-Type:text/html;charset=UTF-8\n" +
                                "Content-Length: " + (cuerpoRespuesta.length() + 2) + "\n";
                        respuestaHttp();
                        System.out.println("Sólo se atienden peticiones GET");
                }
            } else {
                //Qué código de respuesta sería adecuado devolver aquí?
                System.out.println("Sólo se responde a solicitudes HTTP");
            }
        } catch (IOException e) {
            System.err.println("Error al abrir los flujos de entrada/salida");
        } catch (NullPointerException e) {//Postman parece que genera dos solicitudes cada vez, una sin contenido
            //System.err.println("Petición nula");
        }
    }

    /**
     * Imprime las cabeceras recibidas
     */
    private void printPeticionConCabeceras() {
        if(peticion == null) return;

        System.out.println(" \n*********** PETICIÓN RECIBIDA ************");
        System.out.println("Línea de petición: " + peticion);
        System.out.println("Cabeceras: ");
        for (String datoCabecera : cabeceras.keySet()) {
            System.out.println(datoCabecera + ": " + cabeceras.get(datoCabecera));
        }
        System.out.println();
    }

    private void procesarGet(String recurso) {

        //La respuesta depende del recurso (la página) solicitada
        switch (recurso) {
            case "/":
                //En caso de devolver el contenido de archivos, devolveríamos el index.html
                cuerpoRespuesta = "<html><head><title>Página principal</title></head>" +
                        "<body><h1>Página principal</h1><p>Servidor funciona correctamente</p></body></html>";
                cabeceraRespuesta = "HTTP/1.1 200 OK\n" +
                        "Content-Type:text/html;charset=UTF-8\n" +
                        "Content-Length: " + (cuerpoRespuesta.length() + 2) + "\n";

                break;
            case "/prueba.html":
                //En caso de devolver el contenido de archivos, devolveríamos el index.html
                cuerpoRespuesta = "<html><head><title>Página de prueba</title></head>" +
                        "<body><h1>Página de prueba</h1><p>Todo funcionando OK</p></body></html>";
                cabeceraRespuesta = "HTTP/1.1 200 OK\n" +
                        "Content-Type:text/html;charset=UTF-8\n" +
                        "Content-Length: " + (cuerpoRespuesta.length() + 2) + "\n";
                break;
            default://Si no tenemos el recurso solicitado, error 404
                cuerpoRespuesta = "<html><head><title>Error 404</title></head>" +
                        "<body><h1>Error</h1><p>Página no encontrada</p></body></html>";
                cabeceraRespuesta = "HTTP/1.1 404 Not Found\n" +
                        "Content-Type:text/html;charset=UTF-8\n" +
                        "Content-Length: " + (cuerpoRespuesta.length() + 2) + "\n";
                break;
        }
        respuestaHttp();
    }

    /**
     * Una vez preparados la cabecera y el cuerpo, los escribe en el flujo de salida como respuesta
     */
    private void respuestaHttp() {
        flujoSalida.println(cabeceraRespuesta);
        flujoSalida.println(cuerpoRespuesta);
    }
}
