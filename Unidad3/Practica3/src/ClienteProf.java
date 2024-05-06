import java.io.IOException;
import java.net.*;

public class ClienteProf {
    private final String HOST_SERVIDOR = "localhost";
    private final int NUM_PUERTO_SERVIDOR = 6000;
    private final int TAM_PAQUETE = 1024;
    DatagramSocket socketCliente;

    /**
     * El constructor inicializa abre el socket udp en un puerto aleatorio
     * y envía un saludo aleatorio al servidor
     * El socket udp queda abierto a la espera de recibir el número
     */
    private ClienteProf() {
        try {
            socketCliente = new DatagramSocket();
            System.out.println("Puerto asociado: " + socketCliente.getLocalPort());
            String saludo = Saludo.getAleatorio();
            byte[] saludoBuffer = saludo.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(saludoBuffer, saludoBuffer.length, InetAddress.getByName(HOST_SERVIDOR),NUM_PUERTO_SERVIDOR);
            socketCliente.send(paqueteEnvio);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra la conexión con el servidor
     */
    private void cerrar(){
        socketCliente.close();
    }


    /**
     * Recibe e imprime el número recibido del servidor
     */
    private void recibirNumero(){
        byte[] buffer = new byte[TAM_PAQUETE];
        DatagramPacket paqueteRecepcion = new DatagramPacket(buffer, buffer.length);
        try {
            socketCliente.receive(paqueteRecepcion);
            String recibido = new String(paqueteRecepcion.getData()).trim();
            System.out.println("El número recibido por el servidor es " + recibido);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Crea UN cliente, recibe los datos del servidor, y lo cierra
     * Probad a ejecutarlo tantas veces como clientes hayáis indicado al servidor.
     * Y alguna más para ver qué pasa.
     */
    public static void main(String[] args) {
        ClienteProf cliente1 = new ClienteProf();
        cliente1.recibirNumero();
        cliente1.cerrar();
    }
}
