import java.io.*;
import java.net.Socket;

public class Cliente {
    private final String HOST_SERVIDOR = "localhost";
    private final int NUM_PUERTO_SERVIDOR = 6000;
    private Socket cliente;
    /**
     * El constructor abre la conexión con el servidor
     */
    public Cliente() {
        try {
            cliente = new Socket(HOST_SERVIDOR,NUM_PUERTO_SERVIDOR);
        } catch (IOException e) {
            System.err.println(e.getMessage());;
        }
    }

    /**
     * Cierra la conexión con el servidor
     */
    public void cerrar(){
        try {
            cliente.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Abre el flujo de entrada
     * Recibe e imprime el número recibido del servidor
     */
    public void recibirNumero(){
        try {
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println(flujoEntrada.readUTF());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Crea UN cliente, recibe los datos del servidor, y lo cierra
     * Probad a ejecutarlo tantas veces como clientes hayáis indicado al servidor.
     * Y alguna más para ver qué pasa.
     */
    public static void main(String[] args) {
        int numClientes = 4;
        for (int i = 1; i <= numClientes; i++) {
            Cliente cliente = new Cliente();
            cliente.recibirNumero();
            cliente.cerrar();
        }

    }
}
