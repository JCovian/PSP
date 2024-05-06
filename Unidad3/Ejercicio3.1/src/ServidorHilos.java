import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @author Jose Aquilino Covián Ornia
 * Practica 3.1
 * Crear una aplicación cliente/servidor usando sockets TCP en la que el servidor debe generar un número secreto
 * de forma aleatoria entre el 0 y el 100 (exc). El objetivo del cliente es solicitarle al usuario un número y enviarlo
 * al servidor hasta que adivine el número secreto. Para ello, el servidor para cada número que le envía el cliente le
 * indicará si es menor, mayor o es el número secreto.
 */
public class ServidorHilos {
    //Usamos un puerto registrado
    private final int NUM_PUERTO = 6000;
    private final ServerSocket servidor;

    /**
     * El constructor expone el puerto indicado
     */
    public ServidorHilos(){
        try {
            servidor = new ServerSocket(NUM_PUERTO);
            InetAddress server = InetAddress.getLocalHost();
            System.out.println("===================================\n"
                    + "Servidor Número Secreto en linea\n"
                    + "IP: " + server.getHostAddress() + " Puerto: " + NUM_PUERTO
                    + "\n===================================\n");
            while (true) {
                System.out.println("Esperando cliente....");
                //TODO: While para seguir pidiendo clientes
                aceptaCliente();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra el puerto de escucha
     */
    private void cerrar() {
        try {
            servidor.close();
            System.out.println("=================\nServidor cerrado\n=================");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Acepta cliente
     */
    private void aceptaCliente() {
        //No utilizar try-with-resources porque me cerrará el socket
        try {
            Socket cliente = servidor.accept();
            new HiloComunicacionCliente(cliente, generaAleatorio()).start();
            System.out.println("Bienvenido, conexión aceptada\n===================================");
            //comunicaCliente(cliente, generaAleatorio());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Comunicación con el cliente
     */
    private void comunicaCliente(Socket cliente, int numSecreto) {
        //Para testear la aplicación
        //System.out.println("Num secreto: " + numSecreto);
        boolean esAcierto = false;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        //Recibe números y muestra resultado hasta que acierte el número
        try {
            while (!esAcierto) {
                flujoEntrada = new DataInputStream(cliente.getInputStream());
                flujoSalida = new DataOutputStream(cliente.getOutputStream());
                int num = flujoEntrada.readInt();
                System.out.println("Número recibido: " + num);
                if (num == numSecreto) {
                    flujoSalida.writeUTF("Has acertado");
                    esAcierto = true;
                } else flujoSalida.writeUTF((num < numSecreto ? "Tu número es menor" : "Tu número es mayor"));
            }
            cerrar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generador de números aleatorios
     */
    private int generaAleatorio () {
        Random random = new Random();
        return random.nextInt(100);
    }

    public static void main(String[] args) {
        new ServidorHilos();
    }
}