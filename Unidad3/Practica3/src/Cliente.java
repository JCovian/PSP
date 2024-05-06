import com.sun.jdi.DoubleType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Cliente {
    private final String HOST_SERVIDOR = "localhost";
    private final int NUM_PUERTO_SERVIDOR = 6000;
    private final int TAM_PAQUETE = 1024;
    DatagramSocket socketCliente;

    /**
     * El constructor inicializa abre el socket udp en un puerto aleatorio
     * y envía un saludo aleatorio al servidor
     * El socket udp queda abierto a la espera de recibir el número
     */
    private Cliente() {
        try {
            socketCliente = new DatagramSocket();
            byte[] mensaje = Saludo.getAleatorio().getBytes();
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName(HOST_SERVIDOR), NUM_PUERTO_SERVIDOR);
            socketCliente.send(envio);
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
        DatagramPacket recepcion = new DatagramPacket(buffer, buffer.length);
        try {
            socketCliente.receive(recepcion);
            String datos = new String(recepcion.getData(),0,recepcion.getLength());
            System.out.println("El número recibido por el servidor es " + datos);

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
        Cliente cliente1 = new Cliente();
        cliente1.recibirNumero();
        cliente1.cerrar();
    }
}
