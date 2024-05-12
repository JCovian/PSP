import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    private static final int PUERTO = 6005;

    public static void main(String[] args) throws IOException {
        try (ServerSocket servidor = new ServerSocket(PUERTO);) {
            //Mantiene al servidor solicitando clientes constantemente
            while (true) {
                System.out.println("A la espera de un jugador...");
                try {
                    //Crea el socket del cliente en un hilo nuevo
                    Socket cliente = servidor.accept();
                    new Hilo(cliente, generarAleatorio()).start();
                    System.out.println("Bienvenido, conexión aceptada\n===================================");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**
     * Devuelve un número aleatorio entre 1 y 100
     */
    private static int generarAleatorio() {
        Random random = new Random();
        return random.nextInt(100);
    }
}

